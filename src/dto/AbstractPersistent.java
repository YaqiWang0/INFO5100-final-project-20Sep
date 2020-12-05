package dto;

import dao.Dealer;
import dao.Special;
import dao.Vehicle;

import java.util.List;

public interface AbstractPersistent {
    List<Dealer> getAllDealers();
    void writeDealers(List<Dealer> dealers);
    List<Special> getAllSpecials();
    void writeSpecials(Special special);
    List<Vehicle> getAllVehicles();
    void writeVehicles(List<Vehicle> vehicles);
}
