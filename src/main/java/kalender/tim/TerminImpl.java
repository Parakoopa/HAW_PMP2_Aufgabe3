package kalender.tim;

import java.util.HashMap;
import java.util.Map;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.Woche;

public class TerminImpl implements Termin {
	private Dauer dauer;
	private Datum datum;
	private String beschreibung;

	public TerminImpl(String beschreibung, Datum datum, Dauer dauer) {
		this.beschreibung = beschreibung;
		this.datum = datum;
		this.dauer = dauer;
	}



	public int compareTo(Termin o) {
		return this.compareTo(o);
	}

	public String getBeschreibung() {return this.beschreibung;}
	public Datum getDatum() {return this.datum;}
	public Dauer getDauer() {return this.dauer;}

	public Termin verschiebeAuf(Datum datum) {
		this.datum = datum;
		return null;
	}


	public Map<Datum, Termin> termineIn(Monat monat) {
        Map<Datum, Termin> map = new HashMap<>();
		if(datum.getMonat().equals(monat)) {
			map.put(this.getDatum(),this);
		}
		return map;
	}


	public Map<Datum, Termin> termineIn(Woche woche) {
        Map<Datum, Termin> map = new HashMap<>();
        if(datum.getMonat().equals(woche)) {
            map.put(this.getDatum(),this);
        }
        return map;
	}


	public Map<Datum, Termin> termineAn(Tag tag) {
        Map<Datum, Termin> map = new HashMap<>();
        if(datum.getMonat().equals(tag)) {
            map.put(this.getDatum(),this);
        }
        return map;
	}

}
