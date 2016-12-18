package kalender.tim;


import kalender.WiederholungType;
import kalender.interfaces.TerminKalender;

public class TerminlistenGenerator {

    private TerminKalender terminKalender;
    private TerminImpl terminA;
    private TerminImpl terminB;
    private TerminMitWiederholungImpl terminMitWiederholungA;
    private TerminImpl terminA2;

    public TerminKalender erstelleTermine() {
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
        terminKalender.eintragen(terminA);
        terminKalender.eintragen(terminA2);
        terminKalender.eintragen(terminB);
        terminKalender.eintragen(terminMitWiederholungA);

        return terminKalender;
    }
}
