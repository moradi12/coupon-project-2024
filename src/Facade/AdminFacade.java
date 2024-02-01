package Facade;

import DAO.CompaniesDAO;
import DAO.CustomersDAO;
import beans.Company;
import beans.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade {
    private CompaniesDAO companiesDAO;
    private CustomersDAO customersDAO;
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin";
    public AdminFacade(CompaniesDAO companiesDAO, CustomersDAO customersDAO) {
        this.companiesDAO = companiesDAO;
        this.customersDAO = customersDAO;
    }
    public AdminFacade() {
    }
    public boolean adminLogin(String email, String password) {
        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("// Admin login successful");
            return true;
        } else {
            System.out.println("Not admin");
            return false;
        }
    }

    public void addCompany(Company company) throws AdminException {
        try {
            companiesDAO.addCompany(company);
        } catch (SQLException e) {
            throw new AdminException("Failed to add company. Reason: " + e.getMessage());
        }
    }

    public void updateCompany(Company company) throws AdminException {
        try {
            companiesDAO.updateCompany(company);
        } catch (SQLException e) {
            throw new AdminException("Failed to update company. Reason: " + e.getMessage());
        }
    }

    public void deleteCompany(int companyId) throws AdminException {
        try {
            companiesDAO.deleteCompany(companyId);
        } catch (SQLException e) {
            throw new AdminException("Failed to delete company. Reason: " + e.getMessage());
        }
    }

    public List<Company> getAllCompanies() throws AdminException {
        try {
            return companiesDAO.getAllCompanies();
        } catch (SQLException e) {
            throw new AdminException("Failed to retrieve companies. Reason: " + e.getMessage());
        }
    }
    public Company getOneCompany(int companyId) throws AdminException {
        try {
            return companiesDAO.getOneCompany(companyId);
        } catch (SQLException e) {
            throw new AdminException("Failed to retrieve company. Reason: " + e.getMessage());
        }
    }
    public void updateCustomer(Customer customer) throws AdminException {
        try {
            customersDAO.updateCustomer(customer);
        } catch (AdminException e) {
            throw new AdminException("Failed to update customer. Reason: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            customersDAO.deleteCustomer(customerId);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomers() throws AdminException {
        try {
            return customersDAO.getAllCustomers();
        } catch (AdminException e) {
            throw new AdminException("Failed to retrieve customers. Reason: " + e.getMessage());
        }
    }

//    public Customer getOneCustomer(int customerId) throws AdminException {
//        try {
//            return customersDAO.getOneCustomer(customerId);
//        } catch (AdminException e) {
//            throw new AdminException("Failed to retrieve customer. Reason: " + e.getMessage());
//        }
//    }

//    public void addCustomer(Customer customer) throws AdminException {
//        try {
//            customersDAO.addCustomer(customer);
//        } catch (SQLException e) {
//            throw new AdminException("Failed to add customer. Reason: " + e.getMessage());
//        }
//    }

    public static class AdminException extends Exception {
        public AdminException(String message) {
            super(message);
        }
    }
}
