package Sql;

public class companies {
    public static final String CREATE_TABLE_COMPANIES =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`companies` (" +
                    "`idCOMPANIES` INT NOT NULL," +
                    "`NAME` VARCHAR(45) NULL," +
                    "`EMAIL` VARCHAR(45) NULL," +
                    "`PASSWORD` VARCHAR(45) NULL," +
                    "PRIMARY KEY (`idCOMPANIES`)," +
                    "UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE," +
                    "UNIQUE INDEX `idCOMPANIES_UNIQUE` (`idCOMPANIES` ASC) VISIBLE" +
                    ");";


    public static final String addCompany = "INSERT INTO `couponnnn`.`companies` " +
            "(`NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?);";

    public static final String getAllCompanies = "SELECT * FROM `couponnnn`.`companies`";

    public static final String getCompanyByEmailAndPassword = "SELECT * FROM `couponnnn`.`companies` " +
            "WHERE EMAIL=? AND PASSWORD=?";

    public static final String getCompanyById = "SELECT * FROM `couponnnn`.`companies` WHERE idCOMPANIES=?";


}