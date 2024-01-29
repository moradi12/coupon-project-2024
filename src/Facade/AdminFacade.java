package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;
import beans.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminFacade {

    private CompaniesDAO companiesDAO;
    private CouponsDAO couponsDAO;
    private CustomersDAO customersDAO;

    public AdminFacade(CompaniesDAO companiesDAO, CouponsDAO couponsDAO, CustomersDAO customersDAO) {
        this.companiesDAO = companiesDAO;
        this.couponsDAO = couponsDAO;
        this.customersDAO = customersDAO;
    }

    // Admin specific login method
    public boolean adminLogin(String email, String password) {
        try {
            return companiesDAO.isCompanyExists(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addCompany(Company company) throws AdminException {
        try {
            companiesDAO.addCompany(company);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminException("Failed to add company. Reason: " + e.getMessage());
        }
    }

    public void updateCompany(Company company) throws AdminException {
        try {
            companiesDAO.updateCompany(company);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminException("Failed to update company. Reason: " + e.getMessage());
        }
    }

    public void deleteCompany(int companyId) throws AdminException {
        try {
            companiesDAO.deleteCompany(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminException("Failed to delete company. Reason: " + e.getMessage());
        }
    }

    public List<Company> getAllCompanies() throws AdminException {
        try {
            return companiesDAO.getAllCompanies();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminException("Failed to retrieve companies. Reason: " + e.getMessage());
        }
    }

    public Company getOneCompany(int companyId) throws AdminException {
        try {
            return companiesDAO.getOneCompany(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdminException("Failed to retrieve company. Reason: " + e.getMessage());
        }
    }
    public class AdminException extends Exception {

        public AdminException(String message) {
            super(message);
        }

    }

}
