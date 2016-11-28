package kalender;

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
	private List<Termin> termine = new ArrayList<Termin>();

	@Override
	public boolean eintragen(Termin termin) {
		// Da die Methode ein Boolean zurückgeben soll gehe ich an dieser Stelle davon aus, dass
		// doppelte Termine verboten sind.
		if (enthaeltTermin(termin)) {
			return false;
		}
		termine.add(termin);
		return true;
	}

	@Override
	public void verschiebenAuf(Termin termin, Datum datum) {
		termine.stream()
				.filter(termin::equals) // == (terminInKalender) -> termin.equals(terminInKalender)
				.forEach((terminInKalender) -> terminInKalender.verschiebeAuf(datum));
	}

	@Override
	public boolean terminLoeschen(Termin termin) {
		return termine.removeIf(termin::equals); // = terminInList -> termin.equals(terminInList)
	}

	@Override
	public boolean enthaeltTermin(Termin termin) {
		return termine.stream().anyMatch(termin::equals); // = terminInList -> termin.equals(terminInList)
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineAn(tag).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineIn(woche).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
		return termine
				.stream()
				.flatMap(termin -> termin.termineIn(monat).values().stream())
				.collect(Collectors.groupingBy(Termin::getDatum));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TerminKalenderImpl that = (TerminKalenderImpl) o;

		return termine != null ? termine.equals(that.termine) : that.termine == null;

	}

	@Override
	public int hashCode() {
		return termine != null ? termine.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "TerminKalenderImpl{" +
				"termine=" + termine +
				'}';
	}
}
