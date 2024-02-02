package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

import java.sql.SQLException;


public abstract class ClientFacade {

    protected static CustomersDBDAO customerDBDAO = new CustomersDBDAO();
    protected static CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    protected static CouponsDBDAO couponDBDAO = new CouponsDBDAO();

    /**
     * Login method to authenticate a client.
     *
     * @param email    user email
     * @param password user password
     * @return Client object if login successful, otherwise null
     * @throws SQLException if an SQL exception occurs
     */
    public Boolean login(String email, String password) throws SQLException {
        if (customerDBDAO.isCustomerExists(email, password)) {
            return customerDBDAO.getOneCustomer(email, password);
        } else {
            return null;
        }
    }
}