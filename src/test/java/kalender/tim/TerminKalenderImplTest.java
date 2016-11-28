package kalender.tim;

import kalender.WiederholungType;
import kalender.interfaces.Datum;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Integrationstest!
 * ABHÄNGIG VON ALLEN IMPLEMENTATIONEN
 */
public class TerminKalenderImplTest {

    private TerminKalender terminKalender;
    private TerminImpl terminA;
    private TerminImpl terminB;
    private TerminImpl terminExistiertNicht;
    private TerminMitWiederholungImpl terminMitWiederholungA;
    private TerminImpl terminA2;

    @Before
    public void setUp() {
        terminKalender = new TerminKalenderImpl();
        terminA = new TerminImpl("TestA",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(60)
        );
        terminA2 = new TerminImpl("TestA zur selben Zeit",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(30)
        );
        terminB = new TerminImpl("TestB",
                new DatumImpl(
                        new TagImpl(2016, 4, 3),
                        new UhrzeitImpl(11, 0)
                ), new DauerImpl(10)
        );
        terminMitWiederholungA = new TerminMitWiederholungImpl("TestC",
                new DatumImpl(
                        new TagImpl(2016, 4, 1),
                        new UhrzeitImpl(12, 10)
                ), new DauerImpl(20), WiederholungType.TAEGLICH, 10000, 1
        );
        terminExistiertNicht = new TerminImpl("TestX",
                new DatumImpl(
                        new TagImpl(2016, 10, 3),
                        new UhrzeitImpl(11, 0)
                ), new DauerImpl(1)
        );
        terminKalender.eintragen(terminA);
        terminKalender.eintragen(terminA2);
        terminKalender.eintragen(terminB);
        terminKalender.eintragen(terminMitWiederholungA);
    }

    @Test
    public void verschiebenAuf() {
        terminKalender.verschiebenAuf(terminA, new DatumStub(2016, 5, 1, 5, 12, 34));
        assertEquals(
                "TerminA findet nach der Verschiebung am 5. Januar 2016 um 12:34 Uhr statt",
                new DatumStub(2016, 5, 1, 5, 12, 34),
                terminA.getDatum()
        );
        assertTrue(
                "TerminA ist immernoch im Kalender",
                terminKalender.enthaeltTermin(terminA)
        );
    }

    @Test
    public void terminLoeschen() {
        assertTrue(
                "TerminA wurde aus dem Kalender gelöscht",
                terminKalender.terminLoeschen(terminA)
        );
        assertFalse(
                "TerminA ist nicht mehr im Kalender",
                terminKalender.enthaeltTermin(terminA)
        );
    }

    @Test
    public void enthaeltTermin() {
        assertTrue(
                "TerminA ist im Kalender",
                terminKalender.enthaeltTermin(terminA)
        );
        assertFalse(
                "terminExistiertNicht ist nicht im Kalender",
                terminKalender.enthaeltTermin(terminExistiertNicht)
        );
    }

    @Test
    public void termineFuerTag() {
        Map<Datum, List<Termin>> leer = new HashMap<>();
        Map<Datum, List<Termin>> listeFuerTermineAmTag = new HashMap<>();
        List<Termin> listeAmTagA = new ArrayList<>();
        listeAmTagA.add(new TerminImpl("TestA",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(60)
        ));
        listeAmTagA.add(new TerminImpl("TestA zur selben Zeit",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(30)
        ));
        List<Termin> listeAmTagB = new ArrayList<>();
        listeAmTagB.add(new TerminMitWiederholungImpl("TestC",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 10)
                ), new DauerImpl(20), WiederholungType.TAEGLICH, 9969, 1
        ));

        listeFuerTermineAmTag.put(new DatumImpl(
                new TagImpl(2016, 5, 1),
                new UhrzeitImpl(12, 0)
        ), listeAmTagA);

        listeFuerTermineAmTag.put(new DatumImpl(
                new TagImpl(2016, 5, 1),
                new UhrzeitImpl(12, 10)
        ), listeAmTagB);

        assertEquals(
                "TerminA + terminA2 existiert am 1.6.2016, und eine Widerholung von TestC.",
                listeFuerTermineAmTag,
                terminKalender.termineFuerTag(new TagImpl(2016,5,1))
        );

        assertEquals(
                "An diesem Tag gibt es keine Termine",
                leer,
                terminKalender.termineFuerTag(new TagImpl(2016,3,10))
        );
    }

    @Test
    public void termineFuerWoche() {
        Map<Datum, List<Termin>> leer = new HashMap<>();
        Map<Datum, List<Termin>> termineFuerWoche = new HashMap<>();
        List<Termin> listeAmTagA = new ArrayList<>();
        listeAmTagA.add(new TerminImpl("TestA",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(60)
        ));
        listeAmTagA.add(new TerminImpl("TestA zur selben Zeit",
                new DatumImpl(
                        new TagImpl(2016, 5, 1),
                        new UhrzeitImpl(12, 0)
                ), new DauerImpl(30)
        ));
        termineFuerWoche.put(new DatumImpl(
                new TagImpl(2016, 5, 1),
                new UhrzeitImpl(12, 0)
        ),listeAmTagA);
        for (int i = 30; i <= 31; i++) {
            ArrayList<Termin> tmp = new ArrayList<>();
            tmp.add(new TerminMitWiederholungImpl("TestC",
                    new DatumImpl(
                            new TagImpl(2016, 4, i),
                            new UhrzeitImpl(12, 10)
                    ), new DauerImpl(20), WiederholungType.TAEGLICH, 10001-i, 1
            ));
            termineFuerWoche.put(new DatumImpl(
                    new TagImpl(2016, 4, i),
                    new UhrzeitImpl(12, 10)
            ), tmp);
        }
        for (int i = 1; i <= 5; i++) {
            ArrayList<Termin> tmp = new ArrayList<>();
            tmp.add(new TerminMitWiederholungImpl("TestC",
                    new DatumImpl(
                            new TagImpl(2016, 5, i),
                            new UhrzeitImpl(12, 10)
                    ), new DauerImpl(20), WiederholungType.TAEGLICH, 9970-i, 1
            ));
            termineFuerWoche.put(new DatumImpl(
                    new TagImpl(2016, 5, i),
                    new UhrzeitImpl(12, 10)
            ), tmp);
        }

        assertEquals(
                "TerminB + ein Haufen TestC Wiederholungen existieren in der ersten Woche Juni 2016",
                termineFuerWoche,
                terminKalender.termineFuerWoche(new WocheImpl(2016,5,1))
        );

        assertEquals(
                "In dieser Woche gibt es keine Termine",
                leer,
                terminKalender.termineFuerWoche(new WocheImpl(2016,3,1))
        );
    }

    @Test
    public void termineFuerMonat() {
        Map<Datum, List<Termin>> leer = new HashMap<>();
        Map<Datum, List<Termin>> listeFuerTermineImMonat = new HashMap<>();
        List<Termin> listeA = new ArrayList<>();
        listeA.add(new TerminImpl("TestB",
                new DatumImpl(
                        new TagImpl(2016, 4, 3),
                        new UhrzeitImpl(11, 0)
                ), new DauerImpl(10)
        ));
        listeFuerTermineImMonat.put(new DatumImpl(
                new TagImpl(2016, 4, 3),
                new UhrzeitImpl(11, 0)
        ), listeA);
        for (int i = 1; i <= 31; i++) {
            ArrayList<Termin> tmp = new ArrayList<>();
            tmp.add(new TerminMitWiederholungImpl("TestC",
                    new DatumImpl(
                            new TagImpl(2016, 4, i),
                            new UhrzeitImpl(12, 10)
                    ), new DauerImpl(20), WiederholungType.TAEGLICH, 10001-i, 1
            ));
            listeFuerTermineImMonat.put(new DatumImpl(
                    new TagImpl(2016, 4, i),
                    new UhrzeitImpl(12, 10)
            ), tmp);
        }

        assertEquals(
                "TerminB + ein Haufen TestC Wiederholungen existieren im Juni 2016",
                listeFuerTermineImMonat,
                terminKalender.termineFuerMonat(new MonatImpl(2016,4))
        );

        assertEquals(
                "In diesem Monat gibt es keine Termine",
                leer,
                terminKalender.termineFuerMonat(new MonatImpl(2016,3))
        );

    }

}