package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DBQuery class
 */
public class DBQuery {
    /**
     *
     */
    private static PreparedStatement statement;

    /**
     *
     * @param connection
     * @param sqlStatement
     * @throws SQLException
     * sets the prepared statement.
     */

    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    /**
     *
     * @return PreparedStatement.
     */
    public static PreparedStatement getPreparedStatement() { return statement; }
}
