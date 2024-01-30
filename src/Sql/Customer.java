package Sql;

public class Customer {

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
}
