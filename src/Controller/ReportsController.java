package Controller;

import DB.ApptQuery;
import DB.ContactsQuery;
import DB.CustomersQuery;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Reports Controller class
 */
public class ReportsController implements Initializable {
    /**
     * Contact combo box.
     */
    @FXML
    private ComboBox<Integer> ContactCombo;
    /**
     * Customer combo box.
     */
    @FXML
    private ComboBox<Integer> CustomerCombo;
    /**
     * Generate Report Button.
     */
    @FXML
    private Button GenerateButton;
    /**
     * Generate Report button 1.
     */
    @FXML
    private Button GenerateButton1;
    /**
     * Generate Report button 2.
     */
    @FXML
    private Button GenerateButton2;
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
     * Monthly Report Button.
     */
    @FXML
    private RadioButton MonthRadioButton;
    /**
     * Records 1 Label.
     */
    @FXML
    private Label Records1Label;
    /**
     * Records 2 Label.
     */
    @FXML
    private AnchorPane Records2Label;
    /**
     * Records 3 Label.
     */
    @FXML
    private Label Records3Label;
    /**
     * Type of report radio button.
     */
    @FXML
    private ToggleGroup TypeMonthToggle;
    /**
     * Type of report radio button.
     */
    @FXML
    private RadioButton TypeRadioButton;

    /**
     *
     * @param event
     * Generates a report of all appointments by Month or Type.
     */

    @FXML
    void Generate1(ActionEvent event) throws SQLException {
        int planning = 0;
        int debriefing = 0;
        int followup = 0;
        int preparation = 0;
        int open = 0;
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;


        try {
            ObservableList<Appointment> appointments = ApptQuery.getAppointments();

                    for (Appointment appointment : appointments) {
                        LocalDate date = appointment.getStartDateInfo();

                        if (date.getMonth().equals(Month.of(1))) {
                            january++;
                        }
                        if (date.getMonth().equals(Month.of(2))) {
                            february++;
                        }
                        if (date.getMonth().equals(Month.of(3))) {
                            march++;
                        }
                        if (date.getMonth().equals(Month.of(4))) {
                            april++;
                        }
                        if (date.getMonth().equals(Month.of(5))) {
                            may++;
                        }
                        if (date.getMonth().equals(Month.of(6))) {
                            june++;
                        }
                        if (date.getMonth().equals(Month.of(7))) {
                            july++;
                        }
                        if (date.getMonth().equals(Month.of(8))) {
                            august++;
                        }
                        if (date.getMonth().equals(Month.of(9))) {
                            september++;
                        }
                        if (date.getMonth().equals(Month.of(10))) {
                            october++;
                        }
                        if (date.getMonth().equals(Month.of(11))) {
                            november++;
                        }
                        if (date.getMonth().equals(Month.of(12))) {
                            december++;
                        }
                    }

        if (MonthRadioButton.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report: Customer Appointment Count by Month");
            alert.setContentText("Total number of Customer Appointments by Month: " +
                    "\nJanuary: " + january +
                    "\nFebruary: " + february +
                    "\nMarch: " + march +
                    "\nApril: " + april +
                    "\nMay: " + may +
                    "\nJune: " + june +
                    "\nJuly: " + july +
                    "\nAugust: " + august +
                    "\nSeptember: " + september +
                    "\nOctober: " + october +
                    "\nNovember: " + november +
                    "\nDecember: " + december);
            alert.setResizable(true);
            alert.showAndWait();
        }

        for (Appointment appointment : appointments) {
            if (appointment.getTypeInfo().equals("Planning Session")) {
                planning++;
            }
            if (appointment.getTypeInfo().equals("Debriefing")) {
                debriefing++;
            }
            if (appointment.getTypeInfo().equals("Follow-up")) {
                followup++;
            }
            if (appointment.getTypeInfo().equals("Preparation")) {
                preparation++;
            }
            if (appointment.getTypeInfo().equals("Open Session")) {
                open++;
            }
        }
            if (TypeRadioButton.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Report: Customer Appointment Count by Type");
                alert.setContentText("Total number of Customer Appointments by Type: " +
                        "\nPlanning Session: " + planning +
                        "\nDebriefing: " + debriefing +
                        "\nFollow-up: " + followup +
                        "\nPreparation: " + preparation +
                        "\nOpen Session: " + open);
                alert.setResizable(true);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * Generates a report based on the contact ID.
     */

    @FXML
    void Generate2(ActionEvent event) {
        Integer contactID = ContactCombo.getSelectionModel().getSelectedItem();

        try {
            ObservableList<Appointment> appointments = ApptQuery.getAppointmentsByContactID(contactID);

            if (appointments != null) {
                for (Appointment appointment : appointments) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Report: Customer Appointment by Contact ID");

                    alert.setContentText("Appointments by Contact ID #" + contactID + ": " +
                            "\nAppointment ID: " + appointment.getAppointmentIdInfo() +
                            "\nTitle: " + appointment.getTitleInfo() +
                            "\nType: " + appointment.getTypeInfo() +
                            "\nDescription: " + appointment.getDescriptionInfo() +
                            "\nStart Date: " + appointment.getStartTimeInfo() +
                            "\nStart Time: " + appointment.getStartTimeInfo() +
                            "\nEnd Date: " + appointment.getEndDateInfo() +
                            "\nEnd Time: " + appointment.getEndTimeInfo() +
                            "\nCustomer ID: " + appointment.getCustomerIdInfo() +
                            "\nUser ID: " + appointment.getUserIdInfo());
                    alert.setResizable(true);
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * Generates a report based on the user ID.
     */

    @FXML
    void Generate3(ActionEvent event) {
            Integer customerID = CustomerCombo.getSelectionModel().getSelectedItem();
            try {
                ObservableList<Appointment> appointments = ApptQuery.getAppointmentsByCustomerID(customerID);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Report: Customer Appointment Count by Customer ID");
                alert.setContentText("Total number of Customer Appointments by Customer ID #" + customerID + ": " + appointments.size());
                alert.setResizable(true);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     *
     * @param event
     * navigates back to the main menu.
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
            alert.setTitle("Error Dialog");
            alert.showAndWait();
        }

    }

    /**
     * initializes the combo boxes.
     */

    private void populateContactIDComboBox() {
        ObservableList<Integer> contactIDComboList = FXCollections.observableArrayList();

        try {
            ObservableList<Contact> contacts = ContactsQuery.getContacts();
            if (contacts != null) {
                for (Contact contact: contacts) {
                    if (!contactIDComboList.contains(contact.getContactIdInfo())) {
                        contactIDComboList.add(contact.getContactIdInfo());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContactCombo.setItems(contactIDComboList);
    }

    /**
     * initializes the combo boxes.
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
        CustomerCombo.setItems(customerIDComboList);
    }

    /**
     *
     * @param location
     * @param resources
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateContactIDComboBox();
        populateCustomerIDComboBox();
    }

}
