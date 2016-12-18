package kalender.tim;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kalender.WiederholungType;
import kalender.interfaces.TerminKalender;

public class OLD extends Application  {






    //OLD ERSTELLEN

    Stage window;
    Scene selectionScene, monatsAnsicht, tagesAnsicht;

    Button button_to_MonatAnsicht = new Button("Monatsansicht anzeigen");
    Button button_to_TagesAnsicht = new Button("Tagesansicht anzeigen");

    TableView<TerminImpl> terminTabelle, datumsTabelle;
    TableView monatsTabelle = new TableView();
    TableView tagesTabelle = new TableView();

    public  void TerminKalenderImpl(String[] args) { //static?
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Tabellenauswahl");

        //dateColumn
        TableColumn<TerminImpl,String> dateColumn = new TableColumn<>("Datum");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        datumsTabelle = new TableView<>();
     //   datumsTabelle.setItems(getTermin());
        datumsTabelle.getColumns().addAll(dateColumn);


        //nameColumn
        TableColumn<TerminImpl,String> nameColumn = new TableColumn<>("Name");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("bescheibung"));

        //durationColumn
        TableColumn<TerminImpl,String> durationColumn = new TableColumn<>("Dauer");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dauer"));

        terminTabelle = new TableView<>();
  //      terminTabelle.setItems(getTermin());
        terminTabelle.getColumns().addAll(nameColumn, durationColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(datumsTabelle,terminTabelle);

        StackPane layout = new StackPane();
        layout.getChildren().add(button_to_MonatAnsicht);
        //     layout.getChildren().add(button_to_TagesAnsicht);

        selectionScene = new Scene(layout, 300, 250);
        monatsAnsicht = new Scene(vBox);
        //tagesAnsicht = new Scene(vBox);
        window.setScene(selectionScene);
        window.show();

        button_to_MonatAnsicht.setOnAction(e -> window.setScene(monatsAnsicht));
        //button_to_TagesAnsicht.setOnAction(e -> window.setScene(monatsAnsicht));
    }
/*
    private ObservableList<TerminImpl> getTermin() {
        ObservableList<TerminImpl> termine = FXCollections.observableArrayList();
        termine.add(terminA);
        termine.add(terminA2);
        termine.add(terminB);
        termine.add(terminMitWiederholungA);
        return termine;
    }
*/


    //ToDo Monatsansicht
    private void displayMonatsansicht() {
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





}
