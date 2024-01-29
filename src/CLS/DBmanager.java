// DBmanager class
package CLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanager {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "12345678";

    // Database table names
    public static final String SQL_CUSTOMERS = "customers";
    public static final String SQL_CATEGORIES = "categories";
    public static final String SQL_COMPANIES = "companies";
    public static final String SQL_COUPONS = "coupons";
    public static final String SQL_CVC = "customers_vs_coupons";



    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create and return the database connection using try-with-resources
            return DriverManager.getConnection(URL, SQL_USER, SQL_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error establishing database connection", e);
        }

    }

}
