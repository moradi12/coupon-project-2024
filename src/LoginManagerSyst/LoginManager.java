package LoginManagerSyst;

import Clients.ClientType;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import DAO.CompaniesDAO;
import DAO.CouponsDAO;

public class LoginManager {
    private static LoginManager instance;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }


    // Method to perform login
    public Object login(String email, String password, ClientType clientType, CompaniesDAO companiesDAO, CouponsDAO
            couponsDAO) {
        Object facade = null;
        switch (clientType) {
            case administrator:
                if (email.equals("admin@admin.com") && password.equals("admin_password")) {
                    facade = new AdminFacade();
                }
                break;
            case company:
                facade = new CompanyFacade(companiesDAO, couponsDAO);
                break;
            case customer:
                facade = new CustomerFacade();
                break;
            default:
                throw new IllegalArgumentException("Unrecognized client type: " + clientType);
        }
        if (facade == null) {
            System.out.println("Login failed.");
        }
        return facade;
    }
}
