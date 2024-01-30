import CLS.DButils;
import Clients.ClientType;
import LoginManagerSyst.LoginManager;
import Sql.categories;
import Sql.companies;
import Sql.coupons;
import Sql.Customer;
import Sql.customers_vs_coupons;

public class CreateTableMain {
    public static void main(String[] args) {
        // Create database if not exists
        DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");

        // Use the created database
        DButils.runQuery("USE couponnnn");

        // Execute table creation statements
        DButils.runQuery(categories.CREATE_TABLE_CATEGORIES);
        DButils.runQuery(companies.CREATE_TABLE_COMPANIES);
        DButils.runQuery(coupons.CREATE_TABLE_COUPONS);
        DButils.runQuery(Customer.CREATE_TABLE_CUSTOMERS);
        DButils.runQuery(customers_vs_coupons.CREATE_TABLE_CVC);

    }

}
