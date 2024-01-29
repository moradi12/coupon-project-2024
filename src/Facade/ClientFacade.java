package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;

public abstract class ClientFacade implements CompaniesDAO, CustomersDAO, CouponsDAO {

    /**
     *
     * @param email user email
     * @param password user password
     * @return
     */
    public boolean login(String email, String password) {
        return false;
    }
}
