package dto;

import dao.Dealer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDriver {
    public static void main(String[] args) throws IOException {
        DataPersistence dp = new DataPersistence();
        List<Dealer> dealerMap = dp.getAllDealers();
        for (Dealer dealer : dealerMap) {
            System.out.println(dealer);
        }
        dp.writeDealers(dealerMap);
    }
}
