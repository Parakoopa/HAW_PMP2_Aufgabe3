package kalender.gui;

import kalender.*;
import kalender.interfaces.TerminKalender;

/**
 * Proxy Klasse (Factory) um einen Beispielkalender
 * zu erstellen.
 */
public class TerminKalenderFactory {
    public TerminKalender create() {
        TerminKalenderImpl kalender = new TerminKalenderImpl();
        kalender.eintragen(new TerminImpl(
                "Bla 123",
                new DatumImpl(new TagImpl(2016, 2, 3), new UhrzeitImpl(12, 32)),
                new DauerImpl(30)
        ));
        kalender.eintragen(new TerminImpl(
                "Bla 456",
                new DatumImpl(new TagImpl(2016, 2, 3), new UhrzeitImpl(11, 12)),
                new DauerImpl(45)
        ));
        kalender.eintragen(new TerminImpl(
                "Anderer Tag",
                new DatumImpl(new TagImpl(2016, 2, 4), new UhrzeitImpl(1, 12)),
                new DauerImpl(12)
        ));
        kalender.eintragen(new TerminImpl(
                "Anderer Monat",
                new DatumImpl(new TagImpl(2016, 4, 3), new UhrzeitImpl(1, 0)),
                new DauerImpl(55)
        ));
        kalender.eintragen(new TerminMitWiederholungImpl(
                "Adventskalender öffnen",
                new DatumImpl(new TagImpl(2016, 11, 1), new UhrzeitImpl(8, 0)),
                new DauerImpl(5),
                WiederholungType.TAEGLICH,
                23, 1
        ));
        return kalender;
    }

}
