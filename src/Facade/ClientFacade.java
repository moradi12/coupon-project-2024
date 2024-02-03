package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import java.sql.SQLException;
public abstract class ClientFacade {
    protected CustomersDBDAO customerDBDAO;
    protected CompaniesDBDAO companiesDBDAO;
    protected CouponsDBDAO couponDBDAO;
    protected boolean isLogged;
    public ClientFacade() {
        this.customerDBDAO = new CustomersDBDAO();
        this.companiesDBDAO = new CompaniesDBDAO();
        this.couponDBDAO = new CouponsDBDAO();
        this.isLogged = false;
        System.out.println("ClientFacade instance created.");
    }

    public ClientFacade(String email, String password) {
    }

    public abstract boolean login(String email, String password) throws SQLException;
    protected boolean isLogged() {
        return isLogged;
    }
    protected void setLogged(boolean logged) {
        isLogged = logged;
    }
    public void logout() {
        isLogged = false;
        System.out.println("Logged out.");
    }
    protected void handleSQLException(SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }
}
