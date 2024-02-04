package DBDAO;

import CLS.ConnectionPool;
import DAO.CouponsDAO;
import beans.Category;
import beans.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO {
    private ConnectionPool connectionPool;

    @Override
    public void addCoupon(Coupon coupon) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO COUPONS (id, companyId, category, title, description, start_date, end_date, amount, price, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, String.valueOf(coupon.getId()));
            statement.setString(2, String.valueOf(coupon.getCompanyId()));
            statement.setString(3, String.valueOf(coupon.getCategory()));
            statement.setString(4, coupon.getTitle());
            statement.setString(5, coupon.getDescription());
            statement.setString(6, String.valueOf(coupon.getStartDate()));
            statement.setString(7, String.valueOf(coupon.getEndDate()));
            statement.setString(8, String.valueOf(coupon.getAmount()));
            statement.setString(9, String.valueOf(coupon.getPrice()));
            statement.setString(10, coupon.getImage());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding coupon: " + e.getMessage());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);


        }
    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponID) {

    }

    @Override
    public List<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public boolean addCouponPurchase(int customerID, int couponID) {
        return false;
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {

    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId) {
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId) {
        return null;
    }
}