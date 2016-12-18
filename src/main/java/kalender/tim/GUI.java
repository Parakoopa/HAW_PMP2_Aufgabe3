package kalender.tim;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kalender.interfaces.TerminKalender;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            TabPane root = (TabPane) FXMLLoader.load(getClass().getResource(
                    "/GUI_Tim.fxml"));
            Scene scene = new Scene(root);
       //     scene.getStylesheets().add(
      //              getClass().getResource("button.css").toExternalForm());
            primaryStage.setScene(scene);
     //       primaryStage.setTitle("ButtonSample");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}