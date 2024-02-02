package Facade;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import java.sql.SQLException;

public abstract class ClientFacade {
    protected CustomersDBDAO customerDBDAO = new CustomersDBDAO();
    protected CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    protected CouponsDBDAO couponDBDAO = new CouponsDBDAO();
    private boolean isLogged;
    private final Boolean client;
    public ClientFacade(String email, String password) throws SQLException {
        client = login(email, password);
        if (client != null) {
            isLogged = true;
            System.out.println("Logged in successfully");
        } else {
            isLogged = false;
            System.out.println("Login failed try again");
        }
    }
    /**
     * Login method to authenticate a client.
     *
     * @param email    user email
     * @param password user password
     * @return Client object if login successful, otherwise null
     * @throws SQLException if an SQL exception occurs
     */
    public abstract Boolean login(String email, String password) throws SQLException;

    // Protected methods
    protected boolean isLogged() {
        return isLogged;
    }
    private void checkLoginStatus() {
        isLogged = client != null;
    }
}
