package Sql;

public class Customer_sql {

    public static final String CREATE_TABLE_CUSTOMERS =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`customers` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`FIRST_NAME` VARCHAR(45) NOT NULL," +
                    "`LAST_NAME` VARCHAR(45) NOT NULL," +
                    "`EMAIL` VARCHAR(45) NOT NULL," +
                    "`PASSWORD` VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (`ID`)," +
                    "UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE" +
                    ");";

    // Insert a new customer
    public static final String addCustomer = "INSERT INTO `couponnnn`.`customers` " +
            "(`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?, ?);";

    // Update an existing customer
    public static final String updateCustomer = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";

    // Get all customers
    public static final String getAllCustomers = "SELECT * FROM `couponnnn`.`customers`";

    // Get customer by email
    public static final String getCustomerByEmail = "SELECT * FROM `couponnnn`.`customers` WHERE EMAIL=?";

    public static final String getCustomer = "SELECT * FROM `couponnnn`.`customers` WHERE ID=?";
    public static final String addCustomerCouponRelation = "INSERT INTO `couponnnn`.`customers_vs_coupons` " +
            "(`CUSTOMER_ID`, `COUPON_ID`) " +
            "VALUES (?, ?);";

    // Get coupons of a customer
    public static final String getCouponsOfCustomer = "SELECT * FROM `couponnnn`.`coupons` " +
            "JOIN `couponnnn`.`customers_vs_coupons` ON `coupons`.`ID` = `customers_vs_coupons`.`COUPON_ID` " +
            "WHERE `customers_vs_coupons`.`CUSTOMER_ID` = ?";

    public static final String deleteCustomer = "DELETE FROM `couponnnn`.`customers` WHERE ID = ?";}
