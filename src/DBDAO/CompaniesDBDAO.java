package DBDAO;

import CLS.ConnectionPool;
import DAO.CompaniesDAO;
import beans.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompaniesDBDAO implements CompaniesDAO {

    private ConnectionPool connectionPool;

    public CompaniesDBDAO() {
        // Initialize your connection pool here
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
    }

    @Override
    public void updateCompany(Company company) throws SQLException { 
    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new ArrayList<>();
        return companies;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        Company company = null;
        return company;
    }

    @Override
    public String getCompanyDetails(String email) {
        return null;
    }
}



