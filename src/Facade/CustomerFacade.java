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

    public Boolean login(String email, String password) {
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
}

