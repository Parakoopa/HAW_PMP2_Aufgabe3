package kalender.marco;

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
		termin.verschiebeAuf(datum);
	}


	public boolean terminLoeschen(Termin termin) {
		return termine.removeIf(terminInList -> terminInList == termin);
	}


	public boolean enthaeltTermin(Termin termin) {
		return termine.stream().anyMatch(terminInlist -> terminInlist == termin);
	}


	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
		return termine
				.stream()
				.filter(termin -> termin.getDatum().getTag() == tag)
				.collect(Collectors.groupingBy(
					Termin::getDatum,
					Collectors.toList()
				));
	}


	public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
		return termine
				.stream()
				.filter(termin -> termin.getDatum().getWoche() == woche)
				.collect(Collectors.groupingBy(
						Termin::getDatum,
						Collectors.toList()
				));
	}


	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
		return termine
				.stream()
				.filter(termin -> termin.getDatum().getMonat() == monat)
				.collect(Collectors.groupingBy(
						Termin::getDatum,
						Collectors.toList()
				));
	}

}
