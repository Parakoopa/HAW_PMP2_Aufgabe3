package kalender.marco;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 26.11.16.
 */
public class DatumImplTest {

    private DatumImpl datumWithTag;
    private DatumImpl datumWithTagUhrzeit;
    private DatumImpl datumWithDatum;
    private DatumImpl datumVorZeitumstellung;

    @Before
    public void setUp() {
        this.datumWithTag = new DatumImpl(new TagStub());
        this.datumWithTagUhrzeit = new DatumImpl(new TagStub(), new UhrzeitStub());
        this.datumVorZeitumstellung = new DatumImpl(new TagStub(2016,2,86,26), new UhrzeitStub());
        this.datumWithDatum = new DatumImpl(datumWithTagUhrzeit);
    }

    @Test
    public void compareTo() {
        assertEquals(
                "1.2.16 12:21 Uhr ist 741 größer als 1.2.2016 00:00 Uhr",
                741,
                this.datumWithTagUhrzeit.compareTo(datumWithTag)
        );
        assertEquals(
                "1.2.16 00:00 Uhr ist 741 kleiner als 1.2.2016 12:21 Uhr",
                -741,
                this.datumWithTag.compareTo(datumWithTagUhrzeit)
        );
        assertEquals(
                "1.2.16 12:21 Uhr und 1.2.2016 12:21 Uhr sind gleich groß",
                0,
                this.datumWithTagUhrzeit.compareTo(datumWithDatum)
        );
    }

    @Test
    public void getTag() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 1.2.16. als Tag ausgeben",
                new TagStub(),
                this.datumWithTagUhrzeit.getTag()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 1.2.16. als Tag ausgeben",
                new TagStub(),
                this.datumWithTag.getTag()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 1.2.16. als Tag ausgeben",
                new TagStub(),
                this.datumWithDatum.getTag()
        );
        assertNotSame(
                "getTag darf nur Kopien von Tagen ausgeben, intern darf nur Calendar verwendet werden",
                new TagStub(),
                this.datumWithTagUhrzeit.getTag()
        );
    }

    @Test
    public void getWoche() {
        assertEquals(
                "1.2.16 12:21 Uhr muss die richtige Woche ausgeben",
                new WocheStub(),
                this.datumWithTagUhrzeit.getWoche()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss die richtige Woche ausgeben",
                new WocheStub(),
                this.datumWithTag.getWoche()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss die richtige Woche ausgeben",
                new WocheStub(),
                this.datumWithDatum.getWoche()
        );
    }

    @Test
    public void getMonat() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 1.2. als Monat ausgeben",
                new MonatStub(),
                this.datumWithTagUhrzeit.getMonat()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 1.2. als Monat ausgeben",
                new MonatStub(),
                this.datumWithTag.getMonat()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 1.2. als Monat ausgeben",
                new MonatStub(),
                this.datumWithDatum.getMonat()
        );
    }

    @Test
    public void getUhrzeit() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 12:21 als Uhrzeit ausgeben",
                new UhrzeitStub(),
                this.datumWithTagUhrzeit.getUhrzeit()
        );
        assertNotEquals(
                "1.2.16 00:00 Uhr darf nicht 12:21 als Uhrzeit ausgeben",
                new UhrzeitStub(),
                this.datumWithTag.getUhrzeit()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 12:21 als Uhrzeit ausgeben",
                new UhrzeitStub(),
                this.datumWithDatum.getUhrzeit()
        );
        assertNotSame(
                "getUhrzeit darf nur Kopien von Uhrzeiten ausgeben, intern darf nur Calendar verwendet werden",
                new UhrzeitStub(),
                this.datumWithTagUhrzeit.getUhrzeit()
        );
    }

    @Test
    public void getJahr() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 2016 als Jahr ausgeben",
                2016,
                this.datumWithTagUhrzeit.getJahr()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 2016 als Jahr ausgeben",
                2016,
                this.datumWithTag.getJahr()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 2016 als Jahr ausgeben",
                2016,
                this.datumWithDatum.getJahr()
        );
    }

    @Test
    public void getTagImMonat() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Tag im Monat ausgeben",
                1,
                this.datumWithTagUhrzeit.getTagImMonat()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 1 als Tag im Monat ausgeben",
                1,
                this.datumWithTag.getTagImMonat()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Tag im Monat ausgeben",
                1,
                this.datumWithDatum.getTagImMonat()
        );
    }

    @Test
    public void getTagImJahr() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 32 als Tag im Jahr ausgeben",
                32,
                this.datumWithTagUhrzeit.getTagImJahr()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 32 als Tag im Jahr ausgeben",
                32,
                this.datumWithTag.getTagImJahr()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 32 als Tag im Jahr ausgeben",
                32,
                this.datumWithDatum.getTagImJahr()
        );
    }

    @Test
    public void getWocheImMonat() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Woche im Monat ausgeben",
                1,
                this.datumWithTagUhrzeit.getWocheImMonat()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 1 als Woche im Monat ausgeben",
                1,
                this.datumWithTag.getWocheImMonat()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Woche im Monat ausgeben",
                1,
                this.datumWithDatum.getWocheImMonat()
        );
    }

    @Test
    public void getWocheImJahr() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 5 als Woche im Jahr ausgeben",
                5,
                this.datumWithTagUhrzeit.getWocheImJahr()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 5 als Woche im Jahr ausgeben",
                5,
                this.datumWithTag.getWocheImJahr()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 5 als Woche im Jahr ausgeben",
                5,
                this.datumWithDatum.getWocheImJahr()
        );
    }

    @Test
    public void getMonatImJahr() {
        // 0 is January
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Monat im Jahr ausgeben",
                1,
                this.datumWithTagUhrzeit.getMonatImJahr()
        );
        assertEquals(
                "1.2.16 00:00 Uhr muss 1 als Monat im Jahr ausgeben",
                1,
                this.datumWithTag.getMonatImJahr()
        );
        assertEquals(
                "1.2.16 12:21 Uhr muss 1 als Monat im Jahr ausgeben",
                1,
                this.datumWithDatum.getMonatImJahr()
        );
    }

    @Test
    public void inMinutenAddSubAbstandDiT() {
        assertEquals(
                "1.2.16 12:21 Uhr muss 24238761 als gesamte Minutenanzahl ausgeben",
                24238761,
                this.datumWithTagUhrzeit.inMinuten()
        );
        assertEquals(
                "1.2.16 12:21 Uhr addiert mit 30240 Minuten, muss 24269001 als Minutenzahl ausgeben",
                24269001,
                this.datumWithTagUhrzeit.add(new DauerStub()).inMinuten()
        );
        assertEquals(
                "1.2.16 12:21 Uhr + 3 Wochen und 1.2.16 12:21 Uhr müssen einen Abstand von 30240 Minuten haben",
                new DauerStub(),
                this.datumWithTagUhrzeit.abstand(this.datumWithDatum)
        );
        assertEquals(
                "1.2.16 12:21 Uhr + 3 Wochen und 1.2.16 12:21 Uhr müssen einen Abstand von 21 Tagen haben",
                21,
                this.datumWithTagUhrzeit.differenzInTagen(this.datumWithDatum)
        );
        assertEquals(
                "1.2.16 12:21 Uhr + 3 Wochen subtrahiert mit 30240 Minuten, muss 24238761 als Minutenzahl ausgeben",
                24238761,
                this.datumWithTagUhrzeit.sub(new DauerStub()).inMinuten()
        );
    }

    @Test
    @Ignore
    public void zeitumstellungTest() {
        assertEquals(
                "26.3.16 12:21 Uhr addiert mit 30240 Minuten, muss 24346701 als Minutenzahl ausgeben\n" +
                        "(eine Stunde mehr wegen Zeitumstellung!)\n" +
                        "Bitte generisch implementieren (für alle Zeitumstellungen!) Hier wird nur die eine getestet!",
                24346701,
                this.datumVorZeitumstellung.add(new DauerStub()).inMinuten()
        );
        assertEquals(
                "26.3.16 12:21 Uhr + 3 Wochen subtrahiert mit 30240 Minuten, muss 24316521 als Minutenzahl ausgeben\n" +
                        "(eine Stunde weniger wegen Zeitumstellung!)\n" +
                        "Bitte generisch implementieren (für alle Zeitumstellungen!) Hier wird nur die eine getestet!",
                24316521,
                this.datumVorZeitumstellung.sub(new DauerStub()).inMinuten()
        );
    }

    @Test
    public void equals() {
        assertEquals(
                "datumWithTagUhrzeit und das gegebene Datum sind gleich.",
                datumWithDatum,
                datumWithTagUhrzeit
        );
    }
}