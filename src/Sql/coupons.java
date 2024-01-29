// coupons class
package Sql;

public class coupons {
    public static final String CREATE_TABLE_COUPONS =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`coupons` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`COMPANY_ID` INT NOT NULL," +
                    "`CATEGORY_ID` INT NOT NULL," +
                    "`TITLE` VARCHAR(45) NOT NULL," +
                    "`DESCRIPTION` VARCHAR(255) NOT NULL," +
                    "`START_DATE` DATE NOT NULL," +
                    "`AMOUNT` INT NOT NULL," +
                    "`PRICE` DOUBLE NOT NULL," +
                    "`IMAGE` VARCHAR(45) NULL," +
                    "PRIMARY KEY (`ID`)," +
                    "UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE" +
                    ");";
}
