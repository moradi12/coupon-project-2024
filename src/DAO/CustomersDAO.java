package DAO;

import Sql.Customers;

import java.util.ArrayList;

public interface CustomersDAO {

    public boolean isCustomerExsists(String email, String password);

    public void addCustomer(Customers customers);

    public void updateCustomer(Customers customers);

    public void deleteCustomer(int customersID);

    public ArrayList<Customers> getAllCustomers();

    public Customers getOneCustomer(int customerID);


}
