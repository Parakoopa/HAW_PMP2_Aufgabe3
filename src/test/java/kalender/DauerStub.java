package kalender;

import kalender.interfaces.Dauer;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class DauerStub implements Dauer {
    @Override
    public int inMinuten() {
        return 30240;
    }

    @Override
    public int inStunden() {
        return 504;
    }

    @Override
    public int inTagen() {
        return 21;
    }

    @Override
    public int inWochen() {
        return 3;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dauer)) return false;
        Dauer d = (Dauer) o;
        return d.inMinuten() == inMinuten();
    }

    @Override
    public String toString() {
        return "Dauer{"+inMinuten()+"}";
    }

    // NOT NEEDED / TESTED

    @Override
    public int anteilMinuten() {
        return 0;
    }

    @Override
    public int anteilStunden() {
        return 0;
    }

    @Override
    public int anteilTage() {
        return 0;
    }

    @Override
    public int anteilWochen() {
        return 0;
    }

    @Override
    public int compareTo(Dauer dauer) {
        return 0;
    }
}
