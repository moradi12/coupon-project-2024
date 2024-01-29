package Facade;

import beans.Coupon;
import DAO.CompaniesDAO;
import DAO.CouponsDAO;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade {

    private final CompaniesDAO companiesDAO;
    private final CouponsDAO couponsDAO;

    public CompanyFacade(CompaniesDAO companiesDAO, CouponsDAO couponsDAO) {
        this.companiesDAO = companiesDAO;
        this.couponsDAO = couponsDAO;
    }

    public void login(String email, String password) {
        try {
            if (companiesDAO.isCompanyExists(email, password)) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    /**
     *
     * @param coupon
     */
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

    /**
     *
     * @return
     * @throws SQLException - if we got an sql exception for any reason
     */
    public List<Coupon> getAllCoupons() throws SQLException {
        int companyId = 1; // Replace with the actual company ID
        return couponsDAO.getAllCouponsByCompany(companyId);
    }

    private void handleSQLException(SQLException e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
}
