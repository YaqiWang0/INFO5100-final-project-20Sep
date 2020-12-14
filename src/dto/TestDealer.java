package dto;

import dao.Dealer;

import java.io.IOException;
import java.util.List;

public class TestDealer {
    public static void main(String[] args) throws IOException {
        DataPersistence dp = new DataPersistence();
        List<Dealer> dealers = dp.getAllDealers();
        for (Dealer d: dealers) {
            System.out.println(d);
        }

        dp.writeDealers(dealers);
    }
}
