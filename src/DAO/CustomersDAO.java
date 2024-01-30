package DAO;

import Facade.AdminFacade;
import Sql.Customer;

import java.util.List;

public interface CustomersDAO {

    boolean isCustomerExists(String email, String password);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer) throws AdminFacade.AdminException;

    void deleteCustomer(int customerId) throws AdminFacade.AdminException;

    List<beans.Customer> getAllCustomers() throws AdminFacade.AdminException;

    Customer getOneCustomer(int customerID) throws AdminFacade.AdminException;

    void updateCustomer(beans.Customer customer) throws AdminFacade.AdminException;
}