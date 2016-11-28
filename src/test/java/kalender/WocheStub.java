package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Woche;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class WocheStub implements Woche {
    @Override
    public int getJahr() {
        return 2016;
    }

    @Override
    public int getMonat() {
        return 1; // February
    }

    @Override
    public int getWocheImMonat() {
        return 1;
    }

    @Override
    public int getWocheImJahr() {
        return 5;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Woche)) return false;
        Woche d = (Woche) o;
        return
                        d.getJahr()         == getJahr() &&
                        d.getMonat()        == getMonat() &&
                        d.getWocheImMonat() == getWocheImMonat() &&
                        d.getWocheImJahr()  == getWocheImJahr();
    }

    @Override
    public String toString() {
        return "Tag{"+getWocheImMonat()+"Woche in "+(getMonat()+1)+"."+getJahr()+"}";
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
