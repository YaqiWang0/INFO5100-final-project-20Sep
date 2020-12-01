package dto;

import dao.Dealer;

import java.io.IOException;
import java.util.Map;

public class TestDealer {
    public static void main(String[] args) throws IOException {
        DataPersistence dp = new DataPersistence();
        Map<String, Dealer> dealerMap = dp.readDealerFile();
        for (String key: dealerMap.keySet()) {
            System.out.println(dealerMap.get(key));
        }

        dp.saveDealersToFile(dealerMap);
    }
}
