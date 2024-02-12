package Facade;

import DBDAO.CompaniesDAO;
import DBDAO.CouponsDAO;
import DBDAO.CustomersDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CustomersDBDAO customerDBDAO;
    protected CompaniesDAO companiesDBDAO;
    protected CouponsDAO couponDBDAO;
    protected boolean isLogged;
    public ClientFacade() {
        this.customerDBDAO = new CustomersDBDAO();
        this.companiesDBDAO = new CompaniesDAO();
        this.couponDBDAO = new CouponsDAO();
        this.isLogged = false;
        System.out.println("ClientFacade instance created.");
    }
    /**
     * Constructor for ClientFacade with email and password.
     * @param email The email of the client.
     * @param password The password of the client.
     */


    public ClientFacade(String email, String password) {
        this();
        try {
            setLogged(login(email, password));
            if (isLogged()) {
                loginSuccessMessage();
            } else {
                System.out.println("Login failed: Invalid credentials.");
            }
        } catch (AdminFacade.AdminException e) {
            System.out.println("Login failed: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Abstract method for login functionality.
     *
     * @param email    The email of the client.
     * @param password The password of the client.
     * @return A Customer object upon successful login.
     * @return True if login is successful, false otherwise.
     */


    public abstract boolean login(String email, String password) throws SQLException, AdminFacade.AdminException;

    protected boolean isLogged() {
        return isLogged;
    }

    protected void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void logout() {
        setLogged(false);;
        System.out.println("Logged out.");    }

    protected void handleSQLException(SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }

    protected void loginSuccessMessage() {
        System.out.println("Logged in successfully.");
    }

}
