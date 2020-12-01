package dto;

import dao.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestVehicle {
    public static void main(String[] args)  throws IOException {
        DataPersistence dp = new DataPersistence();
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle v1 = new Vehicle("gmps-aj-dohmann", "2014", "Cadillac", "CTS Sedan", true, "57620.0", "Red", "Black", BodyType.CAR, "0");
        v1.addImgUrl("http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg");
        v1.addFeatures("3.6L V6 AWD Luxury");
        Vehicle v2 = new Vehicle("gmps-aj-dohmann", "2013", "Chevrolet", "Camaro", true, "32440.0", "Black", "Black", BodyType.CAR, "0");
        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/4/6/5/13411476564x90.jpg");
        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/2/3/5/13411476532x90.jpg");
        v2.addFeatures("2dr Cpe LT w/1LT");
        v2.addFeatures("Test feature");
        System.out.println("Before Writing To File:");
        System.out.println(v1.toCSVLine());
        System.out.println(v2.toCSVLine());

        vehicleList.add(v1);
        vehicleList.add(v2);

<<<<<<< HEAD
        dp.writeVehicles(vehicleList);

        List<Vehicle> vehicles = dp.getAllVehicles();
        System.out.println("\nAfter Reading From File:");
        for (Vehicle v: vehicles) {
            System.out.println(v.toCSVLine());
=======
        vehicleMap = dp.readVehicleFile();
        for (String k: vehicleMap.keySet()) {
            System.out.println(vehicleMap.get(k).toCSVLine());
>>>>>>> c873bae (fixed error on special)
        }
    }
}
