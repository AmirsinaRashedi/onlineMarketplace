package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private Connection connection;
    private String schemaName = "online_marketplace";

    public DatabaseInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {
        initSchema();
        initAddressTable();
        initUserTable();
        initProductTable();
        initElectronicsTable();
        initShoesTable();
        initLitterateurTable();
    }

    private void initLitterateurTable() throws SQLException {
        String createLitterateurTableDDL = "create table if not exists litterateur(" +
                "product_id int not null," +
                "litterateur_title varchar(45)," +
                "litterateur_type varchar(45)," +
                "litterateur_author varchar(45)," +
                "primary key (product_id)," +
                "constraint product_id_fk" +
                "foreign key (product_id)" +
                "references product(product_id)" +
                "on delete no action" +
                "on insert no action)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createLitterateurTableDDL);
    }

    private void initShoesTable() throws SQLException {
        String createShoesTableDDL = "create table if not exists shoes(" +
                "product_id int not null," +
                "shoe_brand varchar(45)," +
                "shoe_type varchar(45)," +
                "primary key (product_id)," +
                "constraint product_id_fk" +
                "foreign key (product_id)" +
                "references product(product_id)" +
                "on delete no action" +
                "on insert no action)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createShoesTableDDL);
    }

    private void initElectronicsTable() throws SQLException {
        String createElectronicsTableDDL = "create table if not exists electronics(" +
                "product_id int not null," +
                "model varchar(45)," +
                "manufacturer varchar(45)," +
                "primary key (product_id)," +
                "constraint product_id_fk" +
                "foreign key (product_id)" +
                "references product(product_id)" +
                "on delete no action" +
                "on insert no action)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createElectronicsTableDDL);
    }

    private void initProductTable() throws SQLException {
        String createProductClassDDL = "create table if not exists product(" +
                "product_id int not null auto_increment," +
                "available_units int," +
                "price_per_unit int," +
                "primary key (product_id))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createProductClassDDL);
    }

    private void initUserTable() throws SQLException {
        String createUserTableDDL = "CREATE TABLE if not exists `user` (" +
                "  `user_id` INT NOT NULL AUTO_INCREMENT," +
                "  `user_name` VARCHAR(45) NULL," +
                "  `password` VARCHAR(45) NULL," +
                "  `real_name` VARCHAR(45) NULL," +
                "  `last_name` VARCHAR(45) NULL," +
                "  `phone_number` CHAR(13) NULL," +
                "  `email` VARCHAR(45) NULL," +
                "  `address_id` INT NULL," +
                "  PRIMARY KEY (`user_id`)," +
                "  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE," +
                "  INDEX `address_id_fk_idx` (`address_id` ASC) VISIBLE," +
                "  CONSTRAINT `address_id_fk`" +
                "    FOREIGN KEY (`address_id`)" +
                "    REFERENCES `online_marketplace`.`address` (`address_id`)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createUserTableDDL);

    }

    private void initAddressTable() throws SQLException {
        String createAddressTableDDL = "CREATE TABLE if not exists address (\n" +
                "  `address_id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `province` VARCHAR(45) NULL,\n" +
                "  `city` VARCHAR(45) NULL,\n" +
                "  `street_name` VARCHAR(45) NULL,\n" +
                "  `postal_code` INT UNSIGNED NULL,\n" +
                "  PRIMARY KEY (`address_id`))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createAddressTableDDL);

    }


    private void initSchema() throws SQLException {
        String createSchemaQuery = "create schema if not exists " + schemaName
                + "DEFAULT CHARACTER SET utf8 COLLATE utf8_persian_ci";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createSchemaQuery);

        statement = connection.createStatement();
        statement.executeUpdate("use " + schemaName);
    }


}
