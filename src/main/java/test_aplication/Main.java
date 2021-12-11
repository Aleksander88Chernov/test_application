package test_aplication;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        CreateDB db = new CreateDB();
        Model model = new Model();
        ServiceShop shop = new ServiceShop(model);
        Scanner console = new Scanner(System.in);
        HashMap<String, Integer> order = new HashMap();

        if(!db.exitDB()) {
         db.createNewDB(db.getDB_URL(), db.getUSER(), db.getPASS());
         db.createNewTable(db.getDB_URL(), db.getUSER(), db.getPASS(), db.getCREATE_TABLE_SHOP());
         db.createNewTable(db.getDB_URL(), db.getUSER(), db.getPASS(), db.getCREATE_TABLE_CARD());
         db.addTable(db.getDB_URL(), db.getUSER(), db.getPASS(), db.getAddTableShop());
         db.addTable(db.getDB_URL(), db.getUSER(), db.getPASS(), db.getAddTableCardList());
        }

        while (true) {
            System.out.println("Input order:");
            String str = console.nextLine();

            System.out.println("Input card number:");
            String card = console.nextLine();

            String[] array = shop.parseStr(str);
            shop.putMap(order, array);

            try (Connection connection = DriverManager.getConnection(shop.getUrl(), shop.getUser(), shop.getPassword());
                 Statement statement = connection.createStatement()) {
                shop.printCheck(order, statement, card);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            order.clear();

            System.out.println("Continue ? [Y/N]");
            String answer = console.nextLine();
            if (answer.equalsIgnoreCase("n"))
                break;
        }
    }
}
