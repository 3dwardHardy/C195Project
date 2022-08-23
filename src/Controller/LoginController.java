package Controller;



import DB.ApptQuery;
import DB.UsersQuery;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Login Controller class
 */
public class LoginController implements Initializable {

    /***
     * Lambda expression for logActivity. Used for ease of being shorter.
     */
    LogActivity logActivity = () -> "login_activity.txt";
    /**
     * Resource Bundle.
     */
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());

    /**
     * Cancel Button.
     */
    @FXML
    private Button CancelButton;
    /**
     * Header Label.
     */
    @FXML
    private Label Header;
    /**
     * Location Text Field.
     */
    @FXML
    private Label LocationField;
    /**
     * Location Label.
     */
    @FXML
    private Label LocationLabel;
    /**
     * Login Button.
     */
    @FXML
    private Button LoginButton;
    /**
     * Password label.
     */
    @FXML
    private Label PasswordLabel;
    /**
     * Password PasswordField.
     */
    @FXML
    private PasswordField PasswordTextField;
    /**
     * Time zone field label.
     */
    @FXML
    private Label TimeZoneField;
    /**
     * time zone label.
     */
    @FXML
    private Label TimeZoneLabel;
    /**
     * title label.
     */
    @FXML
    private Label Title;
    /**
     * Username label.
     */
    @FXML
    private Label UsernameLabel;
    /**
     * Username Text Field.
     */
    @FXML
    private TextField UsernameTextField;


    /**
     *
     * @param location
     * @param resources
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resources = ResourceBundle.getBundle("Language/language", Locale.getDefault());
            Header.setText(resources.getString("header"));
            Title.setText(resources.getString("title"));
            UsernameLabel.setText(resources.getString("username"));
            PasswordLabel.setText(resources.getString("password"));
            LocationLabel.setText(resources.getString("location"));
            LocationField.setText(resources.getString("country"));
            TimeZoneLabel.setText(resources.getString("timezone"));
            TimeZoneField.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            LoginButton.setText(resources.getString("login"));
            CancelButton.setText(resources.getString("cancel"));
        } catch (MissingResourceException e) {
            System.out.println("Missing resource");
        }
    }

    /**
     *
     * @param event
     * login, creates log file is none exists. Informs of successful login.
     */

    @FXML
    void Login(ActionEvent event) {
        validateUsernameNotEmpty(UsernameTextField.getText());
        validatePasswordNotEmpty(PasswordTextField.getText());

        createFile();

        try {
            boolean valid = UsersQuery.checkUsernamePassword(UsernameTextField.getText(), PasswordTextField.getText());
            if (valid) {
                loginSuccess();

                try {
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Home");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                loginFailure();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates file for log activity if none exists.
     */

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
     * @param username validates username is not empty.
     *
     */

    private void validateUsernameNotEmpty(String username) {
        if (username.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("usernameRequired"));
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * @param username validates password is not empty.
     */

    private void validatePasswordNotEmpty(String username) {
        if (username.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("passwordRequired"));
                alert.showAndWait();
            }
        }
    }

    /**
     * Alerts to upcoming appointments
     * Lambda expression for filterAppointment, used because of it being a short expression.
     */
    private void alertAppointment() throws SQLException {


        FilteredList<Appointment> filteredList = new FilteredList<>(ApptQuery.getAppointments());

        //  Lambda expression for filterAppointment.
        /**
         * Lambda expression for filterAppointment.
         */
        filteredList.setPredicate(row -> {
            LocalDateTime dateRows = LocalDateTime.parse(row.getStartTimeInfo().toString());
            return dateRows.isAfter(LocalDateTime.now().minusMinutes(1)) && dateRows.isBefore(LocalDateTime.now().plusMinutes(15));
        });
        if (filteredList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resourceBundle.getString("appointmentAlert"));
            alert.setContentText(resourceBundle.getString("noUpcomingAppointments"));
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resourceBundle.getString("appointmentAlert"));
            alert.setContentText(resourceBundle.getString("lessThanFifteenMin"));
            alert.showAndWait();
        }
    }

    /**
     * successful login generates log activity.
     */

    private void loginSuccess() throws SQLException {
        alertAppointment();
        try {
            FileWriter fileWriter = new FileWriter(logActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Successful Login: Username=" + UsernameTextField.getText() + " Password=" + PasswordTextField.getText() + " Timestamp: " + simpleDateFormat.format(date) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * unsuccessful login generates log activity.
     */

    private void incorrectLogPass() {
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("error"));
            alert.setContentText(resourceBundle.getString("incorrectUsernamePassword"));
            alert.showAndWait();
        }
    }

    /**
     * unsuccessful login generates log activity.
     */

    private void loginFailure() {
        incorrectLogPass();
        try {
            FileWriter fileWriter = new FileWriter(logActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Failed Login: Username=" + UsernameTextField.getText() + " Password=" + PasswordTextField.getText() + " Timestamp: " + simpleDateFormat.format(date) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * cancels login and closes window.
     *
     * @param event
     */

    @FXML
    void Cancel(javafx.event.ActionEvent event) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, resourceBundle.getString("cancelError"));
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }
}

    /***
     * Lambda expression for Log Activity.
     */

    @FunctionalInterface
    public interface LogActivity {
        String getFileName();
    }
}
