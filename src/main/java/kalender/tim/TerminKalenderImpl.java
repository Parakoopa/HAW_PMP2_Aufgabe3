package kalender.tim;

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
	private List<Termin> kalender = new ArrayList<Termin>();

	public boolean eintragen(Termin termin) {
	    try {
            this.kalender.add(termin);
            return true;
        }
        catch(NullPointerException NE ) {
            System.out.println("Can't add termin " + NE.getMessage() + " to Kalender!");
        }
        return false;
	}


	public void verschiebenAuf(Termin termin, Datum datum) {
        try {
            termin.verschiebeAuf(datum);
        }
        catch(NullPointerException NE ) {
            System.out.println("Termin" + NE.getMessage() + " can't be found!");
        }
	}


	public boolean terminLoeschen(Termin termin) {
        try {
            this.kalender.remove(termin);
        }
        catch(NullPointerException NE ) {
            System.out.println("Termin" + NE.getMessage() + " can't be found!");
        }
		return false;
	}


	public boolean enthaeltTermin(Termin termin) {
            return this.kalender.contains(termin);
	}


	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
		return kalender
                .stream()
                .filter(s -> s.getDatum().getTag() == tag)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

    public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
        return kalender
                .stream()
                .filter(s -> s.getDatum().getWoche() == woche)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}


	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
        return kalender
                .stream()
                .filter(s -> s.getDatum().getMonat() == monat)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

}
