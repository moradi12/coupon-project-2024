package DAO;

import beans.Coupon;

import java.util.List;

public interface CouponsDAO {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponID);
    List<Coupon> getAllCoupons();
    void addCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> getAllCouponsByCompany(int companyId);
}
