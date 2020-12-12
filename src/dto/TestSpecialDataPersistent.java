package dto;

import dao.Dealer;
import dao.Special;
import dao.Vehicle;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestSpecialDataPersistent {
    public static void main(String[] args) throws Exception {
        DataPersistence dp = new DataPersistence();

        Special i = new Special();
        i.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("08/12/2020"));
        i.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020"));
        i.setDiscountValue(1500);
        //i.setDiscountPercent(0);
        i.setValidOnCashPayment(true);
        i.setValidOnCheckPayment(true);
        i.setValidOnLoan(true);
        i.setValidOnLease(false);
        i.setValueOfVehicle("57000");
        //i.setYear("2017");
        i.setBrand("Cadillac");
        //i.setBodyType("Spark");
        //i.setIsNew("New");
        //i.setScopeMiles("5000");
        i.setTitle("Deals on Cadillac");
        i.setDescription("All Cadillac above $57k on sale with $1500 off.");
        i.setDisclaimer("The vehicle photo displayed may be an example only. Pricing throughout the web site " +
                "does not include any options that may have been installed at the dealership. Please see the dealer " +
                "for details. Vehicles may be in transit or currently in production.");

        List<Dealer> allDealers = dp.getAllDealers();
        i.setDealerName("gmps-aj-dohmann");

        List<Vehicle> allVehicles = dp.getAllVehicles();
        List<String> scopes = new ArrayList<>();
        for (Vehicle v : allVehicles) {
            scopes.add(v.getVehicleId());
        }
        //i.setScope(scopes);

        //dp.writeSpecial(i, i.getDealerName());

        List<Special> allSpecialsRead = dp.getAllSpecials("gmps-ernievon");
        for (Special s: allSpecialsRead) {
            System.out.println(s.getSpecialId());
            System.out.println(s.getScope());
        }
//        System.out.println(allSpecialsRead.get(0).getTitle());
//        System.out.println(allSpecialsRead.get(1).getTitle());
//        System.out.println(allSpecialsRead.get(2).getTitle());

    }

}
