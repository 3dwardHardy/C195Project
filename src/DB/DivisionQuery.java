package DB;

import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DivisionQuery class
 */
public class DivisionQuery {
    /**
     *
     * @return
     * @throws SQLException
     * gets all divisions in the database.
     */

    public static ObservableList<Division> getDivision() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        String queryStatement = "SELECT * FROM first_level_divisions;";
        DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();



        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );

                divisions.add(newDivision);
            }

        } catch (Exception e) {
            System.out.println("Error: getDivision " + e.getMessage());

        }
        return divisions;
    }

    /**
     *
     * @param division
     * @return
     * @throws SQLException
     * gets all divisions by id.
     */

    public static Division getDivisionId(String division) throws SQLException {
        String queryStatement = "SELECT * FROM first_level_divisions WHERE Division=?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setString(1, division);

        Division newDivision = null;
        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );

            }
        } catch (Exception e) {
            System.out.println("Error: getDivisionID" + e.getMessage());
        }
        return newDivision;
    }

    /**
     * @param
     * @throws SQLException
     * gets all divisions by country id.
     */

    public static ObservableList<Division> getDivisionByCountry(int countryId) throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        String queryStatement = "";
        if (countryId == 0) {
            queryStatement = "SELECT * FROM first_level_divisions Where Country_ID=1;";
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            try {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Division newDivision = new Division(
                            resultSet.getInt("Division_ID"),
                            resultSet.getString("Division"),
                            resultSet.getInt("COUNTRY_ID")
                    );
                    divisions.add(newDivision);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return divisions;

        } else if (countryId == 1) {
            queryStatement = "SELECT * FROM first_level_divisions Where Country_ID=2;";
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            try {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Division newDivision = new Division(
                            resultSet.getInt("Division_ID"),
                            resultSet.getString("Division"),
                            resultSet.getInt("COUNTRY_ID")
                    );
                    divisions.add(newDivision);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return divisions;

        } else if (countryId == 2) {
            queryStatement = "SELECT * FROM first_level_divisions Where Country_ID=3;";
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            try {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Division newDivision = new Division(
                            resultSet.getInt("Division_ID"),
                            resultSet.getString("Division"),
                            resultSet.getInt("COUNTRY_ID")
                    );
                    divisions.add(newDivision);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return divisions;

        }
        return divisions;
    }
}
