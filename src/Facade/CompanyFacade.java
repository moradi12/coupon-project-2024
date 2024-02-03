package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import beans.Category;
import beans.Coupon;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {

    private final CompaniesDAO companiesDAO;
    private final CouponsDAO couponsDAO;

    public CompanyFacade(CompaniesDAO companiesDAO, CouponsDAO couponsDAO) {
        super();
        this.companiesDAO = companiesDAO;
        this.couponsDAO = couponsDAO;
    }

    public boolean login(String email, String password) {
        try {
            if (companiesDAO.isCompanyExists(email, password)) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return false;
    }

    public void addCoupon(Coupon coupon) {
        List<Coupon> companyCoupons = couponsDAO.getAllCoupons();
        boolean titleExists = companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getCompanyId() == coupon.getCompanyId() &&
                        existingCoupon.getTitle().equals(coupon.getTitle()));

        if (!titleExists) {
            couponsDAO.addCoupon(coupon);
        } else {
            System.out.println("Coupon with the same title already exists for this company");
        }
    }

    public void updateCoupon(Coupon coupon) throws SQLException {
        List<Coupon> companyCoupons = couponsDAO.getAllCoupons();
        boolean couponExists = companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getId() == coupon.getId() &&
                        existingCoupon.getCompanyId() == coupon.getCompanyId());

        if (couponExists) {
            couponsDAO.updateCoupon(coupon);
        } else {
            System.out.println("Coupon not found or does not belong to this company");
        }
    }

    public void deleteCoupon(int couponID) throws SQLException {
        List<Coupon> companyCoupons = couponsDAO.getAllCoupons();
        boolean couponExists = companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getId() == couponID &&
                        existingCoupon.getCompanyId() == companyCoupons.get(0).getCompanyId());

        if (couponExists) {
            couponsDAO.deleteCoupon(couponID);
            couponsDAO.deleteCouponPurchaseHistory(couponID);
        } else {
            System.out.println("Coupon not found or does not belong to this company");
        }
    }

    public List<Coupon> getAllCoupons() throws SQLException {
        int companyId = 1;
        return couponsDAO.getAllCouponsByCompany(companyId);
    }

    private void handleSQLException() {
        handleSQLException(null);
    }

    public void handleSQLException(SQLException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }

    public List<Coupon> getAllCouponsByCategory(Category category) throws SQLException {
        int companyId = 1;
        return couponsDAO.getAllCouponsByCategoryAndCompany(category, companyId);
    }

    public List<Coupon> getAllCouponsByUpToPrice(double price) throws SQLException {
        int companyId = 1;
        return couponsDAO.getAllCouponsUpToPriceAndCompany(price, companyId);
    }

    public Object returnCompanyDetails(String email, String password) {
        try {
            if (companiesDAO.isCompanyExists(email, password)) {
                return companiesDAO.getCompanyDetails(email);
            } else {
                return "Login failed";
            }
        } catch (SQLException e) {
            handleSQLException(e);
            return "An error occurred while fetching company details";
        }
    }
}
