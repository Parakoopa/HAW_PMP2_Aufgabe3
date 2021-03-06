package kalender.marco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kalender.interfaces.Datum;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;
import kalender.interfaces.Woche;

public class TerminKalenderImpl implements TerminKalender {

	private List<Termin> termine;

	public TerminKalenderImpl() {
		this.termine = new ArrayList<>();
	}


	public boolean eintragen(Termin termin) {
		// Da die Methode ein Boolean zurückgeben soll gehe ich an dieser Stelle davon aus, dass
		// doppelte Termine verboten sind.
		if (enthaeltTermin(termin)) {
			return false;
		}
		termine.add(termin);
		return true;
	}


	public void verschiebenAuf(Termin termin, Datum datum) {
		// Versuchen den Termin aus der Liste zu verschieben (bei Fehlschlag NPE)
		//terminelist.get(terminelist.indexOf(termin)).verschiebeAuf(datum);
		termine.set(termine.indexOf(termin), termin.verschiebeAuf(datum));
		// oder einfach stumpf den Termin verschieben:
		// termin.verschiebeAuf(datum);
	}


	public boolean terminLoeschen(Termin termin) {
		return termine.removeIf(terminInList -> terminInList.equals(termin));
	}


	public boolean enthaeltTermin(Termin termin) {
		return termine.stream().anyMatch(terminInlist -> terminInlist.equals(termin));
	}


	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineAn(tag).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}


	public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineIn(woche).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}


	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineIn(monat).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}

}
