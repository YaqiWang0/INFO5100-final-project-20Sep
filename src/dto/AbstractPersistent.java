package dto;

import dao.Dealer;
import dao.Special;
import dao.Vehicle;

import java.io.IOException;
import java.util.List;

public interface AbstractPersistent {
    List<Dealer> getAllDealers();
    void writeDealers(List<Dealer> dealers) throws IOException;
    List<Special> getAllSpecials();
    void writeSpecials(List<Special> allSpecials) throws IOException;
    List<Vehicle> getAllVehicles();
    void writeVehicles(List<Vehicle> vehicles) throws IOException;
}
