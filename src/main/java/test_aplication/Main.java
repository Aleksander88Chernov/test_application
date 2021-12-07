package test_aplication;

import java.sql.*;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {

        Model model = new Model();
        ServiceShop shop = new ServiceShop(model);
        HashMap<String, Integer> order = new HashMap();

        Scanner console = new Scanner(System.in);
        System.out.println("Input order:");
        String str = console.nextLine();
        System.out.println("Input card:");
        String card = console.nextLine();
        console.close();

        String[] array = shop.parseStr(str);
        shop.putMap(order, array);

        try {
            connection = DriverManager.getConnection(shop.getUrl(),shop.getUser(),shop.getPassword());
            statement = connection.createStatement();
            shop.printCheck(order, statement,  card);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exception) {
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception exception) {
            }

        }
    }
}
