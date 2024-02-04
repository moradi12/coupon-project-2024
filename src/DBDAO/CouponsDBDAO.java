package DBDAO;

import CLS.ConnectionPool;
import DAO.CouponsDAO;
import beans.Category;
import beans.Coupon;

import java.sql.*;
import java.util.ArrayList;
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
    public boolean getOneCoupon(int couponID) {

        return false;
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE COUPONS SET NAME = ?, COMPANY_ID = ?, CATEGORY_ID = ?, TITLE = ?, DESCRIPTION = ?, " +
                             "START_DATE = ?, AMOUNT = ?, PRICE = ?, IMAGE = ? WHERE ID = ?")) {

            statement.setString(1, coupon.getImage());
            statement.setInt(2, coupon.getCompanyId());
            statement.setString(3, coupon.getCategory().toString());
            statement.setString(4, coupon.getTitle());
            statement.setString(5, coupon.getDescription());
            statement.setDate(6, Date.valueOf(String.valueOf(coupon.getStartDate())));
            statement.setInt(7, coupon.getAmount());
            statement.setDouble(8, coupon.getPrice());
            statement.setString(9, coupon.getImage());
            statement.setInt(10, coupon.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating coupon: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteCoupon(int couponID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM COUPONS WHERE id = ?")) {
            statement.setInt(1, couponID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error deleting coupon with ID " + couponID + ": " + e.getMessage(), e);
        }


    }

    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM `couponnnn`.`coupons`")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coupon coupon = resultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return coupons;
    }

    @Override
    public boolean addCouponPurchase(int customerID, int couponID) {
        return false;
    }

    private Coupon resultSetToCoupon(ResultSet resultSet) {

        return null;
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

