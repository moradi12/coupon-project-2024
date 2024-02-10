package CLS;

import DBDAO.CouponsDBDAO;

import java.util.List;

import beans.Coupon;

public class CouponExpirationDailyJob implements Runnable {

    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

    private boolean quit;
    private final long TIME = 1000 * 60 * 60 * 24; // 24 hours in milliseconds

    public CouponExpirationDailyJob(boolean quit) {
        this.quit = quit;
    }

    @Override
    public void run() {
        while (!quit) {
            // Check for and delete expired coupons
            DeleteExpiredCoupons();
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
    public void stop() {
        quit = true;
    }

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
            }
        } else {
            System.out.println("No expired coupons found.");
        }
    }

    public static void main(String[] args) {
        CouponExpirationDailyJob job = new CouponExpirationDailyJob(false);

        Thread thread = new Thread(job);
        thread.start();

        // To stop the job, call the stop() method
        // job.stop();
    }
}
