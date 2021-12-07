package test_aplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String trololo = "Trololo";
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

        try (Connection connection = DriverManager.getConnection(shop.getUrl(), shop.getUser(), shop.getPassword());
             Statement statement = connection.createStatement()) {
            shop.printCheck(order, statement, card);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
