package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HomeController class
 */
public class HomeController {
    /**
     * Login Controller method. Lambda expression.
     */
    LoginController.LogActivity logActivity = () -> "login_activity.txt";
    /**
     * Appointment Button.
     */
    @FXML
    private Button AppointmentsButton;
    /**
     * Customers Button.
     */
    @FXML
    private Button CustomersButton;
    /**
     * Header Label.
     */
    @FXML
    private Label Header;
    /**
     * Logout Button.
     */
    @FXML
    private Button LogoutButton;
    /**
     * Reports Button.
     */
    @FXML
    private Button ReportsButton;

    /**
     *
     * @param event loads appointment screen
     */

    @FXML
    void Appointments(ActionEvent event) {
        try {



                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Appointments");
                stage.show();

        }   catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Load Screen Error.");
            alert.showAndWait();

        }
        }

        /**
     *
     * @param event loads customer screen
     */

    @FXML
    void Customers(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Customers");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Load Screen Error.");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param event loads login screen when logged out.
     */

    @FXML
    void Logout(ActionEvent event) {
        createFile();
        logoutSuccess();

        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Load Screen Error");
            alert.showAndWait();
        }
    }

    /**
     *  logs successful logout
     */
    //logs successful logout.
    private void logoutSuccess() {
        try {
            FileWriter fileWriter = new FileWriter(logActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Successful Logout: " + simpleDateFormat.format(date) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates log activity file
     */
    //creates log activity file.
    private void createFile() {
        try {
            File newfile = new File(logActivity.getFileName());
            if (newfile.createNewFile()) {
                System.out.println("File created:" + newfile.getName());
            } else {
                System.out.println("File already exists. Path location: " + newfile.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event loads reports screen
     */
    //navigates to reports screen.
    @FXML
    void Reports(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Reports");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Load Screen Error.");
            alert.showAndWait();
        }
    }
}




