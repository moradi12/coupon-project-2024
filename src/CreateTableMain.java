import CLS.DBmanager;
import CLS.DButils;
import Sql.Categories;
import Sql.companies;
import Sql.coupons;
import Sql.Customer_sql;
import Sql.customers_vs_coupons;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableMain {
    public static void main(String[] args) {

        try (Connection connection = DBmanager.getConnection()) {
            DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");
            DButils.runQuery("USE couponnnn");

            connection.setAutoCommit(false);
            createTable(connection, Categories.CREATE_TABLE_CATEGORIES);
            createTable(connection, companies.CREATE_TABLE_COMPANIES);
            createTable(connection, coupons.CREATE_TABLE_COUPONS);
            createTable(connection, Customer_sql.CREATE_TABLE_CUSTOMERS);
            createTable(connection, customers_vs_coupons.CREATE_TABLE_CVC);


            connection.commit();
            System.out.println("Table creation completed successfully.");
        } catch (SQLException e) {
            System.out.println("Error during table creation: " + e.getMessage());
        }
    }

    private static void createTable(Connection connection, String createTableQuery) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
}
