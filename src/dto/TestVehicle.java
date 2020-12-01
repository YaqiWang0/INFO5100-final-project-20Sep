package dto;

import dao.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestVehicle {
    public static void main(String[] args)  throws IOException {
        DataPersistence dp = new DataPersistence();
        Map<String, Vehicle> vehicleMap = new HashMap<>();
        Vehicle v1 = new Vehicle("gmps-aj-dohmann", "2014", "Cadillac", "CTS Sedan", true, "57620.0", "Red", "Black", BodyType.CAR, "0");
        v1.addImgUrl("http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg");
        v1.addFeatures("3.6L V6 AWD Luxury");
        Vehicle v2 = new Vehicle("gmps-aj-dohmann", "2013", "Chevrolet", "Camaro", true, "32440.0", "Black", "Black", BodyType.CAR, "0");
        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/4/6/5/13411476564x90.jpg");
        v2.addImgUrl("http://inventory-dmg.assets-cdk.com/2/3/5/13411476532x90.jpg");
        v2.addFeatures("2dr Cpe LT w/1LT");
        v2.addFeatures("Test feature");
        vehicleMap.put(v1.getVehicleId(), v1);
        vehicleMap.put(v2.getVehicleId(), v2);

        dp.saveVehiclesToFile(vehicleMap);

        dp.readVehicleFile();
    }
}
