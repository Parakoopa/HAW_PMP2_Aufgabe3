package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Monat;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class MonatStub implements Monat {

    private int monat = 1;
    private int jahr = 2016;

    public MonatStub() {
    }

    public MonatStub(int monat, int jahr) {
        this.monat = monat;
        this.jahr = jahr;
    }

    @Override
    public int getMonat() {
        return monat; // Feb
    }

    @Override
    public int getJahr() {
        return jahr;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Monat)) return false;
        Monat d = (Monat) o;
        return
                d.getJahr()         == getJahr() &&
                d.getMonat()        == getMonat();
    }

    @Override
    public String toString() {
        return "Monat{"+(getMonat()+1)+"."+getJahr()+"}";
    }

    // NOT NEEDED / TESTED

    @Override
    public Datum getStart() {
        return null;
    }

    @Override
    public Datum getEnde() {
        return null;
    }
}
