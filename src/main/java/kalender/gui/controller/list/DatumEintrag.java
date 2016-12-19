package kalender.gui.controller.list;

import kalender.interfaces.Datum;

import java.text.DateFormatSymbols;

/**
 * Ein Eintrag in der Datum-Tabelle für die Monatsansicht.
 */
public class DatumEintrag {
    private Datum datum;

    public String getTag() {
        return String.valueOf(datum.getTagImMonat());
    }

    /**
     * Liefere den Namen des aktuellen Monats
     */
    public String getMonat() {
        return new DateFormatSymbols().getMonths()[datum.getMonatImJahr()];
    }

    public String getJahr() {
        return String.valueOf(datum.getJahr());
    }

    public Datum getDatum() {
        return datum;
    }

    public DatumEintrag(Datum datum) {
        this.datum = datum;
    }
}
