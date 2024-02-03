package Facade;
import Sql.Customer;
import beans.Category;
import beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFacade extends ClientFacade {

    private int customerId;

    public CustomerFacade(String email, String password) throws SQLException {
        super(email, password);
    }

    public boolean login(String email, String password) {
        try {
            if (customerDBDAO.isCustomerExists(email, password)) {
                System.out.println("Login successful for email: " + email);
                return true;
            } else {
                System.out.println("Login failed: Customer does not exist for email: " + email);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            return false;
        }
    }

    public void purchaseCoupon(Coupon coupon) {
        try {
            if (couponDBDAO.addCouponPurchase(customerId, coupon.getId())) {
                System.out.println("Coupon purchased successfully.");
            } else {
                System.out.println("Failed to purchase coupon.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during coupon purchase: " + e.getMessage());
        }
    }

    public ArrayList<Coupon> getCustomerCoupons() {
        try {
            return couponDBDAO.getCustomerCoupons(customerId);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching customer coupons: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) {
        try {
            return couponDBDAO.getCustomerCouponsByCategory(customerId, category);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching customer coupons: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
        try {
            return couponDBDAO.getCustomerCouponsByMaxPrice(customerId, maxPrice);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching customer coupons: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Customer getCustomerDetails() {
        try {
            return customerDBDAO.getOneCustomer(customerId);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching customer details: " + e.getMessage());
            return null;
        }
    }
}
