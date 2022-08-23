package Controller;

import DB.ApptQuery;
import DB.CustomersQuery;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * CustomersController class
 */
public class CustomersController implements Initializable {
    /**
     * Customers List.
     */
    static ObservableList<Customer> customers;
    /**
     * Address Table column.
     */
    @FXML
    private TableColumn<?, ?> AddressColumn;
    /**
     * Country Table column.
     */
    @FXML
    private TableColumn<?, ?> CountryColumn;
    /**
     * Create Customer Button.
     */
    @FXML
    private Button CreateCustomerButton;
    /**
     * Customer ID Table column.
     */
    @FXML
    private TableColumn<?, ?> CustomerIDColumn;
    /**
     * Customers Table view.
     */
    @FXML
    private TableView<Customer> CustomersTable;
    /**
     * Delete Customer Button.
     */
    @FXML
    private Button DeleteCustomerButton;
    /**
     * Division Table column.
     */
    @FXML
    private TableColumn<?, ?> DivisionColumn;
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
     * Name Table column.
     */
    @FXML
    private TableColumn<?, ?> NameColumn;
    /**
     * Phone Table column.
     */
    @FXML
    private TableColumn<?, ?> PhoneColumn;
    /**
     * Postal Code Table column.
     */
    @FXML
    private TableColumn<?, ?> PostalCodeColumn;
    /**
     * Search Customer Button.
     */
    @FXML
    private Button SearchButton;
    /**
     * Search Customer Text Field.
     */
    @FXML
    private TextField SearchTextField;
    /**
     * Update Customer Button.
     */
    @FXML
    private Button UpdateCustomerButton;

    /**
     *
     * @param event loads the create customer screen
     */

    @FXML
    void CreateCustomer(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/CreateCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Create New Customer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Load Screen Error.");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param event deletes customer
     */

    @FXML
    void DeleteCustomer(ActionEvent event) {
        Customer selectedCustomer = CustomersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a customer to delete.");
            alert.showAndWait();
        } else if (CustomersTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected customer? This action will also delete all associated appointments.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                try {
                    boolean valid = checkAppointments(selectedCustomer);
                    if (valid) {
                        boolean deleteSuccessful = CustomersQuery.deleteCustomer(CustomersTable.getSelectionModel().getSelectedItem().getCustomerIdInfo());
                        ApptQuery.deleteAppointment(CustomersTable.getSelectionModel().getSelectedItem().getCustomerIdInfo());


                        if (deleteSuccessful) {
                            customers = CustomersQuery.getCustomer();
                            CustomersTable.setItems(customers);
                            CustomersTable.refresh();
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("Unable to delete Customer.");
                            alert.showAndWait();
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Unable to delete customer with existing appointments.");
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
     * @param event searches customer
     */

    @FXML
    void SearchCustomers(ActionEvent event) {
        ObservableList<Customer> updateTable = lookupCustomer(SearchTextField.getText());
        CustomersTable.setItems(updateTable);
    }

    /**
     *
     * @param input
     * @return
     */
    private static ObservableList<Customer> lookupCustomer(String input) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        for (Customer customer: customers) {
            if (customer.getCustomerNameInfo().contains(input)) {
                customerList.add(customer);
            } else if (Integer.toString(customer.getCustomerIdInfo()).contains(input)) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    /**
     *
     * @param event updates existing customer
     */

    @FXML
    void UpdateCustomer(ActionEvent event) {
        UpdateCustomerController.receiveSelectedCustomer(CustomersTable.getSelectionModel().getSelectedItem());
        if (CustomersTable.getSelectionModel().getSelectedItem() != null) {


            try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Existing Customer");
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
            alert.setContentText("You must select a customer to update.");
            alert.showAndWait();
        }
        }

    /**
     *
     * @param selectedCustomer
     * @return true if customer has no appointments. false if customer has appointments.
     */
        private boolean checkAppointments(Customer selectedCustomer) {
        try {
            ObservableList appointments = ApptQuery.getAppointmentsByCustomerID(selectedCustomer.getCustomerIdInfo());

        } catch (SQLException e) {
            e.printStackTrace();

        }
            return true;
        }

    /**
     *
      * @param location
     * @param resources
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customers = CustomersQuery.getCustomer();
            CustomersTable.setItems(customers);
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerIdInfo"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerNameInfo"));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<>("addressInfo"));
            PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCodeInfo"));
            PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumberInfo"));
            DivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionInfo"));
            CountryColumn.setCellValueFactory(new PropertyValueFactory<>("countryInfo"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
}