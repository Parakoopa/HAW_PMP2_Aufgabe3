package kalender.marco.gui;

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
        TabPane root = FXMLLoader.load(getClass().getResource("/marco.fxml"));
        Scene scene = new Scene(root, 800,600);
        scene.getStylesheets().add(
                getClass().getResource("/marco.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Toller Terminkalender 2.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
