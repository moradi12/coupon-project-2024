package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

//implements CompaniesDAO, CustomersDAO, CouponsDAO//
public abstract class ClientFacade {

    protected CompaniesDBDAO companiesDBDAO;
    protected CustomersDBDAO customersDBDAO;
    protected CouponsDBDAO couponsDBDAO;

    /**
     * @param email    user email
     * @param password user password
     * @return
     */
    public boolean login(String email, String password) {
        return false;
    }


}
