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
    public boolean isCompanyExists(String email, String password) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE email = ? AND password = ?")) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
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
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE companies SET name = ?, email = ?, password = ? WHERE id = ?")) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getPassword());
            statement.setInt(4, company.getId());
            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM companies WHERE id = ?")) {

            statement.setInt(1, companyId);
            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setEmail(resultSet.getString("email"));
                company.setPassword(resultSet.getString("password"));

                companies.add(company);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException {
        Company company = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE id = ?")) {

            statement.setInt(1, companyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    company = new Company();
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    company.setEmail(resultSet.getString("email"));
                    company.setPassword(resultSet.getString("password"));
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return company;
    }

    @Override
    public Company getCompanyDetails(String email) throws SQLException {
        Company company = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE email = ?")) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    company = new Company();
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    company.setEmail(resultSet.getString("email"));
                    company.setPassword(resultSet.getString("password"));
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
}
