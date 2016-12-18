package kalender.marco.gui.controller.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kalender.TagImpl;
import kalender.interfaces.Datum;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;
import kalender.marco.gui.controller.TerminEintrag;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Ein abstrakter Controller für
 * Monats- und Tagesansicht.
 * Regelt die Logik die bei beiden Ansichten gleich ist.
 */
public abstract class AbstractListController {

    private DatePicker datePicker;
    private Label label;
    private TableView<TerminEintrag> eventsTable;
    private TerminKalender kalender;

    private Button forwardButton;
    private Button backButton;

    // GETTER UND SETTER zur Initialisierung

    public void setKalender(TerminKalender kalender) {
        this.kalender = kalender;
    }

    protected TerminKalender getKalender() {
        return kalender;
    }

    public void setDateControls(DatePicker datePicker, Button forward, Button back) {
        this.datePicker = datePicker;
        this.forwardButton = forward;
        this.backButton = back;
    }

    protected DatePicker getDatePicker() {
        return datePicker;
    }

    protected Button getForwardButton() {
        return forwardButton;
    }

    protected Button getBackButton() {
        return backButton;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    protected Label getLabel() {
        return label;
    }


    public void setEventsTable(TableView<TerminEintrag> eventsTable) {
        this.eventsTable = eventsTable;
    }

    protected TableView<TerminEintrag> getEventsTable() {
        return eventsTable;
    }

    // ENDE ... GETTER UND SETTER zur Initialisierung

    /**
     * Initiiere gemeinsame Elemente der Stage.
     */
    public void initialize() {
        // Makiere Terminauswahl als mehrfach auswählbar
        eventsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        // Initiiere Event-Handler für die Datums-Auswahl
        datePicker.setOnAction(this::onDatePickerAction);
        // Setze Datums-Auswahl auf heute
        datePicker.setValue(LocalDate.now());
        // Trigger manually. For some reason setting the value doesn't work.
        datePicker.getOnAction().handle(new ActionEvent());

        // Initiiere Event-Handler für die Buttons
        forwardButton.setOnAction(this::onForwardPressed);
        backButton.setOnAction(this::onBackPressed);

        // Initiiere Event-Handler für die Termin-Tabelle (löschen)
        eventsTable.setOnKeyPressed(this::eventsTableOnKeyPressed);

        // Initiiere Tabellen-Spalten der Termin-Tabelle
        eventsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("datum"));
        eventsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
        eventsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("wiederholtVon"));
    }

    /**
     * Event handler für den Date Picker
     * @param actionEvent
     */
    private void onDatePickerAction(ActionEvent actionEvent) {
        processDateChange(datePicker.getValue());
    }

    /**
     * Event handler für Keypresses in der Tabelle
     * Löscht ausgewählte Zeilen aus der Tabelle und dem TerminKalender.
     * @param keyEvent
     */
    private void eventsTableOnKeyPressed(KeyEvent keyEvent) {
        ObservableList<TerminEintrag> selectedItems = eventsTable.getSelectionModel().getSelectedItems();
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            for (TerminEintrag terminE : selectedItems) {
                System.out.println("Lösche Termin "+terminE.getTermin());
                kalender.terminLoeschen(terminE.getTermin());
            }
            eventsTable.getItems().removeAll(selectedItems);
        }
    }

    /**
     * Zeige die Events des übergebenen Tages in der Terminansicht.
     * @param tag
     */
    protected void showEventsForDay(Tag tag) {
        System.out.println("Lade Termine für "+tag);
        Map<Datum, List<Termin>> map = kalender.termineFuerTag(tag);
        ObservableList<TerminEintrag> obsList = FXCollections.observableArrayList();

        // TODO: Könnte man auch schöner mit Streams machen, aber so ist es erstmal übersichtlicher
        for (Map.Entry<Datum, List<Termin>> entry : map.entrySet()) {
            for (Termin termin : entry.getValue()) {
                obsList.add(new TerminEintrag(entry.getKey(), termin));
            }
        }

        // Sortiere sicherheitshalber nochmal nach Datum. @see TerminEintrag::compareTo
        obsList.sort(Comparable::compareTo);
        eventsTable.setItems(obsList);
    }

    /**
     * Verarbeite die Auswahl im Date-Picker.
     * @param date Das ausgewählte Datum
     */
    protected abstract void processDateChange(LocalDate date);

    /**
     * Verarbeite Klicken des Buttons "Vorwärts"
     */
    protected abstract void onForwardPressed(ActionEvent actionEvent);

    /**
     * Verarbeite Klicken des Buttons "Zurück"
     */
    protected abstract void onBackPressed(ActionEvent actionEvent);
}
