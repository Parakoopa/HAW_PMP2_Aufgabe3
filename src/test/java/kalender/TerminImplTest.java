package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Termin;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by marco on 26.11.16.
 */
public class TerminImplTest {

    TerminImpl terminA;
    TerminImpl terminB;

    @Before
    public void setUp() {

        terminA = new TerminImpl("TestA", new DatumStub(2016, 3, 3, 12, 0), new DauerStub());
        terminB = new TerminImpl("TestB", new DatumStub(2016, 2, 1, 12, 0), new DauerStub());

    }

    /*
     *
     * compareTo is not tested because of the limitations
     * of the Stubs. Please implement it with Datum and use the
     * DatumTest to verify your code.
     *
     * @Test
     * public void compareTo() {
     *
     * }
     */

    @Test
    public void getBeschreibung() {
        assertEquals(
                "Die Beschreibung vom TerminA ist TestA.",
                "TestA",
                terminA.getBeschreibung()
        );
        assertEquals(
                "Die Beschreibung vom TerminB ist TestA.",
                "TestB",
                terminB.getBeschreibung()
        );
    }

    @Test
    public void getDatum() {
        assertEquals(
                "TerminA findet am 3. April 2016 um 12 Uhr statt",
                new DatumStub(2016, 3, 3, 94, 12, 0),
                terminA.getDatum()
        );
        assertEquals(
                "TerminB findet am 2. Februar 2016 um 12 Uhr statt",
                new DatumStub(2016, 2, 1, 32, 12, 0),
                terminB.getDatum()
        );
    }

    @Test
    public void getDauer() {
        assertEquals(
                "TerminA ist 30240 Minuten lang",
                new DauerStub(),
                terminA.getDauer()
        );
    }

    @Test
    public void verschiebeAuf() {
        assertEquals(
                "TerminA findet nach der Verschiebung am 5. Januar 2016 um 12:34 Uhr statt",
                new DatumStub(2016, 5, 1, 5, 12, 34),
                terminA.verschiebeAuf(new DatumStub(2016, 5, 1, 5, 12, 34)).getDatum()
        );
        assertSame(
                "Bei einer TerminverschieMonatStubbung wird kein neuer Termin erstellt",
                terminA,
                terminA.verschiebeAuf(new DatumStub(2016, 5, 1, 5, 12, 34))
        );
    }


    @Test
    public void equals() {
        assertEquals(
                "TerminB und der gegebene Termin müssen gleich sein.",
                new TerminImpl("TestB", new DatumStub(2016, 2, 1, 12, 0), new DauerStub()),
                terminB
        );
    }

    @Test
    public void termineInMonat() {

        HashMap<Datum, Termin> leereMap = new HashMap<Datum, Termin>();
        HashMap<Datum, Termin> termineInFebruar = new HashMap<Datum, Termin>();
        termineInFebruar.put(terminB.getDatum(), terminB);

        assertEquals(
                "TerminB findet im Feburar 2016 statt (Hängt von equals Test ab und ggf. von DatumImpl equals Test!!!).",
                termineInFebruar,
                terminB.termineIn(new MonatStub())
        );
        assertEquals(
                "TerminA findet nicht im Feburar 2016 statt.",
                leereMap,
                terminA.termineIn(new MonatStub())
        );
    }

    @Test
    public void termineInWoche() {

        //HashMap<Datum, Termin> leereMap = new HashMap<Datum, Termin>();
        HashMap<Datum, Termin> termineInWoche = new HashMap<Datum, Termin>();
        termineInWoche.put(terminB.getDatum(), terminB);

        assertEquals(
                "TerminB findet im Feburar 2016 in der ersten Woche statt (Hängt von equals Test ab und ggf. von DatumImpl equals Test!!!).",
                termineInWoche,
                terminB.termineIn(new WocheStub())
        );
        // Negativtest geht derzeit leider nicht, ohne die ganze Wochen-Logik nachzubauen, da das Datum
        // Stub nur das einfache Wochen-Mock zurückgibt.
        /*assertEquals(
                "TerminA findet nicht im Feburar 2016 in der ersten Woche statt.",
                leereMap,
                terminA.termineIn(new WocheStub())
        );*/
    }

    @Test
    public void termineAnTag() {

        HashMap<Datum, Termin> leereMap = new HashMap<Datum, Termin>();
        HashMap<Datum, Termin> termine = new HashMap<Datum, Termin>();
        termine.put(terminB.getDatum(), terminB);

        assertEquals(
                "TerminB findet im Feburar 2016 am 2. Tag statt (Hängt von equals Test ab und ggf. von DatumImpl equals Test!!!).",
                termine,
                terminB.termineAn(new TagStub(2016, 1, 32, 2))
        );

        assertEquals(
                "TerminA findet nicht im Feburar 2016 am 2. Tag statt.",
                leereMap,
                terminA.termineAn(new TagStub(2016, 1, 33, 2))
        );
    }

}