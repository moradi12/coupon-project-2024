package DAO;

import beans.Category;
import beans.Coupon;

import java.time.LocalDate;
import java.util.List;

public interface CouponsDBDAO {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponID,int companyID);
    List<Coupon> getAllCoupons();

    void getOneCoupon(int couponID);
    void addCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> getAllCouponsByCompany(int companyId);

    List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId);

    List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId);
<<<<<<< HEAD:src/DAO/CouponsDBDAO.java
    List<Coupon> getExpiredCoupons(long currentTime);
=======
    List<Coupon> getExpiredCoupons(LocalDate currentDate);





>>>>>>> c870dfb (getOneCustomer update):src/DAO/CouponsDAO.java
}

