package DAO;

import Facade.AdminFacade;
import Sql.Customer_sql;
import beans.Customer;
import com.mysql.cj.xdevapi.Client;

import java.util.List;

public interface CustomersDAO {

    boolean isCustomerExists(String email, String password);

    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId) throws AdminFacade.AdminException;
    List<Customer> getAllCustomers() throws AdminFacade.AdminException;
    Customer getOneCustomer(int customerID) throws AdminFacade.AdminException;

}