package kalender.marco.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import kalender.interfaces.TerminKalender;
import kalender.marco.gui.controller.list.DayListController;
import kalender.marco.gui.controller.list.MonthListController;

/**
 * Haupt-Klasse des Controllers für die GUI Anwendung.
 */
public class TerminKalenderController {

    /* TAGESANSICHT */

    @FXML
    private Label dayLabel;

    @FXML
    private DatePicker dayDatePicker;

    @FXML
    private TableView<TerminEintrag> dayEvents;

    @FXML
    private Button dayForward;

    @FXML
    private Button dayBack;

    /* MONATSANSICHT */

    @FXML
    private Label monthLabel;

    @FXML
    private DatePicker monthDatePicker;

    @FXML
    private TableView<DatumEintrag> monthDateTable;

    @FXML
    private TableView<TerminEintrag> monthEvents;

    @FXML
    private Button monthForward;

    @FXML
    private Button monthBack;

    /**
     * Lade die "Untercontroller"
     */
    @FXML
    protected void initialize() {
        TerminKalender kalender = new TerminKalenderFactory().create();
        DayListController dayListController = new DayListController();
        dayListController.setKalender(kalender);
        dayListController.setDateControls(dayDatePicker, dayForward, dayBack);
        dayListController.setLabel(dayLabel);
        dayListController.setEventsTable(dayEvents);
        dayListController.initialize();

        MonthListController monthListController = new MonthListController();
        monthListController.setKalender(kalender);
        monthListController.setDateControls(monthDatePicker, monthForward, monthBack);
        monthListController.setLabel(monthLabel);
        monthListController.setEventsTable(monthEvents);
        monthListController.setDateTable(monthDateTable);
        monthListController.initialize();
    }

}
