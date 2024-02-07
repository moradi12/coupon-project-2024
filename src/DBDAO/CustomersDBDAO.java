package DBDAO;

import CLS.ConnectionPool;
import DAO.CustomersDAO;
import Facade.AdminFacade;
import Sql.Customer_sql;
import beans.Customer;
import Exception.CouponAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersDBDAO implements CustomersDAO {
    private ConnectionPool connectionPool;

    public CustomersDBDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public CustomersDBDAO() {

    }

    @Override
    public boolean isCustomerExists(String email, String password) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM `couponnnn`.`customers` WHERE email = ? AND password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.addCustomer)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error adding customer", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.updateCustomer)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.setInt(5, customer.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update customer, customer not found.");
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.deleteCustomer)) {
            statement.setInt(1, customerId);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Failed to delete customer, customer not found.");
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error deleting customer", e);
        }

    }

    public List<Customer> getAllCustomers() throws AdminFacade.AdminException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.getAllCustomers);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("ID"));
                customer.setFirstName(resultSet.getString("First Name"));
                customer.setLastName(resultSet.getString("Last Name"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all customers", e);
        } catch (InterruptedException e) {
            throw new AdminFacade.AdminException("Interrupted while fetching customers");
        }
        return customers;
    }


    @Override
    public Customer getOneCustomer(int customerID) throws AdminFacade.AdminException {
        Customer customer = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.getCustomer)) {
            statement.setInt(1, customerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setId(resultSet.getInt("ID"));
                    customer.setFirstName(resultSet.getString("First Name"));
                    customer.setLastName(resultSet.getString("Last Name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching customer with ID: " + customerID, e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public void buyCupon(int customerId, int couponId) throws CouponAlreadyExistsException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Customer_sql.buyCupon)) {
            statement.setInt(1, customerId);
            statement.setInt(2, couponId);
            statement.executeUpdate();

        } catch (SQLException | InterruptedException e) {
            throw new CouponAlreadyExistsException("Error buying coupon: " + e.getMessage());        }
    }
}