package test_aplication;

import java.sql.*;
import java.util.*;

public class CreateDB  {
    private  final String DB_URL = "jdbc:mysql://localhost:3306/";
    private  final String USER = "root";
    private  final String PASS = "root";
    private final String NAME_DB = "warehouse";
    private  final String CREATE_TABLE_CARD =
              " CREATE TABLE " + NAME_DB + ".`card_list` (" +
              " `id` INT NOT NULL AUTO_INCREMENT," +
              " `card_id` INT NOT NULL," +
              " PRIMARY KEY (`id`));";
private final String CREATE_TABLE_SHOP =
            " CREATE TABLE `warehouse`.`shop` ("+
                    " `id` INT NOT NULL AUTO_INCREMENT,"+
            " `product` VARCHAR(255) NOT NULL,"+
            " `price` INT NOT NULL,"+
            " `amount` INT NOT NULL,"+
            " PRIMARY KEY (`id`));";

    private final List<String> addTableShop = Arrays.asList(
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('telephone', 25034, 7);" ,
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('jacket', 5050, 3);" ,
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('pen', 234, 12);" ,
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('bike', 52050, 2);" ,
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('jeans', 7045, 5);" ,
                    "INSERT INTO " + NAME_DB + ".`shop` (`product`, `price`, `amount`) VALUES ('shoes', 10020, 5);");

    private final List<String> addTableCardList = Arrays.asList(
                    "INSERT INTO " + NAME_DB + ".`card_list` (`card_id`) VALUES ('12345');",
                    "INSERT INTO " + NAME_DB + ".`card_list` (`card_id`) VALUES ('23456');",
                    "INSERT INTO " + NAME_DB + ".`card_list` (`card_id`) VALUES ('34567');",
                    "INSERT INTO " + NAME_DB + ".`card_list` (`card_id`) VALUES ('45678');");

    public String getDB_URL() {
        return this.DB_URL;
    }

    public String getUSER() {
        return this.USER;
    }

    public String getPASS() {
        return this.PASS;
    }

    public String getCREATE_TABLE_CARD() {return this.CREATE_TABLE_CARD;}

    public String getCREATE_TABLE_SHOP() {return this.CREATE_TABLE_SHOP;}

    public List<String> getAddTableCardList() {return this.addTableCardList;}

    public List<String> getAddTableShop() {return this.addTableShop;}

    public  boolean exitDB() {

        boolean hasDB = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

            DatabaseMetaData data = connection.getMetaData();
            ResultSet resultSet = data.getCatalogs();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                if (name.equals(NAME_DB))
                    hasDB = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return hasDB;
    }

    public void createNewDB(String url, String user, String pass) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE SCHEMA " + NAME_DB + ";";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void createNewTable(String url, String user, String pass,String createTable) {
            try (Connection conn = DriverManager.getConnection(url + "warehouse", user, pass);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTable);
            System.out.println("Table created successfully...");
        } catch (SQLException sqlException) {
                sqlException.printStackTrace();
        }
    }

    public void addTable(String url, String user, String pass, List<String> query) {
        try (Connection conn = DriverManager.getConnection(url + NAME_DB, user, pass);
             Statement stmt = conn.createStatement()) {
            for (String add : query) {
                stmt.executeUpdate(add);
            }
            System.out.println("Table add successfully...");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}



    

