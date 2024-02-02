package DBDAO;

import CLS.ConnectionPool;
import DAO.CustomersDAO;
import Sql.Customer;

import java.util.List;

public class CustomersDBDAO implements CustomersDAO {
    private ConnectionPool connectionPool;

    @Override
    public boolean isCustomerExists(String email, String password) {
        return false;
    }

    @Override
    public void addCustomer(Customer customers) {

    }

    @Override
    public void updateCustomer(Customer customers) {

    }

    @Override
    public void deleteCustomer(int customersID) {

    }

    @Override
    public List<beans.Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return null;
    }

    @Override
    public void updateCustomer(beans.Customer customer) {

    }

    public boolean getOneCustomer(String email, String password) {

        return false;
    }
}
