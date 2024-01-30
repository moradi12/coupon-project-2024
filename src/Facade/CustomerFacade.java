package Facade;

import Sql.Customer;
import beans.Category;
import beans.Company;
import beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends ClientFacade {

    private int customerId;

    public boolean login(String email, String password) {
        return false;
    }

    public void purchaseCoupon(Coupon coupon) {

    }

    public ArrayList<Coupon> getCustomerCoupons() {
        return new ArrayList<>(); //
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) {
        return new ArrayList<>(); //
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
        return new ArrayList<>(); //
    }

    public Customer getCustomerDetails(Customer customer) {
        return customer; //
    }

//////// הוספה לבד של המערכת לשאול מה עושים!
    @Override
    public boolean isCustomerExists(String email, String password) {
        return false;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) throws AdminFacade.AdminException {

    }

    @Override
    public void deleteCustomer(int customerId) throws AdminFacade.AdminException {

    }

    @Override
    public List<beans.Customer> getAllCustomers() throws AdminFacade.AdminException {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws AdminFacade.AdminException {
        return null;
    }

    @Override
    public void updateCustomer(beans.Customer customer) throws AdminFacade.AdminException {

    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException, AdminFacade.AdminException {

    }

    @Override
    public void updateCompany(Company company) throws SQLException, AdminFacade.AdminException {

    }

    @Override
    public void deleteCompany(int companyID) throws SQLException, AdminFacade.AdminException {

    }

    @Override
    public List<Company> getAllCompanies() throws SQLException, AdminFacade.AdminException {
        return null;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException, AdminFacade.AdminException {
        return null;
    }

    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponID) {

    }

    @Override
    public List<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {

    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        return null;
    }
}
