package DB;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CountryQuery class
 */
public class CountryQuery {
    /**
     * @return
     * @throws SQLException
     * gets all countries in the database.
     */

    public static ObservableList<Country> getCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String searchStatement = "SELECT * FROM countries;";

        DBQuery.setPreparedStatement(JDBC.getConnection(), searchStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Country newCountry = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );

                countries.add(newCountry);

            }

        } catch (Exception e) {
            System.out.println("Error: getCountries" + e.getMessage());

        }
        return countries;
    }


    /**
     * @param
     * @return
     * @throws SQLException
     * gets all countries by id.
     */

    public static ObservableList<Country> getCountryId() throws SQLException {
        String queryStatement = "SELECT * FROM countries";
        ObservableList<Country> countries = FXCollections.observableArrayList();
        DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();


        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Country newCountry = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );
                countries.add(newCountry);

            }
        } catch (Exception e) {
            System.out.println("Error: getCountryID" + e.getMessage());
        }
        return countries;
    }
}
