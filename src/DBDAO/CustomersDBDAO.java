package DBDAO;

import CLS.ConnectionPool;
import DAO.CustomersDAO;
import Sql.Customers;

import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {


    private ConnectionPool connectionPool;

    @Override
    public boolean isCustomerExsists(String email, String password) {
        return false;
    }

    @Override
    public void addCustomer(Customers customers) {

    }

    @Override
    public void updateCustomer(Customers customers) {

    }

    @Override
    public void deleteCustomer(int customersID) {

    }

    @Override
    public ArrayList<Customers> getAllCustomers() {
        return null;
    }

    @Override
    public Customers getOneCustomer(int customerID) {
        return null;
    }
}
