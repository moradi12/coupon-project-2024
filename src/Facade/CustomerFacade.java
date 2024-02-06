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

    public CustomerFacade(String email ,String password) {
        connectionPool = ConnectionPool.getInstance();
        customerDBDAO = new CustomersDBDAO();
    }

    @Override
    public Customer login(String email, String password) throws SQLException, AdminFacade.AdminException {
        if (customerDBDAO.isCustomerExists(email, password)) {
            Customer customer = customerDBDAO.getOneCustomer(Integer.parseInt(email));
            this.customerID = customer.getId();
            return customer;
        }
        return null;
    }

    public void purchaseCoupon(Coupon coupon) {
        if (customerID != 0) {
            try {
                couponDBDAO.addCouponPurchase(customerID, coupon.getId());
                System.out.println("Coupon purchased successfully");
            } catch (SQLException e) {
                System.out.println("Failed to purchase coupon: " + e.getMessage());
            }
        } else {
            System.out.println("Customer not logged in");
        }
    }
    }