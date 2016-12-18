package kalender.marco.gui.controller.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kalender.DatumImpl;
import kalender.MonatImpl;
import kalender.TagImpl;
import kalender.interfaces.Datum;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by marco on 18.12.16.
 */
public class MonthListController extends AbstractListController {
    private TableView<DatumEintrag> dateTable;

    // GETTER UND SETTER zur Initialisierung

    public void setDateTable(TableView<DatumEintrag> dateTable) {
        this.dateTable = dateTable;
    }

    public TableView<DatumEintrag> getDateTable() {
        return dateTable;
    }
    // Ende ... GETTER UND SETTER zur Initialisierung

    /**
     * Initiiere Elemente der Monatsansicht
     * Dann:
     * Initiiere gemeinsame Elemente der Stage (super-Aufruf)
     */
    @Override
    public void initialize() {
        // Nur ein Tag kann gleichzeitig gewählt sein
        dateTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        // Ändere Tag für Termin-Ansicht bei Auswahl eines neuen Tages
        dateTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) showEventsForDay(newSelection.getDatum().getTag());
        });

        // Styles für Tage in der Datums-Ansicht:
        // tc-today (rot) für heute
        // tc-events (blau) für Tage mit Termin
        // Magenta wenn beides, siehe css Datei!
        dateTable.setRowFactory(tv -> new TableRow<DatumEintrag>() {
            @Override
            public void updateItem(DatumEintrag item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) return;
                getStyleClass().remove("tc-today");
                getStyleClass().remove("tc-events");
                if (item.getDatum().getJahr() == Calendar.getInstance().get(Calendar.YEAR) &&
                    item.getDatum().getTagImJahr() == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) {
                    getStyleClass().add("tc-today");
                } if (dayContainsEvents(item.getDatum().getTag())) {
                    getStyleClass().add("tc-events");
                }
            }
        });

        // Initiiere Spalten für Datums-Tabelle
        dateTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("tag"));
        dateTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("monat"));
        dateTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("jahr"));

        super.initialize();
    }

    /**
     * Enthält der gegebene Tag Termine?
     */
    private boolean dayContainsEvents(Tag tag) {
        return getKalender().termineFuerTag(tag).size() > 0;
    }

    /**
     * Verarbeite die Auswahl im Date-Picker.
     * - Setze Monat für Monatsanischt und setze den Label.
     * - Scrolle zu aktuellen Tag und wähle ihn aus
     * @param date Das ausgewählte Datum
     */
    @Override
    protected void processDateChange(LocalDate date) {
        getLabel().setText(date.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        createMonthList(new MonatImpl(date.getYear(), date.getMonth().getValue()-1));
        dateTable.getSelectionModel().select(date.getDayOfMonth()-1);
        dateTable.scrollTo(date.getDayOfMonth()-1);
    }

    /**
     * Verarbeite Klicken des Buttons "Vorwärts"
     * - Gehe einen Monat vor
     */
    @Override
    protected void onForwardPressed(ActionEvent actionEvent) {
        getDatePicker().setValue(getDatePicker().getValue().plusMonths(1));
    }

    /**
     * Verarbeite Klicken des Buttons "Zurück"
     * - Gehe einen Monat zurück
     */
    @Override
    protected void onBackPressed(ActionEvent actionEvent) {
        getDatePicker().setValue(getDatePicker().getValue().minusMonths(1));
    }

    /**
     * Erstelle eine Liste von Tagen im übergebenen Monat
     * @param monat
     */
    private void createMonthList(Monat monat) {
        Datum ende = monat.getEnde();
        ObservableList<DatumEintrag> obsList = FXCollections.observableArrayList();
        for (int i = 1; i <= ende.getTagImMonat(); i++) {
            obsList.add(new DatumEintrag(
                    new DatumImpl(new TagImpl(monat.getJahr(), monat.getMonat(), i))
            ));
        }
        dateTable.setItems(obsList);
    }
}
