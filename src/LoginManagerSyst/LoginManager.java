// LoginManager.java
package LoginManagerSyst;

import Clients.ClientType;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class LoginManager {
    private static volatile LoginManager instance = null;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null)
                    instance = new LoginManager();
            }
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws LoginException, SQLException, IllegalArgumentException {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
        switch (clientType) {
            case company:
///whgat is the problem
                return new CompanyFacade(email, password);
            case customer:
                return new CustomerFacade(email, password);
            case administrator:
                if (!email.equals("admin@admin.com") || !password.equals("admin")) {
                    throw new LoginException("Invalid email or password for admin");
                }
                return new AdminFacade(email, password);
            default:
                throw new IllegalArgumentException("Invalid client type: " + clientType);
        }
    }

    public void logout(ClientFacade facade) {
        facade.logout();
    }
}
