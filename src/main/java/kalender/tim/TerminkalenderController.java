package kalender.tim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kalender.interfaces.Datum;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TerminkalenderController {

    @FXML
    private Tab monatsansicht;

    @FXML
    private TableView<TerminImpl> monatsansicht_Tableview;

    @FXML
    private TableView<?> tagesAnsicht_Tableview;

    @FXML
    private TableColumn<Termin, String> monatsansicht_Tab_Datum;

    @FXML
    private TitledPane tagesAnsicht_Tab;

    @FXML
    private TableColumn<Termin, String> tagesAnsicht_Tab_Datum;

    @FXML
    private TableColumn<Termin, String> tagesAnsicht_Tab_Beschreibung;

    @FXML
    private Tab tagesAnsicht;

    @FXML
    private TableColumn<Termin, String> monatsansicht_Tab_Datum1;

    @FXML
    private DatePicker datePicker;


    TerminlistenGenerator terminlistenGenerator = new TerminlistenGenerator();
    TerminKalender terminKalender = terminlistenGenerator.erstelleTermine();

    protected void initialize() {
        TableColumn<TerminImpl,String> nameColumn = new TableColumn<>("Name");
        monatsansicht_Tableview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("datum"));

        monatsansicht_Tableview.setItems(getTermine(terminKalender));

        //datePicker.setOnAction(this::onDatePickerAction); //ToDo Remove error
        datePicker.setValue(LocalDate.now());
        datePicker.getValue().getYear();

       // MonatImpl monat = new MonatImpl(1992,2);
       // Termin[] termine = terminKalender.termineFuerMonat(monat, new DatumImpl());

    }

    private ObservableList<TerminImpl> getTermine(TerminKalender terminKalender) {
        ObservableList<TerminImpl> obsTermine = FXCollections.observableArrayList();
        Map<Datum, List<Termin>> termine = terminKalender.termineFuerTag(new TagImpl(1, 1));

        for(List<Termin> termin : termine.values()) {
            obsTermine.add((TerminImpl) termin);
        }
        return obsTermine;
    }

    private void fuegeTermineInTabelle(Termin[] termine, TableColumn tableColumn) {
        for(Termin termin : termine) {
        //    tableColumn.setCellValueFactory("stuff");
        }

    }




}
