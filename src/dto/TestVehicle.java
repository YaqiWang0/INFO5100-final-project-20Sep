package dto;
import dao.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestVehicle {
    public static void main(String[] args)  throws IOException {
        DataPersistence dp = new DataPersistence();
        List<Vehicle> vehicleList = new ArrayList<>();
//        Vehicle v1 = new Vehicle("1f3f02f6-1d69-4874-b976-e45d0d44a5bc", "2014", "Cadillac", "CTS Sedan", true, "57620.0", "Red", "Black", BodyType.CAR, "0");
//        v1.addImgUrl("http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg");
//        v1.addFeatures("3.6L V6 AWD Luxury");
//        Vehicle v2 = new Vehicle("1f3f02f6-1d69-4874-b976-e45d0d44a5bc", "2013", "Chevrolet", "Camaro", true, "32440.0", "Black", "Black", BodyType.CAR, "0");
//        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/4/6/5/13411476564x90.jpg");
//        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/2/3/5/13411476532x90.jpg");
//        v2.addFeatures("2dr Cpe LT w/1LT");
//        v2.addFeatures("Test feature");
//        System.out.println("Before Writing To File:");
//        System.out.println(v1.toCSVLine());
//        System.out.println(v2.toCSVLine());
//
//        vehicleList.add(v1);
//        vehicleList.add(v2);
//
//        // clean up previous test result
//        String vehicleFilePath = new File("").getAbsolutePath() + "/data/" + "vehicles.csv";
//        File csv = new File(vehicleFilePath);
//        if (csv.exists()) { csv.delete(); }
//
//        dp.writeVehicles(vehicleList);
//
//        List<Vehicle> vehicles = dp.getAllVehicles();
//        System.out.println("\nAfter Reading From File:");

        stressTest(dp.getAllDealers(), dp);
    }

    public static void stressTest(List<dao.Dealer> dealerList, DataPersistence dp) {
//        Tests getting all vehicles of all dealers existed.
        for (dao.Dealer dealer: dealerList) {
            List<Vehicle> vehicleList = dp.getVehicles(dealer.getId());
            for (Vehicle v: vehicleList) {
                System.out.println(v.toCSVLine());
            }

        }

    }
}
