package kalender;

import kalender.interfaces.Uhrzeit;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class UhrzeitStub implements Uhrzeit {

    private int stunde;
    private int minute;

    public UhrzeitStub() {
        this.stunde = 12;
        this.minute = 21;
    }

    public UhrzeitStub(int stunde, int minute) {
        this.stunde = stunde;
        this.minute = minute;
    }

    @Override
    public int getStunde() {
        return stunde;
    }

    @Override
    public int getMinuten() {
        return minute;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Uhrzeit)) return false;
        Uhrzeit d = (Uhrzeit) o;
        return
                d.getStunde() == getStunde() &&
                        d.getMinuten()== getMinuten();
    }

    @Override
    public String toString() {
        return "Uhrzeit{"+getStunde()+":"+getMinuten()+"}";
    }

    // NOT NEEDED / TESTED

    @Override
    public int compareTo(Uhrzeit uhrzeit) {
        return 0;
    }
}