package kalender;

import kalender.interfaces.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Abhängig von den Datums/DauerKlassen und Wiederholung
 */
public class TerminMitWiederholungImplTest {

    private TerminMitWiederholungImpl terminA;
    private TerminMitWiederholungImpl terminB;

    private Map<Datum, Termin> listeFuerTermineImMonatA;
    private Map<Datum, Termin> listeFuerTermineInWoche;
    private Map<Datum, Termin> listeFuerTermineAmTagA;

    @Before
    public void setUp() {
        terminA = new TerminMitWiederholungImpl("TerminA",
                new DatumImpl(new TagImpl(2016, 1, 1), new UhrzeitImpl(12, 0)),
                new DauerImpl(10),
                WiederholungType.TAEGLICH, 9, 2);
        terminB = new TerminMitWiederholungImpl("TerminB",
                new DatumImpl(new TagImpl(2015, 1, 1), new UhrzeitImpl(12, 0)),
                new DauerImpl(10),
                WiederholungType.WOECHENTLICH, 20, 2);

        listeFuerTermineImMonatA = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            listeFuerTermineImMonatA.put(
                    new DatumImpl(new TagImpl(2016, 1, 1+2*i), new UhrzeitImpl(12, 0)),
                    new TerminMitWiederholungImpl("TerminA",
                            new DatumImpl(new TagImpl(2016, 1, 1), new UhrzeitImpl(12, 0)),
                            new DauerImpl(10),
                            WiederholungType.TAEGLICH, 9, 2)
            );
        }

        listeFuerTermineInWoche = new HashMap<>();
        for (int i = 0; i <= 3; i++) {
            listeFuerTermineInWoche.put(
                    new DatumImpl(new TagImpl(2016, 1, 1+2*i), new UhrzeitImpl(12, 0)),
                    new TerminMitWiederholungImpl("TerminA",
                            new DatumImpl(new TagImpl(2016, 1, 1), new UhrzeitImpl(12, 0)),
                            new DauerImpl(10),
                            WiederholungType.TAEGLICH, 9, 2)
            );
        }

        listeFuerTermineAmTagA = new HashMap<>();
        listeFuerTermineAmTagA.put(
                new DatumImpl(new TagImpl(2016, 1, 1), new UhrzeitImpl(12, 0)),
                new TerminMitWiederholungImpl("TerminA",
                        new DatumImpl(new TagImpl(2016, 1, 1), new UhrzeitImpl(12, 0)),
                        new DauerImpl(10),
                        WiederholungType.TAEGLICH, 9, 2)
        );
    }

    @Test
    public void getWdh() {
        assertEquals(
                WiederholungType.WOECHENTLICH,
                terminB.getWdh().getType()
        );
        assertEquals(
                2,
                terminB.getWdh().getZyklus()
        );
        assertEquals(
                20,
                terminB.getWdh().anzahl()
        );
    }

    @Test
    public void termineInWoche() {

        assertEquals(
                "TerminA + 3 Wiederholungen mit korrekten Daten sind in der ersten Woche des Februar 2016",
                listeFuerTermineInWoche,
                terminA.termineIn(new WocheImpl(2016,1,1))
        );

    }

    @Test
    public void termineInMonat() {


        assertEquals(
                "TerminA + 8 Wiederholungen mit korrekten Daten sind im Februar 2016",
                listeFuerTermineImMonatA,
                terminA.termineIn(new MonatImpl(2016,1))
        );

    }

    @Test
    public void termineAn() {
        assertEquals(
                "TerminA findet ohne Wiederholungen am 1. Februar 2016 statt.",
                listeFuerTermineAmTagA,
                terminA.termineAn(new TagImpl(2016,1, 1))
        );
    }

    @Test
    public void termineFuer() {
        assertEquals(
                "TerminA + 3 Wiederholungen mit korrekten Daten sind in der ersten Woche des Februar 2016",
                listeFuerTermineInWoche,
                terminA.termineFuer(new WocheImpl(2016,1,1))
        );

        assertEquals(
                "TerminA + 8 Wiederholungen mit korrekten Daten sind im Februar 2016",
                listeFuerTermineImMonatA,
                terminA.termineFuer(new MonatImpl(2016,1))
        );

        assertEquals(
                "TerminA findet ohne Wiederholungen am 1. Februar 2016 statt.",
                listeFuerTermineAmTagA,
                terminA.termineFuer(new TagImpl(2016,1, 1))
        );
    }

    @Test
    public void equals() {
        assertEquals(
                "TerminB und der gegebene Termin müssen gleich sein.",
                new TerminMitWiederholungImpl("TerminB",
                        new DatumStub(2015, 1, 1, 1, 12, 0),
                        new DauerImpl(10),
                        WiederholungType.WOECHENTLICH, 20, 2),
                terminB
        );
    }

    @Test
    public void intervallIterator() {
        final int[] zaehler = {0};
        terminA.intervallIterator(5, 15).forEachRemaining(
                (datum) -> {
                    if (zaehler[0] > 4) {
                        fail(
                                "Es gibt nur Termine im Intervall 0-9. Daher gibt es von 5-15 auch nur 4 Durchläufe."
                        );
                    }
                    assertEquals(
                            "Die von IntervallIterator zurückgebenenen Datums-Werte müssen dem Wiederholungs-Muster entsprechen\n" +
                                    "und den richtigen Bereich abdecken [EQUALS ABHÄNGIG!].\n" +
                                    "Fehler bei Durchlauf "+zaehler[0]+".",
                            new DatumStub(2016, 11+zaehler[0]*2, 1, 42+zaehler[0]*2, 12, 0),
                            datum
                    );
                    zaehler[0]++;
                }
        );
    }

}