package Controller;

import DB.*;
import Model.*;
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
import java.util.TimeZone;

import static Controller.AppointmentController.appointments;

/**
 * UpdateAppointmentController class
 */
public class UpdateAppointmentController implements Initializable {
    /**
     * Start Date ZoneDateTime.
     */
    private ZonedDateTime StartDateTimeConversion;
    /**
     * End Date ZoneDateTime.
     */
    private ZonedDateTime EndDateTimeConversion;
    /**
     * Select Appointment Object.
     */
    @FXML
    private static Appointment selectAppointment;
    /**
     * Start Date DatePicker.
     */
    @FXML
    private DatePicker StartDateDatePicker;
    /**
     * End Date DatePicker.
     */
    @FXML
    private DatePicker EndDateDatePicker;
    /**
     * Location Text Field.
     */
    @FXML
    private TextField LocationTextField;
    /**
     * Home Button.
     */
    @FXML
    private Button HomeButton;
    /**
     * Description Text Field.
     */
    @FXML
    private TextField DescriptionTextField;
    /**
     * Cancel Button.
     */
    @FXML
    private Button CancelButton;
    /**
     * Start Time Label.
     */
    @FXML
    private Label StartTimeLabel;
    /**
     * Save Button.
     */
    @FXML
    private Button SaveButton;
    /**
     * Appointment ID Text Field.
     */
    @FXML
    private TextField AppointmentIDTextField;
    /**
     * Contact Combo Box.
     */
    @FXML
    private ComboBox<String> ContactCombo;
    /**
     * Customer ID Text Field.
     */
    @FXML
    private TextField CustomerIDTextField;
    /**
     * End Time Label.
     */
    @FXML
    private Label EndTimeLabel;
    /**
     * Description Label.
     */
    @FXML
    private Label DescriptionLabel;
    /**
     * Appointment ID Label.
     */
    @FXML
    private Label AppointmentIDLabel;
    /**
     * Title Label.
     */
    @FXML
    private Label TitleLabel;
    /**
     * Start time Combo Box.
     */
    @FXML
    private ComboBox<String> StartTimeCombo;
    /**
     * Title Text Field.
     */
    @FXML
    private TextField TitleTextField;
    /**
     * Type Label.
     */
    @FXML
    private Label TypeLabel;
    /**
     * Location Label.
     */
    @FXML
    private Label LocationLabel;
    /**
     * End time Combo Box.
     */
    @FXML
    private ComboBox<String> EndTimeCombo;
    /**
     * Header Label.
     */
    @FXML
    private Label Header;
    /**
     * End Date Label.
     */
    @FXML
    private Label EndDateLabel;
    /**
     * Contact Label.
     */
    @FXML
    private Label ContactLabel;
    /**
     * Start Date Label.
     */
    @FXML
    private Label StartDateLabel;
    /**
     * Customer ID Label.
     */
    @FXML
    private Label CustomerIDLabel;
    /**
     * User ID Label.
     */
    @FXML
    private Label UserIDLabel;
    /**
     * Customer ID Combo Box.
     */
    @FXML
    private ComboBox<Integer> CustomerIDCombo;
    /**
     * User ID Combo Box.
     */
    @FXML
    private ComboBox<Integer> UserIDCombo;
    /**
     * Type Combo Box.
     */
    @FXML
    private ComboBox<String> TypeCombo;

    /**
     *
     * @param time
     * @return
     * Converts the date and time to a zoned date time object
     */

    private ZonedDateTime convertToEST(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }
//Converts the zoned date time object to a local date time object
    private ZonedDateTime convertToTimeZone(LocalDateTime time, String zoneId) {
        return ZonedDateTime.of(time, ZoneId.of(zoneId));
    }
//Picks a start date.
    @FXML
    void PickStartDate(ActionEvent event) {

    }
    //picks an end date.
    @FXML
    void PickEndDate(ActionEvent event) {

    }

    /**
     *
     * @param event
     * saves the updated appointment.
     */

    @FXML
    void Save(ActionEvent event) throws SQLException {
        boolean valid = validateAppointment(
                TitleTextField.getText(),
                DescriptionTextField.getText(),
                LocationTextField.getText(),
                AppointmentIDTextField.getText()
        );

        if (valid) {
            try {
                boolean success = ApptQuery.updateAppointment(
                        ContactCombo.getSelectionModel().getSelectedItem(),
                        TitleTextField.getText(),
                        DescriptionTextField.getText(),
                        LocationTextField.getText(),
                        TypeCombo.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of(StartDateDatePicker.getValue(), LocalTime.parse(StartTimeCombo.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeCombo.getSelectionModel().getSelectedItem())),
                        CustomerIDCombo.getSelectionModel().getSelectedItem(),
                        UserIDCombo.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(AppointmentIDTextField.getText())
                );
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully updated appointment");
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
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update appointment");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param title
     * @param description
     * @param location
     * @param appointmentId
     * @return
     * validates the appointment.
     */

    private boolean validateAppointment(String title, String description, String location, String appointmentId) throws SQLException {
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
            alert.setContentText("Title required.");
            alert.showAndWait();
            return false;
        }
        if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Description required.");
            alert.showAndWait();
            return false;
        }
        if (location.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Location required.");
            alert.showAndWait();
            return false;
        }
        if (TypeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Type required.");
            alert.showAndWait();
            return false;
        }
        if (StartDateDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Date required.");
            alert.showAndWait();
            return false;
        }
        if (StartTimeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Time required.");
            alert.showAndWait();
            return false;
        }
        if (EndDateDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End Date required.");
            alert.showAndWait();
            return false;
        }
        if (EndDateDatePicker.getValue().isBefore(StartDateDatePicker.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End Date must be after Start Date.");
            alert.showAndWait();
            return false;
        }
        if (EndTimeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End Time required.");
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

        if (StartDateDatePicker.equals(EndDateDatePicker)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must start and end on the same date.");
            alert.showAndWait();
            return false;
        }

        LocalDateTime selectedStart = startDate.atTime(startTime);
        LocalDateTime selectedEnd = endDate.atTime(endTime);

        LocalDateTime proposedAppointmentStart;
        LocalDateTime proposedAppointmentEnd;
        LocalDate proposedDate;

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

        StartDateTimeConversion = convertToEST(LocalDateTime.of(StartDateDatePicker.getValue(), LocalTime.parse(StartTimeCombo.getSelectionModel().getSelectedItem())));
        EndDateTimeConversion = convertToEST(LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeCombo.getSelectionModel().getSelectedItem())));

        if (StartDateTimeConversion.toLocalTime().isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (EndDateTimeConversion.toLocalTime().isAfter(LocalTime.of(22,0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (StartDateTimeConversion.toLocalTime().isBefore(LocalTime.of(8, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if (EndDateTimeConversion.toLocalTime().isBefore(LocalTime.of(8,0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointments must be within business hours: 8AM - 10PM EST.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     *
     * @param appointment
     * returns to appointment screen after appointment is updated.
     */

        public static void receiveSelectedAppointment(Appointment appointment) { selectAppointment = appointment; }
        @FXML
        void Cancel(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Return to Appointments?");
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
     * returns to home screen.
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
     * populates time combo boxes.
     */
     private void populateTimeComboBoxes() {
     ObservableList<String> time = FXCollections.observableArrayList();
     LocalTime startTime = LocalTime.of(7,0);
     LocalTime endTime = LocalTime.of(23,0);

     time.add(startTime.toString());
     while (startTime.isBefore(endTime)) {
     startTime = startTime.plusMinutes(15);
     time.add(startTime.toString());
     }
     StartTimeCombo.setItems(time);
     EndTimeCombo.setItems(time);
     }

    /**
     * populates contact combo box.
     */
    private void populateContactComboBox() {
        ObservableList<String> contactComboList = FXCollections.observableArrayList();

        try {
            ObservableList<Contact> contacts = ContactsQuery.getContacts();
            if (contacts != null) {
                for (Contact contact: contacts) {
                    if (!contactComboList.contains(contact.getContactNameInfo())) {
                        contactComboList.add(contact.getContactNameInfo());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            ContactCombo.setItems(contactComboList);
        }

    /**
     *populates customer id combo box.
     */
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
     *populates user ID combo box.
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
     *Populates Type combo box.
     */

        private void populateTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "De-Briefing", "Follow-up", "Preparation", "Open Session");
        TypeCombo.setItems(typeList);
        }


    @Override
    /**
     * Initializes the controller class.
     */
    public void initialize(URL location, ResourceBundle resources) {
        populateTimeComboBoxes();
        populateContactComboBox();
        populateCustomerIDComboBox();
        populateUserIDComboBox();
        populateTypeComboBox();
        AppointmentIDTextField.setText(Integer.toString(selectAppointment.getAppointmentIdInfo()));
        ContactCombo.getSelectionModel().select(selectAppointment.getContactIdInfo());
        TitleTextField.setText(selectAppointment.getTitleInfo());
        DescriptionTextField.setText(selectAppointment.getDescriptionInfo());
        LocationTextField.setText(selectAppointment.getLocationInfo());
        TypeCombo.getSelectionModel().select(selectAppointment.getTypeInfo());
        StartDateDatePicker.setValue(selectAppointment.getStartDateInfo());
        EndDateDatePicker.setValue(selectAppointment.getEndDateInfo());
        EndTimeCombo.getSelectionModel().getSelectedItem();
        CustomerIDCombo.setValue(selectAppointment.getCustomerIdInfo());
        UserIDCombo.setValue(selectAppointment.getUserIdInfo());



    }
}
