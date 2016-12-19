package kalender.marco.gui.controller.list;

import javafx.event.ActionEvent;
import kalender.TagImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by marco on 18.12.16.
 */
public class DayListController extends AbstractListController {

    /**
     * Verarbeite die Auswahl im Date-Picker.
     * - Setze Label und lade Termine für den gewählten Tag
     * @param date Das ausgewählte Datum
     */
    @Override
    protected void processDateChange(LocalDate date) {
        getLabel().setText(date.format(DateTimeFormatter.ofPattern("d. MMMM yyyy")));
        showEventsForDay(new TagImpl(date.getYear(), date.getDayOfYear()));
    }

    /**
     * Verarbeite Klicken des Buttons "Vorwärts"
     * - Gehe einen Tag vor
     */
    @Override
    protected void onForwardPressed(ActionEvent actionEvent) {
        getDatePicker().setValue(getDatePicker().getValue().plusDays(1));
    }

    /**
     * Verarbeite Klicken des Buttons "Zurück"
     * - Gehe einen Tag zurück
     */
    @Override
    protected void onBackPressed(ActionEvent actionEvent) {
        getDatePicker().setValue(getDatePicker().getValue().minusDays(1));
    }
}
