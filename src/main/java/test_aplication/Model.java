package test_aplication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model implements Repository {

    private static final String URL = "jdbc:mysql://localhost:3306/warehouse";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String ID = "id";
    private static final String PRODUCT = "product";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";
    private static final String CARD = "card_id";
    private String queryShop = "SELECT id, product, price,amount  FROM shop WHERE id = ";
    private String queryCard = "SELECT card_id FROM card_list where card_id = ";

    public void printOrder(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(ID);
            String product = resultSet.getString(PRODUCT);
            int price = resultSet.getInt(PRICE);
            System.out.print(product + "  " + price / 100.0);
        }

    }

    public Integer getAmount(ResultSet resultSet) throws SQLException {
        int result;
        for (result = 0; resultSet.next(); result = resultSet.getInt(AMOUNT)) {
        }
        return result;
    }

    public Integer getId(ResultSet resultSet) throws SQLException {
        int result;
        for (result = 0; resultSet.next(); result = resultSet.getInt(ID)) {
        }
        return result;
    }

    public String getQueryShop() {
        return this.queryShop;
    }

    public Integer getPrice(ResultSet resultSet) throws SQLException {
        int result;
        for (result = 0; resultSet.next(); result = resultSet.getInt(PRICE)) {
        }
        return result;
    }

    public String getQueryCard() {
        return this.queryCard;
    }


    public Integer getCard(ResultSet resultSet) throws SQLException {
        Integer result;
        for (result = 0; resultSet.next(); result = resultSet.getInt(CARD)) {
        }
        return result;
    }

    public String getUrl() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
