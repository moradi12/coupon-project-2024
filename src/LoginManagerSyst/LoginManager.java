package LoginManagerSyst;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Clients.ClientType;
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
//IllegalArgumentException/// למה זאת האופציה היחידה להוספה ?

    public ClientFacade login(String email, String password, ClientType clientType) throws LoginException, SQLException, IllegalArgumentException {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }

        ClientFacade facade;
        switch (clientType) {
            case company:
                facade = new CompanyFacade(email, password);
                break;
            case customer:
                facade = new CustomerFacade(email,password);
                break;
            case administrator:
                if (!email.equals("admin@admin.com") || !password.equals("admin")) {
                    throw new LoginException("Invalid email or password for admin");
                }
                facade = new AdminFacade(email, password);
                break;
            default:
                throw new IllegalArgumentException("Invalid client type: " + clientType);
        }
        return facade;
    }

    public void logout(ClientFacade facade) {
        facade.logout();
    }
}
