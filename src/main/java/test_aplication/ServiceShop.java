package test_aplication;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ServiceShop   {

    private final Repository repository;

    public ServiceShop(Repository repository) {
       this.repository = repository;
    }

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public void putMap(HashMap<String, Integer> map, String[] array) {
        for(int i = 0; i < array.length; i += 2) {
            map.put(array[i], Integer.parseInt(array[i + 1]));
        }
    }

    public void printCheck(HashMap<String, Integer> map, Statement statement,  String str) throws SQLException {

        ResultSet resultSet = statement.executeQuery(repository.getQueryCard() + str);
        ResultSet rsPrintOrder;
        ResultSet rsAmount;
        ResultSet rsId;
        ResultSet rsPrice;
        int sums = 0;

        Integer card = repository.getCard(resultSet);


        for (Map.Entry<String, Integer> pair : map.entrySet()) {

            rsPrintOrder = statement.executeQuery(repository.getQueryShop() + pair.getKey());
            repository.printOrder(rsPrintOrder);
            rsPrintOrder.close();

            rsPrice = statement.executeQuery(repository.getQueryShop() + pair.getKey());
            int price = repository.getPrice(rsPrice);
            rsPrice.close();

            rsAmount = statement.executeQuery(repository.getQueryShop() + pair.getKey());
            int amount = repository.getAmount(rsAmount);
            rsAmount.close();

            rsId = statement.executeQuery(repository.getQueryShop() + pair.getKey());
            int id = repository.getId(rsId);
            rsId.close();

            if (id == 0) {
                System.out.println("Товара нет в наличии");
            } else if (amount < pair.getValue()) {
                System.out.println("  Недостаточное количество товара в наличии, вы можете приобрести только:" + amount);
            } else {
                int sum = price * pair.getValue();
                System.out.println("  " + sum / 100.0);
                sums = sums + sum;
            }
        }

        System.out.println("-------------------------------------------");
            if (card != 0) {
                System.out.println("Скидка : " + decimalFormat.format((sums * 0.1) / 100));
                System.out.println("Итоговая сумма : " + decimalFormat.format((sums * 0.9) / 100));
            } else {
                System.out.println("Скидка : 0");
                System.out.println("Итоговая сумма : " + sums / 100.0);
            }

    }

    public String[] parseStr(String str) {
        return str.split("[^0-9]");
    }


    public String getUrl() {return repository.getUrl();}

    public String getUser() {return repository.getUser();}

    public String getPassword() {return repository.getPassword();}
}
