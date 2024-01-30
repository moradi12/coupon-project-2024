package DBDAO;

import CLS.ConnectionPool;
import DAO.CouponsDAO;
import beans.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO {
    private ConnectionPool connectionPool;

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
    public ArrayList<Coupon> getAllCoupons() {
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
        // Implement the logic to delete purchase history associated with the coupon
        System.out.println("Deleting purchase history for coupon with ID: " + couponID);


    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        return null;
    }
// add it
//    @Override
//    public List<Coupon> getAllCouponsByCompany(int companyId) {
//        return null;
}

