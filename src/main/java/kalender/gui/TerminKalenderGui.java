package kalender.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * JavaFX GUI Anwendung für Praktikum 4
 */
public class TerminKalenderGui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("/abgabe.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("/abgabe.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Toller Terminkalender 3.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
