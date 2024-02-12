package Facade;

import CLS.ConnectionPool;
import beans.Coupon;
import beans.Customer;
import DBDAO.CustomersDBDAO;

import java.sql.SQLException;

public class CustomerFacade extends ClientFacade {
    private int customerID;
    private ConnectionPool connectionPool;
    private CustomersDBDAO customerDBDAO;


    /**
     * Constructor for CustomerFacade.
     * @param email The email of the customer.
     * @param password The password of the customer.
     */

    public CustomerFacade(String email, String password) {
        connectionPool = ConnectionPool.getInstance();
        customerDBDAO = new CustomersDBDAO();

    }


    /**
     * Logs in the customer.
     *
     * @param email    The email of the customer.
     * @param password The password of the customer.
     * @return The logged-in customer.
     * @throws SQLException               If there is an SQL exception.
     * @throws AdminFacade.AdminException If there is an admin-related exception.
     */

    @Override
    public boolean login(String email, String password) throws SQLException, AdminFacade.AdminException {
        if (customerDBDAO.isCustomerExists(email, password)) {
            Customer customer = customerDBDAO.getOneCustomer(Integer.parseInt(email));
            this.customerID = customer.getId();
            return true;
        }
        return false ;
    }

    /**
     * Purchases a coupon.
     * @param coupon The coupon to purchase.
     */

    public void purchaseCoupon(Coupon coupon) {
        if (customerID != 0) {
            try {
                couponDBDAO.addCouponPurchase(customerID, coupon.getId());
                System.out.println("Coupon purchased successfully");
            } catch (Exception e) {
                System.out.println("Failed to purchase coupon: " + e.getMessage());
            }
        } else {
            System.out.println("Customer not logged in");
        }
    }
}
