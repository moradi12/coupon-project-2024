//// CompaniesDBDAO.java
//package DBDAO;
//
//import CLS.ConnectionPool;
//import DAO.CompaniesDAO;
//import beans.Company;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CompaniesDBDAO implements CompaniesDAO {
//
//    private ConnectionPool connectionPool;
//
//    public CompaniesDBDAO() {
//        // Initialize your connection pool here
//    }
//
//    @Override
//    public boolean isCompanyExists(String email, String password) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        boolean exists = false;
//
//        try {
//            connection = connectionPool.getConnection();
//            String query = "SELECT COUNT(*) FROM companies WHERE email = ? AND password = ?";
//            statement = connection.prepareStatement(query);
//            statement.setString(1, email);
//            statement.setString(2, password);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                exists = count > 0;
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//        return exists;
//    }
//
//    @Override
//    public void addCompany(Company company) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = connectionPool.getConnection();
//            String query = "INSERT INTO companies (name, email, password) VALUES (?, ?, ?)";
//            statement = connection.prepareStatement(query);
//            statement.setString(1, company.getName());
//            statement.setString(2, company.getEmail());
//            statement.setString(3, company.getPassword());
//            statement.executeUpdate();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    @Override
//    public void updateCompany(Company company) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = connectionPool.getConnection();
//            String query = "UPDATE companies SET name = ?, email = ?, password = ? WHERE id = ?";
//            statement = connection.prepareStatement(query);
//            statement.setString(1, company.getName());
//            statement.setString(2, company.getEmail());
//            statement.setString(3, company.getPassword());
//            statement.setInt(4, company.getId());
//            statement.executeUpdate();
//        } finally {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    @Override
//    public void deleteCompany(int companyID) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = connectionPool.getConnection();
//            String query = "DELETE FROM companies WHERE id = ?";
//            statement = connection.prepareStatement(query);
//            statement.setInt(1, companyID);
//            statement.executeUpdate();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    @Override
//    public List<Company> getAllCompanies() throws SQLException {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<Company> companies = new ArrayList<>();
//
//        try {
//            connection = connectionPool.getConnection();
//            statement = connection.createStatement();
//            String query = "SELECT * FROM companies";
//            resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Company company = new Company();
//                company.setId(resultSet.getInt("id"));
//                company.setName(resultSet.getString("name"));
//                company.setEmail(resultSet.getString("email"));
//                company.setPassword(resultSet.getString("password"));
//                companies.add(company);
//            }
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//        return companies;
//    }
//
//    @Override
//    public Company getOneCompany(int companyID) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        Company company = null;
//
//        try {
//            connection = connectionPool.getConnection();
//            String query = "SELECT * FROM companies WHERE id = ?";
//            statement = connection.prepareStatement(query);
//            statement.setInt(1, companyID);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                company = new Company();
//                company.setId(resultSet.getInt("id"));
//                company.setName(resultSet.getString("name"));
//                company.setEmail(resultSet.getString("email"));
//                company.setPassword(resultSet.getString("password"));
//            }
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//        return company;
//    }
//}
