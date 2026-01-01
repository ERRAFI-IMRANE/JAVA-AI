package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/gui/main_view.fxml")
        );

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(
        	    getClass().getResource("/style.css").toExternalForm()

        );

        stage.setTitle("IA SPAM DETECTOR");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
