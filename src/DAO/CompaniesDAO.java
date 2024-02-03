package DAO;

import Facade.AdminFacade;
import beans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password) throws SQLException;
    void addCompany(Company company) throws SQLException, AdminFacade.AdminException;
    void updateCompany(Company company) throws SQLException, AdminFacade.AdminException;
    void deleteCompany(int companyID) throws SQLException, AdminFacade.AdminException;
    List<Company> getAllCompanies() throws SQLException, AdminFacade.AdminException;
    Company getOneCompany(int companyID) throws SQLException, AdminFacade.AdminException;

    Company getCompanyDetails(String email) throws SQLException;
}
