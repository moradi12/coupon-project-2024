package DBDAO;

import CLS.ConnectionPool;
import DAO.CompaniesDAO;
import beans.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompaniesDBDAO implements CompaniesDAO {

    private ConnectionPool connectionPool;

    public CompaniesDBDAO() {
    }

    @Override
    public boolean isCompanyExists(String email, String password) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE email = ? AND password = ?")) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking company existence: " + e.getMessage());
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO companies (name, email, password) VALUES (?, ?, ?)")) {

            statement.setString(1, company.getName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getPassword());

            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateCompany(Company company) throws SQLException {
        // Add logic to update a company in the database
    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
        // Add logic to delete a company from the database
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        // Add logic to fetch all companies from the database
        List<Company> companies = new ArrayList<>();
        return companies;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        // Add logic to fetch a single company by ID from the database
        Company company = null;
        return company;
    }

    @Override
    public String getCompanyDetails(String email) {
        // Add logic to fetch company details by email from the database
        return null;
    }
}
