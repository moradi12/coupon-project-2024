package LoginManagerSyst;

import Clients.ClientType;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import DAO.CompaniesDAO;
import DAO.CouponsDAO;

public class LoginManager {
    public Object login(String email, String password, ClientType clientType, CompaniesDAO companiesDAO, CouponsDAO couponsDAO) {
        Object facade = null;

        switch (clientType) {
            case administrator:
                if (email.equals("admin@admin.com") && password.equals("admin_password")) {
                    facade = new AdminFacade();
                }
                break;
            case company:
                if (email.equals("company@company.com") && password.equals("company_password")) {
                    facade = new CompanyFacade(companiesDAO, couponsDAO);
                }
                break;
            case customer:
                if (email.equals("customer@customer.com") && password.equals("customer_password")) {
                    facade = new CustomerFacade();
                }
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
