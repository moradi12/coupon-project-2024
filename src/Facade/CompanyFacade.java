package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import beans.Category;
import beans.Coupon;
import beans.Customer;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {

    private  CompaniesDAO companiesDAO;
    private  CouponsDAO couponsDAO;
    private  int companyId;

    /**
     * Constructor for CompanyFacade.
     * @param email The email of the company.
     * @param password The password of the company.
     */

    public CompanyFacade(String email, String password) {
        super(email, password);
        this.companyId = companyId;
        this.companiesDAO = companiesDAO;
        this.couponsDAO = couponsDAO;
    }



    //  add a coupon

    public void addCoupon(Coupon coupon) {
        try {
            if (!isCouponTitleExists(coupon.getTitle())) {
                couponsDAO.addCoupon(coupon);
            } else {
                System.out.println("Coupon with the same title already exists for this company");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    //  update a coupon
    public void updateCoupon(Coupon coupon) {
        try {
            if (isCouponExists(coupon.getId())) {
                couponsDAO.updateCoupon(coupon);
            } else {
                System.out.println("Coupon not found or does not belong to this company");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    //  delete a coupon
    public void deleteCoupon(int couponID) {
        try {
            if (isCouponExists(couponID)) {
                couponsDAO.deleteCoupon(couponID, companyId);
                couponsDAO.deleteCouponPurchaseHistory(couponID);
            } else {
                System.out.println("Coupon not found or does not belong to this company");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    //  get all coupons
    public List<Coupon> getAllCoupons() {
        return couponsDAO.getAllCouponsByCompany(companyId);
    }

    //  get all coupons by category
    public List<Coupon> getAllCouponsByCategory(Category category) {
        return couponsDAO.getAllCouponsByCategoryAndCompany(category, companyId);
    }

    //  get all coupons by up to a certain price
    public List<Coupon> getAllCouponsByUpToPrice(double price) {
        return couponsDAO.getAllCouponsUpToPriceAndCompany(price, companyId);
    }


    public String getCompanyDetails(String email) {
        try {
            return String.valueOf(companiesDAO.getCompanyDetails(email));
        } catch (SQLException e) {
            return "Sorry ,unable to fetch company details at the moment. " +
                    "Please try again later or contact support for assistance.";
        }
    }

    //  check if a coupon with the same title already exists
    private boolean isCouponTitleExists(String title) throws SQLException {
        List<Coupon> companyCoupons = couponsDAO.getAllCoupons();
        return companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getCompanyId() == companyId &&
                        existingCoupon.getTitle().equals(title));
    }

    //  check if a coupon exists
    private boolean isCouponExists(int couponID) throws SQLException {
        List<Coupon> companyCoupons = couponsDAO.getAllCoupons();
        return companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getId() == couponID &&
                        existingCoupon.getCompanyId() == companyId);
    }

    @Override
    public Customer login(String email, String password) throws SQLException, AdminFacade.AdminException {
        return null;
    }
}
