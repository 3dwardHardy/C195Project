package Controller;

import DB.CountryQuery;
import DB.CustomersQuery;
import DB.DivisionQuery;
import Model.Country;
import Model.Customer;
import Model.Division;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Update Customer Controller class
 */
public class UpdateCustomerController implements Initializable {
    /**
     * Select Customer Object.
     */
    private static Customer selectedCustomer;
    /**
     * Name Label.
     */
    @FXML
    private Label NameLabel;
    /**
     * Phone Label.
     */
    @FXML
    private Label PhoneLabel;
    /**
     * Home Button.
     */
    @FXML
    private Button HomeButton;
    /**
     * Postal Code text field.
     */
    @FXML
    private TextField PostalCodeTextField;
    /**
     * Address text field.
     */
    @FXML
    private TextField AddressTextField;
    /**
     * Cancel Button.
     */
    @FXML
    private Button CancelButton;
    /**
     * Name text field.
     */
    @FXML
    private TextField NameTextField;
    /**
     * Save Button.
     */
    @FXML
    private Button SaveButton;
    /**
     * Header Label.
     */
    @FXML
    private Label Header;
    /**
     * ID Label.
     */
    @FXML
    private Label IDLabel;
    /**
     * Division Label.
     */
    @FXML
    private Label DivisionLabel;
    /**
     * Country combo box.
     */
    @FXML
    private ComboBox<String> CountryCombo;
    /**
     * Postal Code Label.
     */
    @FXML
    private Label PostalCodeLabel;
    /**
     * Address Label.
     */
    @FXML
    private Label AddressLabel;
    /**
     * Country Label.
     */
    @FXML
    private Label CountryLabel;
    /**
     * Division combo box.
     */
    @FXML
    private ComboBox<String> DivisionCombo;
    /**
     * Phone text field.
     */
    @FXML
    private TextField PhoneTextField;
    /**
     * ID text field.
     */
    @FXML
    private TextField IDTextField;

    /**
     *
     * @param event
     * selects division in update customer screen.
     */

    @FXML
    void SelectDivision(ActionEvent event) throws SQLException {

        ObservableList<String> divisionList2 = FXCollections.observableArrayList();




        ObservableList<Division> divisions2 = DivisionQuery.getDivisionByCountry(CountryCombo.getSelectionModel().getSelectedIndex());
        if (divisions2 != null) {
            for (Division division : divisions2) {
                divisionList2.add(division.getDivisionInfo());
                DivisionCombo.setItems(divisionList2);
            }

        }
    }

    /**
     *
     * @param event
     * saves updated customer information.
     */

    @FXML
    void Save(ActionEvent event) {
        boolean valid = validateNotEmpty(
                NameTextField.getText(),
                AddressTextField.getText(),
                PostalCodeTextField.getText(),
                PhoneTextField.getText());
        if (valid) {
            try {
                boolean success = CustomersQuery.updateCustomer(
                        Integer.parseInt(IDTextField.getText()),
                        NameTextField.getText(),
                        AddressTextField.getText(),
                        PostalCodeTextField.getText(),
                        PhoneTextField.getText(),
                        DivisionCombo.getValue()
                );
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully updated customer.");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && (result.get() == ButtonType.OK)) {
                        try {
                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
                            stage.setScene(new Scene(scene));
                        } catch (Exception e) {
                            e.printStackTrace();
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("Load Screen Error.");
                            alert.showAndWait();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save new customer.");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @return
     * validates that all fields are filled in.
     */

    private boolean validateNotEmpty(String name, String address, String postalCode, String phone) {
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Name required.");
            alert.showAndWait();
            return false;
        }

        if (address.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Address required.");
            alert.showAndWait();
            return false;
        }

        if (postalCode.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Postal Code required.");
            alert.showAndWait();
            return false;
        }
        if (phone.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Phone number required.");
            alert.showAndWait();
            return false;
        }
        if (DivisionCombo.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Division required.");
            alert.showAndWait();
            return false;
        }
        if (CountryCombo.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Country required.");
            alert.showAndWait();
            return false;
        }
       return true;
    }

    /**
     *
     * @param customer
     * sets selected customer to update.
     */

    public static void receiveSelectedCustomer(Customer customer) { selectedCustomer = customer; }

    @FXML
    void Cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Navigate to Customers?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Load Screen Error");
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
     *sets division in update screen.
     */

    private void setDivisionCombo() {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        try {
            ObservableList<Division> divisions = DivisionQuery.getDivision();
            if (divisions != null) {
                for (Division division : divisions) {
                    divisionList.add(division.getDivisionInfo());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DivisionCombo.setItems(divisionList);
    }

    /**
     *sets country in update screen.
     */

    private void setCountryCombo() {
        ObservableList<String> countryList = FXCollections.observableArrayList();
        try {
            ObservableList<Country> country = CountryQuery.getCountryId();
            for (Country country1 : country) {
                countryList.add(country1.getCountryInfo());
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        CountryCombo.setItems(countryList);
    }

    /**
     *
     * @param location
     * @param resources
     * Initializes Update Customer Controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDivisionCombo();
        setCountryCombo();

        IDTextField.setText(Integer.toString(selectedCustomer.getCustomerIdInfo()));
        NameTextField.setText(selectedCustomer.getCustomerNameInfo());
        PostalCodeTextField.setText(selectedCustomer.getPostalCodeInfo());
        AddressTextField.setText(selectedCustomer.getAddressInfo());
        PhoneTextField.setText(selectedCustomer.getPhoneNumberInfo());
        CountryCombo.getSelectionModel().select(selectedCustomer.getCountryInfo());
        DivisionCombo.getSelectionModel().select(selectedCustomer.getDivisionInfo());
    }
}
