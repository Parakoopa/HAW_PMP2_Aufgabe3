package kalender;

import kalender.interfaces.*;

import java.util.Calendar;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class DatumStub implements Datum {

    private int jahr;
    private int tagImMonat;
    private int monatImJahr;
    private int tagImJahr = 0; // Only needed to set if needed
    private int stunde = 0;
    private int minute = 0;

    public DatumStub(int jahr, int tagImMonat, int monatImJahr) {
        this.jahr = jahr;
        this.tagImMonat = tagImMonat;
        this.monatImJahr = monatImJahr;
    }

    public DatumStub(int jahr, int tagImMonat, int monatImJahr, int tagImJahr) {
        this.jahr = jahr;
        this.tagImMonat = tagImMonat;
        this.monatImJahr = monatImJahr;
        this.tagImJahr = tagImJahr;
    }

    public DatumStub(int jahr, int tagImMonat, int monatImJahr, int stunde, int minute) {
        this.jahr = jahr;
        this.tagImMonat = tagImMonat;
        this.monatImJahr = monatImJahr;
        this.stunde = stunde;
        this.minute = minute;
    }

    public DatumStub(int jahr, int tagImMonat, int monatImJahr, int tagImJahr, int stunde, int minute) {
        this.jahr = jahr;
        this.tagImMonat = tagImMonat;
        this.monatImJahr = monatImJahr;
        this.tagImJahr = tagImJahr;
        this.stunde = stunde;
        this.minute = minute;
    }

    @Override
    public int getJahr() {
        return jahr;
    }

    @Override
    public int getTagImMonat() {
        return tagImMonat;
    }

    @Override
    public int getTagImJahr() {
        return tagImJahr;
    }

    @Override
    public int getMonatImJahr() {
        return monatImJahr;
    }

    @Override
    public Uhrzeit getUhrzeit() {
        return new UhrzeitStub(stunde, minute);
    }

    @Override
    public Tag getTag() {
        return new TagStub(jahr, monatImJahr, tagImJahr, tagImMonat);
    }

    @Override
    public Monat getMonat() {
        return new MonatStub(monatImJahr, jahr);
    }

    // Liefert statischen Wert zurück, wenn Woche genau mit dem Stub übereinstimmt, sonst null.
    // Alles andere ist mir gerade zu kompliziert ohne die ganze Logik einzubauen
    @Override
    public Woche getWoche() {
        if (jahr == 2016 && monatImJahr == 1 && tagImMonat < 7) {
            return new WocheStub();
        }
        return null;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Datum)) return false;
        Datum d = (Datum) o;
        return
                d.getJahr() == getJahr() &&
                d.getTagImMonat() == getTagImMonat() &&
                d.getMonatImJahr() == getMonatImJahr() &&
                getUhrzeit().equals(d.getUhrzeit());
    }

    @Override
    public String toString() {
        return "Datum{"+getTagImMonat()+"."+(getMonatImJahr()+1)+"."+getJahr()+" "+stunde+":"+minute+"}";
    }

    // NOT NEEDED / TESTED

    @Override
    public int getWocheImMonat() {
        return 0;
    }

    @Override
    public int getWocheImJahr() {
        return 0;
    }

    @Override
    public Datum add(Dauer dauer) {
        return null;
    }

    @Override
    public Datum sub(Dauer dauer) {
        return null;
    }

    @Override
    public Dauer abstand(Datum d) {
        return null;
    }

    @Override
    public long differenzInTagen(Datum d) {
        return 0;
    }

    @Override
    public int inMinuten() {
        return 0;
    }

    @Override
    public Calendar inBasis() {
        return null;
    }

    @Override
    public int compareTo(Datum datum) {
        // Throw a exception to mark this method as called.
        return 0;
    }
    public class CompareToWasCalledException extends Exception {}
}
