package DBDAO;

import CLS.ConnectionPool;
import beans.Category;
import beans.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements DAO.CouponsDBDAO {
    private ConnectionPool connectionPool;

    private Map<Integer, Category> categoryIdToEnum = new HashMap<>();

    public CouponsDBDAO() {
        //Category IDs to enums
        // Initialize category ID to enum mapping
        categoryIdToEnum.put(1, Category.Electricity);
        categoryIdToEnum.put(2, Category.Food);
        categoryIdToEnum.put(3, Category.Restaurant);
        categoryIdToEnum.put(4, Category.Vacation);
        categoryIdToEnum.put(5, Category.SportProducts);
        categoryIdToEnum.put(6, Category.Hotels);
    }

    private int getCategoryID(Category category) {
        for (Map.Entry<Integer, Category> entry : categoryIdToEnum.entrySet()) {
            if (entry.getValue() == category) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Category not found: " + category);
    }

    private Coupon resultSetToCoupon(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        int companyId = resultSet.getInt("COMPANY_ID");
        int categoryId = resultSet.getInt("CATEGORY_ID");
        Category category = Category.fromInt(categoryId);
        String title = resultSet.getString("TITLE");
        String description = resultSet.getString("DESCRIPTION");
        Date startDate = resultSet.getDate("START_DATE");
        Date endDate = resultSet.getDate("END_DATE");
        int amount = resultSet.getInt("AMOUNT");
        double price = resultSet.getDouble("PRICE");
        String image = resultSet.getString("IMAGE");

        return new Coupon(id, companyId, category, title, description, startDate, endDate, amount, price, image);
    }

    @Override
    public void addCoupon(Coupon coupon) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO COUPONS (id, companyId, category, title, description, start_date, end_date, amount, price, image)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, String.valueOf(coupon.getId()));
            statement.setString(2, String.valueOf(coupon.getCompanyId()));
            statement.setInt(3, getCategoryID(coupon.getCategory()));
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
            throw new RuntimeException("Error adding coupon: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE COUPONS SET NAME = ?, COMPANY_ID = ?, CATEGORY_ID" +
                             " = ?, TITLE = ?, DESCRIPTION = ?, " +
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
            throw new RuntimeException("Error updating coupon: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCoupon(int couponID, int companyID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM COUPONS WHERE id = ? AND company_id = ?")) {
            statement.setInt(1, couponID);
            statement.setInt(2, companyID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting coupon with ID " + couponID + " for company ID " + companyID + ": " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error deleting coupon with ID " + couponID + " for company ID " + companyID + ": " + e.getMessage(), e);
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
    public void deleteCouponPurchase(int customerID, int couponID) {
        // add
    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {
        // add
    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        // add
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId) {
        // add
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId) {
        // add
        return null;
    }

    @Override
    public List<Coupon> getExpiredCoupons(long currentTime) {
        // add
        return null;
    }

    public void addCouponPurchase(int customerID, int couponID) {
        String query = "INSERT INTO `coupon_purchases` (`customer_id`, `coupon_id`) VALUES (?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Coupon purchase added successfully.");
            } else {
                System.out.println("Failed to add coupon purchase.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding coupon purchase: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getOneCoupon(int couponID) {
        // Implementation
    }
}
