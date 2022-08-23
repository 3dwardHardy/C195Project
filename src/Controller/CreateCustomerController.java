package Controller;

import DB.CountryQuery;
import DB.CustomersQuery;
import DB.DivisionQuery;
import Model.Country;
import Model.Division;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Create Customer Controller
 */
public class CreateCustomerController implements Initializable {

    /**
     * Address Label.
     */
    @FXML
    private Label AddressLabel;
    /**
     * Address Text Field.
     */
    @FXML
    private TextField AddressTextField;
    /**
     * Cancel Button.
     */
    @FXML
    private Button CancelButton;
    /**
     * Country Combo Box.
     */
    @FXML
    private ComboBox<String> CountryCombo;
    /**
     * Country Label.
     */
    @FXML
    private Label CountryLabel;
    /**
     * Division Combo Box.
     */
    @FXML
    private ComboBox<String> DivisionCombo;
    /**
     * Division Label.
     */
    @FXML
    private Label DivisionLabel;
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
     * ID Label.
     */
    @FXML
    private Label IDLabel;
    /**
     * ID Text Field.
     */
    @FXML
    private TextField IDTextField;
    /**
     * Name Label.
     */
    @FXML
    private Label NameLabel;
    /**
     * Name Text Field.
     */
    @FXML
    private TextField NameTextField;
    /**
     * Phone Label.
     */
    @FXML
    private Label PhoneLabel;
    /**
     * Phone Text Field.
     */
    @FXML
    private TextField PhoneTextField;
    /**
     * Postal Code Label.
     */
    @FXML
    private Label PostalCodeLabel;
    /**
     * Postal Code Text Field.
     */
    @FXML
    private TextField PostalCodeTextField;
    /**
     * Save Button.
     */
    @FXML
    private Button SaveButton;

    /**
     * @param event returns to customer screen
     * cancels creation of customer, returns to home screen.
     */

    @FXML
    void Cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Return to Customers?");
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
                alert.setContentText("Load Screen Error.");
                alert.showAndWait();
            }
        }
    }

    /**
     * @param event returns to home screen
     *
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
     * @param event saves info.
     */

    @FXML
    void Save(ActionEvent event) {
        boolean valid = validateNotEmpty(
                NameTextField.getText(),
                AddressTextField.getText(),
                PostalCodeTextField.getText(),
                PhoneTextField.getText()
        );

        if (valid) {
            try {

                boolean success = CustomersQuery.createCustomer(
                        NameTextField.getText(),
                        AddressTextField.getText(),
                        PostalCodeTextField.getText(),
                        PhoneTextField.getText(),
                        String.valueOf(DivisionCombo.getValue()));

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Created new customer");
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
                            alert.setContentText("Load Screen Error.");
                            alert.showAndWait();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create customer");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * validates that fields are not empty
     *
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @return
     */

    private boolean validateNotEmpty(String name, String address, String postalCode, String phone) {
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Name required.");
            alert.showAndWait();
            return false;
        }
        if (address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Address required.");
            alert.showAndWait();
            return false;
        }
        if (postalCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Postal code required.");
            alert.showAndWait();
            return false;
        }
        if (phone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Phone number required.");
            alert.showAndWait();
            return false;
        }
        if (DivisionCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Division required.");
            alert.showAndWait();
            return false;
        }
        if (CountryCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Country required.");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    /**
     * @param event selects country
     */
    //selects country
    @FXML
    void SelectCountry(ActionEvent event) {


    }

    /**
     * sets division based on country selected
     * @param event
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
     * @return sets Division
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
     *  Selects Country
     * @param event
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
     * @param location
     * @param resources
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDivisionCombo();
        setCountryCombo();
    }
}



