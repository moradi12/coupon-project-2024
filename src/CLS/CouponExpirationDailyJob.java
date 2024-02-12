package CLS;

<<<<<<< HEAD
import DBDAO.CouponsDBDAO;

import java.util.List;

import beans.Coupon;

public class CouponExpirationDailyJob implements Runnable {

    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

=======
import DBDAO.CouponsDAO;
import beans.Coupon;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CouponExpirationDailyJob implements Runnable {

    private CouponsDAO couponsDBDAO = new CouponsDAO();
>>>>>>> c870dfb (getOneCustomer update)
    private boolean quit;
    private final long TIME = 1000 * 60 * 60 * 24; // 24 hours in milliseconds

    public CouponExpirationDailyJob(boolean quit) {
        this.quit = quit;
    }

    @Override
    public void run() {
        while (!quit) {
<<<<<<< HEAD
            // Check for and delete expired coupons
            DeleteExpiredCoupons();
=======
            deleteExpiredCoupons();
>>>>>>> c870dfb (getOneCustomer update)
            System.out.println("Daily job executed successfully.");
            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted. Stopping job.");
                quit = true;
            }
        }
        System.out.println("Job stopped.");
    }
<<<<<<< HEAD
=======

>>>>>>> c870dfb (getOneCustomer update)
    public void stop() {
        quit = true;
    }

<<<<<<< HEAD
    private void DeleteExpiredCoupons() {
        // Get the current timestamp
        long currentTime = System.currentTimeMillis();
        List<Coupon> expiredCoupons = couponsDBDAO.getExpiredCoupons(currentTime);
        if (expiredCoupons != null) {
            for (Coupon coupon : expiredCoupons) {
                if (coupon.getEndDate().getTime() < currentTime) {
                    // Delete the expired coupon
                    couponsDBDAO.deleteCoupon(coupon.getId(), coupon.getCompanyId());
                    System.out.println("Expired coupon (ID: " + coupon.getId() + ") has been removed.");
                }
=======


    // todo hhhhhhhhhhhhhhhhhhhhhhhhheeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeellllllllllllllllllllllllllllllllllllllpppppppppppppppppppppppp
    private void deleteExpiredCoupons() {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startOfDay = LocalDateTime.of(currentDate, LocalTime.MIDNIGHT);
        Date expirationDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());

        List<Coupon> expiredCoupons = couponsDBDAO.getExpiredCoupons(currentDate);
        if (expiredCoupons != null) {
            for (Coupon coupon : expiredCoupons) {
                couponsDBDAO.deleteCoupon(coupon.getId(), coupon.getCompanyId());
                System.out.println("Expired coupon (ID: " + coupon.getId() + ") has been removed.");
>>>>>>> c870dfb (getOneCustomer update)
            }
        } else {
            System.out.println("No expired coupons found.");
        }
    }

    public static void main(String[] args) {
        CouponExpirationDailyJob job = new CouponExpirationDailyJob(false);
<<<<<<< HEAD

        Thread thread = new Thread(job);
        thread.start();

=======
        Thread thread = new Thread(job);
        thread.start();
>>>>>>> c870dfb (getOneCustomer update)
        // To stop the job, call the stop() method
        // job.stop();
    }
}
