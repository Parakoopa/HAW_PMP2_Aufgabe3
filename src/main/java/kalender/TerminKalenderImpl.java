package kalender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
		Map<Datum, List<Termin>> termineDesTages = new HashMap<>();
		termine.stream()
				.map(t -> t.termineAn(tag))
				.forEach(m -> { // alle nach gleichem schema, termine für
					for (Map.Entry<Datum, Termin> entry : m.entrySet()) {
						if (!termineDesTages.containsKey(entry.getKey())) {
							termineDesTages.put(entry.getKey(),
									new ArrayList<>());
						}
						termineDesTages.get(entry.getKey()).add(
								entry.getValue());
					}
				});
		return termineDesTages;
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
		Map<Datum, List<Termin>> termineDerWoche = new HashMap<>();
		termine.stream().map(t -> t.termineIn(woche)).forEach(m -> {
			for (Map.Entry<Datum, Termin> entry : m.entrySet()) {
				if (!termineDerWoche.containsKey(entry.getKey())) {
					termineDerWoche.put(entry.getKey(), new ArrayList<>());
				}
				termineDerWoche.get(entry.getKey()).add(entry.getValue());
			}
		});
		return termineDerWoche;
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
		Map<Datum, List<Termin>> termineDesMonats = new HashMap<>();
		termine.stream().map(t -> t.termineIn(monat)).forEach(m -> {
			for (Map.Entry<Datum, Termin> entry : m.entrySet()) {
				if (!termineDesMonats.containsKey(entry.getKey())) {
					termineDesMonats.put(entry.getKey(), new ArrayList<>());
				}
				termineDesMonats.get(entry.getKey()).add(entry.getValue());
			}
		});
		return termineDesMonats;
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
