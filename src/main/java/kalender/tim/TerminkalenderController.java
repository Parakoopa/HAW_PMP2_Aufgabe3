package kalender.tim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kalender.interfaces.Datum;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TerminkalenderController {

    @FXML
    private Tab monatsansicht;

    @FXML
    private TableView<TerminImpl> monatsansicht_Tableview;

    @FXML
    private TableView<TerminImpl> tagesAnsicht_Tableview;

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

    private TextComponent label;

    TerminlistenGenerator terminlistenGenerator = new TerminlistenGenerator();
    TerminKalender terminKalender = terminlistenGenerator.erstelleTermine();

    protected void initialize() {
        TableColumn<TerminImpl,String> nameColumn = new TableColumn<>("Name");
        monatsansicht_Tableview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("datum"));

        //on date picker action -> get datum -> use datum to get terminlist ->add terminlsit to tableview columns

        datePicker.setOnAction(this::onDatePickerAction); 
        datePicker.setValue(LocalDate.now());
        datePicker.getValue().getYear();

       // MonatImpl monat = new MonatImpl(1992,2);
       // Termin[] termine = terminKalender.termineFuerMonat(monat, new DatumImpl());

    }

    private void onDatePickerAction(ActionEvent actionEvent) {
        label.setText(datePicker.getValue().toString());
      ObservableList<TerminImpl> tagesTermine = getTermine(terminKalender,datePicker.getValue());
        fuegeTermineInTabelle(tagesTermine, tagesAnsicht_Tab_Beschreibung);
    }


    private ObservableList<TerminImpl> getTermine(TerminKalender terminKalender, LocalDate date) {
        ObservableList<TerminImpl> obsTermine = FXCollections.observableArrayList();
        Tag tag = new TagImpl(date.getYear(), date.getDayOfYear());
        Map<Datum, List<Termin>> termine = terminKalender.termineFuerTag(tag);

        for(List<Termin> termin : termine.values()) {
            obsTermine.add((TerminImpl) termin);
        }
        return obsTermine;
    }

    private void fuegeTermineInTabelle( ObservableList<TerminImpl> termine, TableColumn tableColumn) {
        tagesAnsicht_Tableview.setItems(termine);

    }




}
