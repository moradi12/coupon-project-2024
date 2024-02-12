package DBDAO;

import CLS.ConnectionPool;
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
=======
import Sql.coupons;
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
import beans.Category;
import beans.Coupon;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
public class CouponsDBDAO implements DAO.CouponsDBDAO {
=======
public class CouponsDAO implements DAO.CouponsDAO {

>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
    private ConnectionPool connectionPool;


    private Map<Integer, Category> categoryIdToEnum = new HashMap<>();

    public CouponsDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;

        //Category IDs to enums
        // Initialize category ID to enum mapping
        categoryIdToEnum.put(1, Category.Electricity);
        categoryIdToEnum.put(2, Category.Food);
        categoryIdToEnum.put(3, Category.Restaurant);
        categoryIdToEnum.put(4, Category.Vacation);
        categoryIdToEnum.put(5, Category.SportProducts);
        categoryIdToEnum.put(6, Category.Hotels);
    }

<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
=======
    public CouponsDAO() {

    }

>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
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
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
        // add
=======
        String query = "DELETE FROM `coupon_purchases` WHERE `customer_id` = ? AND `coupon_id` = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Coupon purchase deleted successfully.");
            } else {
                System.out.println("No coupon purchase found for deletion.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting coupon purchase: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
        // add
=======
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(coupons.DELETECOUPON_PURCHASE_HISTORY_QUERY)) {
            statement.setInt(1, couponID);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Coupon purchase history for coupon ID " + couponID + " deleted successfully.");
            } else {
                System.out.println("No coupon purchase history found for deletion for coupon ID " + couponID + ".");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting coupon purchase history: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
    }


    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
        // add
        return null;
=======
        List<Coupon> coupons = new ArrayList<>();
        String query = "SELECT * FROM `coupons` WHERE `company_id` = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coupon coupon = resultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error retrieving coupons by company ID: " + e.getMessage(), e);
        }
        return coupons;
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
    }


    @Override
    public List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId) {
        // add
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId) {
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
        // add
        return null;
=======
        List<Coupon> coupons = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coupons WHERE price <= ? AND company_id = ?")) {
            statement.setDouble(1, price);
            statement.setInt(2, companyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Coupon coupon = new Coupon(
                            resultSet.getInt("id"),
                            resultSet.getInt("companyId"),
                            Category.valueOf(resultSet.getString("category")),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("startDate"),
                            resultSet.getDate("endDate"),
                            resultSet.getInt("amount"),
                            resultSet.getDouble("price"),
                            resultSet.getString("image")
                    );
                    coupons.add(coupon);
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return coupons;
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
    }
///////////////helpp/

    @Override

<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
    @Override
    public List<Coupon> getExpiredCoupons(long currentTime) {
        // add
        return null;
    }
=======

    public List<Coupon> getExpiredCoupons(LocalDate currentDate) {
        List<Coupon> expiredCoupons = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM `coupons` WHERE `expiration_date` <= ?")) {
            statement.setDate(1, Date.valueOf(currentDate));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Coupon coupon = resultSetToCoupon(resultSet);
                    expiredCoupons.add(coupon);
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error expired coupons: " + e.getMessage(), e);
        }
        return expiredCoupons;
    }

>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java

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
<<<<<<< HEAD:src/DBDAO/CouponsDBDAO.java
        // Implementation
    }
}
=======

    }}
>>>>>>> c870dfb (getOneCustomer update):src/DBDAO/CouponsDAO.java
