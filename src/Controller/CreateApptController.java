package Controller;

import DB.ApptQuery;
import DB.ContactsQuery;
import DB.CustomersQuery;
import DB.UsersQuery;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Create appointment controller
 */
public class CreateApptController implements Initializable {
    /**
     * Appointment ID label.
     */
    @FXML
    private Label AppointmentIDLabel;
    /**
     * Appointment ID text field.
     */
    @FXML
    private TextField AppointmentIDTextField;
    /**
     * Cancel button.
     */
    @FXML
    private Button CancelButton;
    /**
     * Contact ComboBox.
     */
    @FXML
    private ComboBox<String> ContactCombo;
    /**
     * Contact Label.
     */
    @FXML
    private Label ContactLabel;
    /**
     * Customer ID ComboBox.
     */
    @FXML
    private ComboBox<Integer> CustomerIDCombo;
    /**
     * Customer ID Label.
     */
    @FXML
    private Label CustomerIDLabel;
    /**
     * Description Label.
     */
    @FXML
    private Label DescriptionLabel;
    /**
     * Description TextField.
     */
    @FXML
    private TextField DescriptionTextField;
    /**
     * End Date DatePicker.
     */
    @FXML
    private DatePicker EndDateDatePicker;
    /**
     * End Date Label.
     */
    @FXML
    private Label EndDateLabel;
    /**
     * End Time ComboBox.
     */
    @FXML
    private ComboBox<String> EndTimeCombo;
    /**
     * End Time Label.
     */
    @FXML
    private Label EndTimeLabel;
    /**
     * Header Label.
     */
    @FXML
    private Label Header;
    /**
     * Home Button.
     */
    @FXML
    private Button HomeButton;
    /**
     * Location Label.
     */
    @FXML
    private Label LocationLabel;
    /**
     * Location TextField.
     */
    @FXML
    private TextField LocationTextField;
    /**
     * Save Button.
     */
    @FXML
    private Button SaveButton;
    /**
     * Start Date DatePicker.
     */
    @FXML
    private DatePicker StartDateDatePicker;
    /**
     * Start Date Label.
     */
    @FXML
    private Label StartDateLabel;
    /**
     * Start Time ComboBox.
     */
    @FXML
    private ComboBox<String> StartTimeCombo;
    /**
     * Start Time Label.
     */
    @FXML
    private Label StartTimeLabel;
    /**
     * Title Label.
     */
    @FXML
    private Label TitleLabel;
    /**
     * Title TextField.
     */
    @FXML
    private TextField TitleTextField;
    /**
     * Type ComboBox.
     */
    @FXML
    private ComboBox<String> TypeCombo;
    /**
     * Type Label.
     */
    @FXML
    private Label TypeLabel;
    /**
     * User ID ComboBox.
     */
    @FXML
    private ComboBox<Integer> UserIDCombo;
    /**
     * User ID Label.
     */
    @FXML
    private Label UserIDLabel;

    /**
     *
     * @param event
     * cancels the creation of an appointment, and returns to the main screen.
     */

    @FXML
    void Cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Return to appointments?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Load Screen Error.");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * @param event
     * returns to the main screen.
     */
    @FXML
    void Home(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Home");
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
     * @param event
     *
     */
    @FXML
    void PickEndDate(ActionEvent event) {

    }

    /**
     *
     * @param event
     */
    @FXML
    void PickStartDate(ActionEvent event) {

    }

    /**
     *
     * @param title
     * @param description
     * @param location
     * @param appointmentId
     * @return
     *
     * validates the appointment information.
     */

    private boolean validateAppointment(String title, String description, String location, String appointmentId) {
        /***
         * Validate the appointment fields.
         */
        if (ContactCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Contact required.");
            alert.showAndWait();
            return false;
        }
        if (title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Title required");
            alert.showAndWait();
            return false;
        }
        if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Description required");
            alert.showAndWait();
            return false;
        }
        if (location.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Location required");
            alert.showAndWait();
            return false;
        }
        if (appointmentId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment ID required");
            alert.showAndWait();
            return false;
        }
        if (TypeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Type required.");
            alert.setTitle("Error");
            alert.showAndWait();
            return false;
        }
        if (StartDateDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Start date required.");
            alert.showAndWait();
            return false;
        }
        if (StartTimeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start time required.");
            alert.showAndWait();
            return false;
        }
        if (EndDateDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End date required.");
            alert.showAndWait();
            return false;
        }
        if (EndDateDatePicker.getValue().isBefore(StartDateDatePicker.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End date must be after start date.");
            alert.showAndWait();
            return false;
        }
        if (EndTimeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End time required.");
            alert.showAndWait();
            return false;
        }
        if (CustomerIDCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Customer ID required.");
            alert.showAndWait();
            return false;
        }
        if (UserIDCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User ID required.");
            alert.showAndWait();
            return false;
        }

        LocalTime startTime = LocalTime.parse(StartTimeCombo.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(EndTimeCombo.getSelectionModel().getSelectedItem());

        if (endTime.isBefore(startTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment start time must be before end time.");
            alert.showAndWait();
            return false;
        }

        LocalDate startDate = StartDateDatePicker.getValue();
        LocalDate endDate = EndDateDatePicker.getValue();

        if (!startDate.equals(endDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment must start and end on the same date.");
            alert.showAndWait();
            return false;
        }

        LocalDateTime selectedStart = startDate.atTime(startTime);
        LocalDateTime selectedEnd = endDate.atTime(endTime);

        LocalDateTime proposedAppointmentStart;
        LocalDateTime proposedAppointmentEnd;
        LocalDate proposedDate;
        /**
         * Check if the appointment is being edited.
         */
        try {
            ObservableList<Appointment> appointments = ApptQuery.getAppointmentsByCustomerID(CustomerIDCombo.getSelectionModel().getSelectedItem());
            for (Appointment appointment : appointments) {
                proposedAppointmentStart = appointment.getStartDateInfo().atTime(appointment.getStartTimeInfo().toLocalTime());
                proposedAppointmentEnd = appointment.getEndDateInfo().atTime(appointment.getEndTimeInfo().toLocalTime());
                proposedDate = appointment.getStartDateInfo();
                if (startDate.equals(proposedDate)) {
                    if (selectedStart.equals(proposedAppointmentStart) || selectedStart.isAfter(proposedAppointmentStart) && selectedStart.isEqual(proposedAppointmentEnd) || selectedStart.isBefore(proposedAppointmentEnd)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Appointments cannot overlap with existing customer appointments.");
                        alert.showAndWait();
                        return false;
                    } else if (selectedEnd.equals(proposedAppointmentStart) || selectedEnd.isAfter(proposedAppointmentStart) && selectedEnd.isEqual(proposedAppointmentEnd) || selectedEnd.isBefore(proposedAppointmentEnd)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Appointments cannot overlap with existing customer appointments.");
                        alert.showAndWait();
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ZonedDateTime startDateTimeConversion = convertToEST(LocalDateTime.of(StartDateDatePicker.getValue(), LocalTime.parse(StartTimeCombo.getSelectionModel().getSelectedItem())));
        ZonedDateTime endDateTimeConversion = convertToEST(LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeCombo.getSelectionModel().getSelectedItem())));

        if (startDateTimeConversion.toLocalTime().isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be made within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (endDateTimeConversion.toLocalTime().isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be made within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (startDateTimeConversion.toLocalTime().isBefore(LocalTime.of(8, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be made within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (endDateTimeConversion.toLocalTime().isBefore(LocalTime.of(8, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be made within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     *
     * @param time
     * @return
     * Converts time to est.
     */
    private ZonedDateTime convertToEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }

    /**
     *
     * @param event saves info
     * Saves appointment info to database
     */

    @FXML
    void Save(ActionEvent event) {
        boolean valid = validateAppointment(
                TitleTextField.getText(),
                DescriptionTextField.getText(),
                LocationTextField.getText(),
                AppointmentIDTextField.getText()
        );

        if (valid) {
            try {

                boolean success = ApptQuery.createAppointment(
                        ContactCombo.getSelectionModel().getSelectedItem(),
                        TitleTextField.getText(),
                        DescriptionTextField.getText(),
                        LocationTextField.getText(),
                        TypeCombo.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of(StartDateDatePicker.getValue(), LocalTime.parse(StartTimeCombo.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeCombo.getSelectionModel().getSelectedItem())), CustomerIDCombo.getSelectionModel().getSelectedItem(),
                        UserIDCombo.getSelectionModel().getSelectedItem());

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully created new appointment");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && (result.get() ==  ButtonType.OK)) {
                        try {
                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("Load Screen Error.");
                            alert.showAndWait();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save new appointment");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param event
     * selects time for end of appointment.
     */
    @FXML
    void SelectEndTime(ActionEvent event) {

    }

    /**
     *
     * @param event
     * selects time for start of appointment.
     */
    @FXML
    void SelectStartTime(ActionEvent event) {

    }

    /**
     *
     * @param event select times
     * selects type of appointment
     */
    @FXML
    void SelectType(ActionEvent event) {

    }

    /***
     *  * @param event
     *  populates time combo box.
     */

    private void populateTimeComboBoxes() {
        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(23, 0);

        time.add(startTime.toString());
        while (startTime.isBefore(endTime)) {
            startTime = startTime.plusMinutes(15);
            time.add(startTime.toString());

        }
        StartTimeCombo.setItems(time);
        EndTimeCombo.setItems(time);
    }

    /**
     * * @param event select contact
     * populates contact combo box.
     */

    private void populateContactComboBox() {
        ObservableList<String> contactComboList = FXCollections.observableArrayList();

        try {
            ObservableList<Contact> contacts = ContactsQuery.getContacts();
            if (contacts != null) {
                for (Contact contact: contacts) {
                        contactComboList.add(contact.getContactNameInfo());
                    }
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContactCombo.setItems(contactComboList);
    }

    /**
     * * @param event customer ID
     */
    //populates customer ID combo box.
    private void populateCustomerIDComboBox() {
        ObservableList<Integer> customerIDComboList = FXCollections.observableArrayList();

        try {
            ObservableList<Customer> customers = CustomersQuery.getCustomer();
            if (customers != null) {
                for (Customer customer: customers) {
                    customerIDComboList.add(customer.getCustomerIdInfo());
                    }
                }
            } catch (SQLException e) {
            e.printStackTrace();

        }
        CustomerIDCombo.setItems(customerIDComboList);
    }

    /**
     * * @param event fill user id
     * populates user id combo box.
     */

    private void populateUserIDComboBox() {
        ObservableList<Integer> userIDComboList = FXCollections.observableArrayList();

        try {
            ObservableList<User> users = UsersQuery.getUsers();
            if (users != null) {
                for (User user: users) {
                    userIDComboList.add(user.getUserId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        UserIDCombo.setItems(userIDComboList);
    }

    /**
     * populates type combo box.
     */

    private void populateTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("Planning Session", "Debriefing", "Follow-up", "Preparation", "Open Session");

        TypeCombo.setItems(typeList);
    }

    /**
     *
     * @param location
     * @param resources
     * initializes appointment creation screen.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateContactComboBox();
        populateCustomerIDComboBox();
        populateTypeComboBox();
        populateTimeComboBoxes();
        populateUserIDComboBox();
    }

}
