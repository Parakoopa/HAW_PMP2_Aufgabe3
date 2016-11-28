package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Tag;

import java.util.Calendar;

/**
 * Stubs stellen dumme Implementationen dar, die nur dazu dienen
 * Klassen unabängig voneinander testen zu müssen.
 * So muss ich zbs. keine funktionierende TagImpl haben um Datum testen zu können.
 */
class TagStub implements Tag {

    int jahr = 2016;
    int month = 1;
    int tagImJahr = 32;
    int tagImMonat = 1;

    public TagStub() {
    }

    public TagStub(int jahr, int month, int tagImJahr, int tagImMonat) {
        this.jahr = jahr;
        this.month = month;
        this.tagImJahr = tagImJahr;
        this.tagImMonat = tagImMonat;
    }

    @Override
    public int getJahr() {
        return jahr;
    }

    @Override
    public int getMonat() {
        return month; // February
    }

    @Override
    public int getTagImJahr() {
        return tagImJahr;
    }

    @Override
    public int getTagImMonat() {
        return tagImMonat;
    }

    // Lazy Equals Implementation w/o hashCode, good enough for these tests.

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tag)) return false;
        Tag d = (Tag) o;
        // Tag im Jahr may be incorrect, but that's okay.
        return          d.getJahr()      == getJahr() &&
                        d.getMonat()     == getMonat() &&
                        d.getTagImMonat()== getTagImMonat();
    }

    @Override
    public String toString() {
        return "Tag{"+getTagImMonat()+"."+(getMonat()+1)+"."+getJahr()+"}";
    }

    // NOT NEEDED / TESTED

    @Override
    public long differenzInTagen(Tag other) {
        return 0;
    }

    @Override
    public Calendar inBasis() {
        return null;
    }

    @Override
    public int compareTo(Tag tag) {
        return 0;
    }

    @Override
    public Datum getStart() {
        return null;
    }

    @Override
    public Datum getEnde() {
        return null;
    }
}