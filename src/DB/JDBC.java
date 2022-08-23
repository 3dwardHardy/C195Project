package DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * JDBC class
 */
public class JDBC {
    /**
     * protocol field.
     */
    private static final String protocol = "jdbc";
    /**
     * vendor field.
     */
    public static final String vendor = ":mysql:";
    /**
     * location field.
     */

    public static final String location = "//localhost/";
    /**
     * database field.
     */
    public static final String databaseName = "client_schedule";
    /**
     * connection field and sum of protocol, vendor, location, and databaseName.
     */
    public static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    /**
     * driver field.
     */
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * userName field.
     */
    public static final String userName = "sqlUser";
    /**
     * password field.
     */
    public static final String password = "Passw0rd!";
    /**
     * connection field.
     */
    public static Connection connection;

    /**
     *
     * @return connection
     */
    public static Connection getConnection() {return connection;}

    /**
     *connects to the database.
     */

    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     *closes the connection to the database.
     */

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
