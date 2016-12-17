package kalender.tim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import kalender.WiederholungType;
import kalender.interfaces.Datum;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;
import kalender.interfaces.Woche;

public class TerminKalenderImpl extends Application implements TerminKalender {

    //TERMINE ERSTELLEN
    private TerminKalender terminKalender;
    private TerminImpl terminA;
    private TerminImpl terminB;
    private TerminMitWiederholungImpl terminMitWiederholungA;
    private TerminImpl terminA2;

    public void setUp() {
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
    }










    //GUI ERSTELLEN

    Stage primaryStage;

    Button monatsAnsicht = new Button("Monatsansicht anzeigen");
    Button tagesAnsicht = new Button("Tagesansicht anzeigen");

    TableView<Termin> terminTabelle;
    TableView monatsTabelle = new TableView();
    TableView tagesTabelle = new TableView();

    public  void TerminKalenderImpl(String[] args) { //static?
        setUp();
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tabellenauswahl");

        //dateColumn
        TableColumn<Termin,String> dateColumn = new TableColumn<>("Datum");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        terminTabelle = new TableView<>();
        terminTabelle.setItems(getTermin());
        terminTabelle.getColumns().addAll(dateColumn);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(terminTabelle);


        StackPane layout = new StackPane();
   //     layout.getChildren().add(monatsAnsicht);
   //     layout.getChildren().add(tagesAnsicht);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        monatsAnsicht.setOnAction(e -> System.out.println("monatsAnsicht();"));

    }

    public ObservableList<Termin> getTermin() {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        termine.add(terminA);
        termine.add(terminA2);
        termine.add(terminB);
        termine.add(terminMitWiederholungA);
        return termine;
    }


    //ToDo Monatsansicht
    public void displayMonatsansicht() {

    }

    //Alle Tage des Montats Tabellarisch darstellen
    //Tage mit Termine unterscheiden sich farblich von Tagen ohne Termine
    // Wird ein Tag angeklickt, so werden die Termine darunter aufgelistet
    // Termine können durch klciken gelöscht werden -> Verschwinden aus Tages und Monatsansicht
    //ToDo Tagesansicht
    public void displayTagesansicht() {

    }
    //Listen Termine neben ihren Zeiten auf
    //Einzelne Termine können selektiert und gelöscht werden
    //Wird ein Termin gelöscht, so wird er in der Monatsansicht und evtl in der Ansicht darunter gelöscht

























    private List<Termin> kalender = new ArrayList<Termin>();

	public boolean eintragen(Termin termin) {
	    try {
            this.kalender.add(termin);
            return true;
        }
        catch(NullPointerException NE ) {
            System.out.println("Can't add termin " + NE.getMessage() + " to Kalender!");
        }
        return false;
	}


	public void verschiebenAuf(Termin termin, Datum datum) {
        try {
            termin.verschiebeAuf(datum);
        }
        catch(NullPointerException NE ) {
            System.out.println("Termin" + NE.getMessage() + " can't be found!");
        }
	}


	public boolean terminLoeschen(Termin termin) {
        try {
            this.kalender.remove(termin);
        }
        catch(NullPointerException NE ) {
            System.out.println("Termin" + NE.getMessage() + " can't be found!");
        }
		return false;
	}


	public boolean enthaeltTermin(Termin termin) {
            return this.kalender.contains(termin);
	}


	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
		return kalender
                .stream()
                .filter(s -> s.getDatum().getTag() == tag)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

    public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
        return kalender
                .stream()
                .filter(s -> s.getDatum().getWoche() == woche)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}


	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
        return kalender
                .stream()
                .filter(s -> s.getDatum().getMonat() == monat)
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

}
