package kalender;

import java.util.HashMap;
import java.util.Map;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.Woche;

public class TerminImpl implements Termin {

	private String beschreibung;
	private Datum datum;
	private Dauer dauer;

	public TerminImpl(String beschreibung, Datum datum, Dauer dauer) {
		this.beschreibung = beschreibung;
		this.datum = datum;
		this.dauer = dauer;
	}


	@Override
	public int compareTo(Termin o) {
		return this.datum.compareTo(o.getDatum());
	}

	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

	@Override
	public Datum getDatum() {
		return datum;
	}

	@Override
	public Dauer getDauer() {
		return dauer;
	}

	@Override
	public Termin verschiebeAuf(Datum datum) {
		this.datum = datum;
		return this;
	}

	@Override
	public Map<Datum, Termin> termineIn(Monat monat) {
		Map<Datum, Termin> map = new HashMap<>();
		if(datum.getMonat().equals(monat)) {
			map.put(this.getDatum(),this);
		}
		return map;
	}

	@Override
	public Map<Datum, Termin> termineIn(Woche woche) {
		Map<Datum, Termin> map = new HashMap<>();
		if(datum.getWoche().equals(woche)) {
			map.put(this.getDatum(),this);
		}
		return map;
	}

	@Override
	public Map<Datum, Termin> termineAn(Tag tag) {
		Map<Datum, Termin> map = new HashMap<>();
		if(datum.getTag().equals(tag)) {
			map.put(this.getDatum(),this);
		}
		return map;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TerminImpl termin = (TerminImpl) o;

		if (beschreibung != null ? !beschreibung.equals(termin.beschreibung) : termin.beschreibung != null)
			return false;
		if (datum != null ? !datum.equals(termin.datum) : termin.datum != null) return false;
		return dauer != null ? dauer.equals(termin.dauer) : termin.dauer == null;

	}

	@Override
	public int hashCode() {
		int result = beschreibung != null ? beschreibung.hashCode() : 0;
		result = 31 * result + (datum != null ? datum.hashCode() : 0);
		result = 31 * result + (dauer != null ? dauer.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TerminImpl{" +
				"beschreibung='" + beschreibung + '\'' +
				", datum=" + datum +
				", dauer=" + dauer +
				'}';
	}
}
