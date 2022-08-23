package Main;

import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application {
    /**
     *
     * @param primaryStage
     * @throws Exception
     * sets the stage.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            primaryStage.setTitle("Scheduling Application");
            primaryStage.setScene(new Scene(root, 700, 700));
            primaryStage.show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Load Screen Error");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
