package Facade;

import DAO.CompaniesDBDAO;
import DAO.CouponsDBDAO;
import beans.Category;
import beans.Coupon;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {

<<<<<<< HEAD
    private CompaniesDBDAO companiesDBDAO;
    private CouponsDBDAO couponsDBDAO;
=======
    private CompaniesDAO companiesDAO;
    private CouponsDAO couponsDAO;
>>>>>>> c870dfb (getOneCustomer update)
    private int companyId;

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Constructor for CompanyFacade.
     *
     * @param email    The email of the company.
     * @param password The password of the company.
     */

    public CompanyFacade(String email, String password) {
        super(email, password);
        this.companyId = companyId;
        this.companiesDBDAO = companiesDBDAO;
        this.couponsDBDAO = couponsDBDAO;
    }

    @Override
    public boolean login(String email, String password) throws SQLException, AdminFacade.AdminException {
<<<<<<< HEAD
        if ((companiesDBDAO.isCompanyExists(email, password))) {
            setCompanyId(companiesDBDAO.getCompanyDetails(email).getId());
            return true;
        }
        return false;
    }


=======
        try {
            if (companiesDAO.isCompanyExists(email, password)) {
                int companyId = companiesDAO.getCompanyDetails(email).getId();
                setCompanyId(companyId);
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed. Invalid email or password.");
                return false;
            }
        } catch (SQLException e) {
            handleSQLException(e);
            System.out.println("Login failed due to a database error. Please try again later.");
            return false;
        }
    }
>>>>>>> c870dfb (getOneCustomer update)
    //  add a coupon

    public void addCoupon(Coupon coupon) {
        try {
            if (!isCouponTitleExists(coupon.getTitle())) {
                couponsDBDAO.addCoupon(coupon);
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
                couponsDBDAO.updateCoupon(coupon);
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
                couponsDBDAO.deleteCoupon(couponID, companyId);
                couponsDBDAO.deleteCouponPurchaseHistory(couponID);
            } else {
                System.out.println("Coupon not found or does not belong to this company");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    //  get all coupons
    public List<Coupon> getAllCoupons() {
        return couponsDBDAO.getAllCouponsByCompany(companyId);
    }

    //  get all coupons by category
    public List<Coupon> getAllCouponsByCategory(Category category) {
        return couponsDBDAO.getAllCouponsByCategoryAndCompany(category, companyId);
    }

    //  get all coupons by up to a certain price
    public List<Coupon> getAllCouponsByUpToPrice(double price) {
        return couponsDBDAO.getAllCouponsUpToPriceAndCompany(price, companyId);
    }


    public String getCompanyDetails(String email) {
        try {
            return String.valueOf(companiesDBDAO.getCompanyDetails(email));
        } catch (SQLException e) {
            return "Sorry ,unable to fetch company details at the moment. " +
                    "Please try again later or contact support for assistance.";
        }
    }

    //  check if a coupon with the same title already exists
    private boolean isCouponTitleExists(String title) throws SQLException {
        List<Coupon> companyCoupons = couponsDBDAO.getAllCoupons();
        return companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getCompanyId() == companyId &&
                        existingCoupon.getTitle().equals(title));
    }

    //  check if a coupon exists
    private boolean isCouponExists(int couponID) throws SQLException {
        List<Coupon> companyCoupons = couponsDBDAO.getAllCoupons();
        return companyCoupons.stream()
                .anyMatch(existingCoupon -> existingCoupon.getId() == couponID &&
                        existingCoupon.getCompanyId() == companyId);
    }

}

