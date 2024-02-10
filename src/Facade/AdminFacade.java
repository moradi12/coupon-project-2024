package Facade;

import DAO.CompaniesDBDAO;
import DAO.CustomersDAO;
import beans.Company;
import beans.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade {
    private CompaniesDBDAO companiesDBDAO;
    private CustomersDAO customersDAO;

    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin";

    /**
     * Constructor for AdminFacade.
     *
     * @param email    The email of the admin.
     * @param password The password of the admin.
     * @throws SQLException If there is an SQL exception.
     */

    public AdminFacade(String email, String password) throws SQLException {
        super(email, password);
    }

    public void addCompany(Company company) throws AdminException {
        try {
            if (companiesDBDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
                throw new AdminException("Company already exists with email: " + company.getEmail());
            } else {
                companiesDBDAO.addCompany(company);
            }
            companiesDBDAO.addCompany(company);
        } catch (SQLException e) {
            throw new AdminException("Failed to add company. Reason: " + e.getMessage());
        }
    }

    public void updateCompany(Company company) throws AdminException {
        try {
            companiesDBDAO.updateCompany(company);
        } catch (SQLException e) {
            throw new AdminException("Failed to update company. Reason: " + e.getMessage());
        }
    }

    public void deleteCompany(int companyId) throws AdminException {
        try {
            companiesDBDAO.deleteCompany(companyId);
        } catch (SQLException e) {
            throw new AdminException("Failed to delete company. Reason: " + e.getMessage());
        }
    }

    public List<Company> getAllCompanies() throws AdminException {
        try {
            return companiesDBDAO.getAllCompanies();
        } catch (SQLException e) {
            throw new AdminException("Failed to retrieve companies. Reason: " + e.getMessage());
        }
    }

    public Company getOneCompany(int companyId) throws AdminException {
        try {
            return companiesDBDAO.getOneCompany(companyId);
        } catch (SQLException e) {
            throw new AdminException("Failed to retrieve company. Reason: " + e.getMessage());
        }
    }

    public void addCustomer(Customer customer) throws AdminException {
        try {
            customersDAO.addCustomer(customer);
        } catch (Exception e) {
            throw new AdminException("Failed to create a new customer. Reason: " + e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) throws AdminException {
        try {
            customersDAO.updateCustomer(customer);
        } catch (Exception e) {
            throw new AdminException("Failed to update customer. Reason: " + e.getMessage());
        }
    }


    public void deleteCustomer(int customerId) throws AdminException {
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
            throw new AdminException(STR."Failed to retrieve customers. Reason: \{e.getMessage()}");
        }
    }


    @Override
    public boolean login(String email, String password) throws AdminException {
        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            // Check if the customer exists
            if (customersDAO.isCustomerExists(email, password)) {
                return true;

            } else {
                throw new AdminException("Customer does not exist.");
            }
        } else {
            throw new AdminException("Invalid email or password for admin login.");
        }
    }

    public static class AdminException extends Exception {
        public AdminException(String message) {
            super(message);
        }
    }
}
