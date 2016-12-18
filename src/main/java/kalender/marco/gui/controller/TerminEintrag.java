package kalender.marco.gui.controller;

import kalender.interfaces.Datum;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminMitWiederholung;

/**
 * Ein Eintrag in die Termin-Tabellen für Tages- und Monatsansicht
 */

public class TerminEintrag implements Comparable<TerminEintrag> {
    private Datum datum;

    private Termin termin;

    public Datum getDatum() {
        return datum;
    }

    public String getBeschreibung() {
        return termin.getBeschreibung();
    }

    /**
     * Liefert das Datum des ursprünglichen Termins bei Terminen
     * mit Wiederholung oder einen leeren String.
     */
    public String getWiederholtVon() {
        if (termin instanceof TerminMitWiederholung) {
            //TerminMitWiederholung terminMitWdh = (TerminMitWiederholung) termin;
            return termin.getDatum().toString();
        }
        return "";
    }

    public Termin getTermin() {
        return termin;
    }

    public TerminEintrag(Datum datum, Termin termin) {
        this.datum = datum;
        this.termin = termin;
    }

    /**
     * Vergleich via Vergleich der Termine
     */
    @Override
    public int compareTo(TerminEintrag o) {
        return termin.compareTo(o.termin);
    }
}