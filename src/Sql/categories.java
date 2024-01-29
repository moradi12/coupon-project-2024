// categories class
package Sql;

public class categories {
    public static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`categories` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT, " +
                    "`NAME` VARCHAR(45) NULL, " +
                    "`DESCRIPTION` TEXT NULL, " +
                    "`CREATED_AT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "PRIMARY KEY (`ID`));";
}
