package kalender.tim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;

public class TerminkalenderController {

    @FXML
    private Tab monatsansicht;

    @FXML
    private TableColumn<?, ?> monatsansicht_Tab_Datum;

    @FXML
    private TitledPane tagesAnsicht_Tab;

    @FXML
    private TableColumn<?, ?> tagesAnsicht_Tab_Datum;

    @FXML
    private TableColumn<?, ?> tagesAnsicht_Tab_Beschreibung;

    @FXML
    private Tab tagesAnsicht;

    @FXML
    private TableColumn<?, ?> monatsansicht_Tab_Datum1;

    TerminlistenGenerator terminlistenGenerator = new TerminlistenGenerator();
    TerminKalender terminKalender = terminlistenGenerator.erstelleTermine();

    protected void initialize() {
        MonatImpl monat = new MonatImpl(1992,2);
        Termin[] termine = terminKalender.termineFuerMonat(monat, new DatumImpl());
        fuegeTermineInTabelle(terminKalender.termineFuerMonat(new MonatImpl(1992,1)), monatsansicht_Tab_Datum);
    }
    private void fuegeTermineInTabelle(Termin[] termine, TableColumn tableColumn) {
        for(Termin termin : termine) {
        //    tableColumn.setCellValueFactory("stuff");
        }
    }


}
