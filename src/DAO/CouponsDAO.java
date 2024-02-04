package DAO;

import beans.Category;
import beans.Coupon;

import java.util.List;

public interface CouponsDAO {
    void addCoupon(Coupon coupon);
    boolean getOneCoupon(int couponID);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponID);
    List<Coupon> getAllCoupons();
    boolean addCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> getAllCouponsByCompany(int companyId);

    List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId);

    List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId);
}
