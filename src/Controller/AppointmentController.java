package Controller;

import DB.ApptQuery;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AppointmentController class.
 */
public class AppointmentController implements Initializable {
    /**
     * Appointments field.
     */
    static ObservableList<Appointment> appointments;
    /**
     * All Radio button field.
     */
    @FXML
    private RadioButton AllRadioButton;
    /**
     * Appointment ID column table field.
     */
    @FXML
    private TableColumn<?, ?> AppointmentIDColumn;
    /**
     * Appointments table field.
     */
    @FXML
/**
 * Appointments tableview field.
 */
    private TableView<Appointment> AppointmentsTable;
    /**
     * Contact column table field.
     */

    @FXML
    private TableColumn<?, ?> ContactColumn;
    /**
     * Create Appointment button.
     */
    @FXML
    private Button CreateAppointmentButton;
    /**
     * Customer ID column table field.
     */
    @FXML
    private TableColumn<?, ?> CustomerIDColumn;
    /**
     * Delete Appointment button.
     */
    @FXML
    private Button DeleteAppointmentButton;
    /**
     * Description column table field.
     */
    @FXML
    private TableColumn<?, ?> DescriptionColumn;
    /**
     * End date column table field.
     */
    @FXML
    private TableColumn<?, ?> EndDateTimeColumn;
    /**
     * Label Header field.
     */
    @FXML
    private Label Header;
    /**
     * Home button field.
     */
    @FXML
    private Button HomeButton;
    /**
     * Location column table field.
     */
    @FXML
    private TableColumn<?, ?> LocationColumn;
    /**
     * Month Select Radio Buttons.
     */
    @FXML
    private RadioButton MonthRadioButton;
    /**
     * Search Button.
     */
    @FXML
    private Button SearchButton;
    /**
     * Search text field.
     */
    @FXML
    private TextField SearchTextField;
    /**
     * Start date column table field.
     */
    @FXML
    private TableColumn<?, ?> StartDateTimeColumn;
    /**
     * Title column table field.
     */
    @FXML
    private TableColumn<?, ?> TitleColumn;
    /**
     * Toggle group for view.
     */
    @FXML
    private ToggleGroup ToggleView;
    /**
     * Type column table field.
     */
    @FXML
    private TableColumn<?, ?> TypeColumn;
    /**
     * Update Customer Button.
     */
    @FXML
    private Button UpdateCustomerButton;
    /**
     * User ID column table field.
     */
    @FXML
    private TableColumn<?, ?> UserIDColumn;
    /**
     * Week Select Radio Buttons.
     */
    @FXML
    private RadioButton WeekRadioButton;

    /**
     *
     * @param event loads create appointment screen
     */
    @FXML
    void CreateAppointment(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/CreateAppointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Create New Appointment");
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
     * @param event deletes appointments
     */
    @FXML
    void DeleteAppointment(ActionEvent event) {
        Appointment selectedAppointment = AppointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select an appointment to delete.");
            alert.showAndWait();
        } else if (AppointmentsTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected appointment. Continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                try {
                    boolean deleteSuccessful = ApptQuery.deleteAppointment(AppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentIdInfo());

                    if (deleteSuccessful) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successfully Deleted");
                        alert.setContentText("Successfully deleted Appointment ID: " + selectedAppointment.getAppointmentIdInfo() + " Type: " + selectedAppointment.getTypeInfo());
                        alert.showAndWait();

                        appointments = ApptQuery.getAppointments();
                        AppointmentsTable.setItems(appointments);
                        AppointmentsTable.refresh();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Could not delete appointment.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     *
     * @param event loads home screen
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
     * @param event searches appointment
     */
    @FXML
    void SearchAppointments(ActionEvent event) {
        ObservableList<Appointment> updateTable = lookupAppointment(SearchTextField.getText());
        AppointmentsTable.setItems(updateTable);
    }

    /**
     *
     * @param event updates appointment
     */
    @FXML
    void UpdateAppointment(ActionEvent event) {
        UpdateAppointmentController.receiveSelectedAppointment(AppointmentsTable.getSelectionModel().getSelectedItem());

        if (AppointmentsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Existing Appointment");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Load Screen Error.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select an appointment to update.");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param event toggles appointment by all/week/month
     */
    @FXML
    void ViewToggle(ActionEvent event) {
        if (AllRadioButton.isSelected()) {
            try {
                appointments = ApptQuery.getAppointments();
                AppointmentsTable.setItems(appointments);
                AppointmentsTable.refresh();
                AppointmentsTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ToggleView.getSelectedToggle().equals(MonthRadioButton)) {
            try {
                appointments = ApptQuery.getAppointmentsMonth();
                AppointmentsTable.setItems(appointments);
                AppointmentsTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (ToggleView.getSelectedToggle().equals(WeekRadioButton)) {
            try {
                appointments = ApptQuery.getAppointmentsWeek();
                AppointmentsTable.setItems(appointments);
                AppointmentsTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     *
     * @param input look up appointments
     * @return
     */
    private static ObservableList<Appointment> lookupAppointment(String input) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        for (Appointment appointment: appointments) {
            if (appointment.getTitleInfo().contains(input)) {
                appointmentList.add(appointment);
            } else if (Integer.toString(appointment.getAppointmentIdInfo()).contains(input)) {
                appointmentList.add(appointment);

            }
        }
        return appointmentList;
    }

    /***
     *
     * @param location
     * @param resources
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WeekRadioButton.setToggleGroup(ToggleView);
        MonthRadioButton.setToggleGroup(ToggleView);
        AllRadioButton.setToggleGroup(ToggleView);

        try {
            appointments = ApptQuery.getAppointments();
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionInfo"));
            LocationColumn.setCellValueFactory(new PropertyValueFactory<>("locationInfo"));
            ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactNameInfo"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeInfo"));
            AppointmentsTable.setItems(appointments);
            AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentIdInfo"));
            TitleColumn.setCellValueFactory(new PropertyValueFactory<>("titleInfo"));
            StartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTimeInfo"));
            EndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTimeInfo"));
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerIdInfo"));
            UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userIdInfo"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
