package test_aplication;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface Repository {
        String getUrl();

        String getUser();

        String getPassword();

        void printOrder(ResultSet resultSet) throws SQLException;

        String getQueryShop();

        Integer getAmount(ResultSet resultSet) throws SQLException;

        Integer getPrice(ResultSet resultSet) throws SQLException;

        String getQueryCard();

        Integer getId(ResultSet resultSet) throws SQLException;

        Integer getCard(ResultSet resultSet) throws SQLException;
    }


