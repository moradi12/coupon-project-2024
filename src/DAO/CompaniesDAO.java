package DAO;

import beans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password) throws SQLException;
    void addCompany(Company company) throws SQLException;
    void updateCompany(Company company) throws SQLException;
    void deleteCompany(int companyID) throws SQLException;
    List<Company> getAllCompanies() throws SQLException;
    Company getOneCompany(int companyID) throws SQLException;

    



}
