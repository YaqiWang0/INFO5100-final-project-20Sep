package dto;

import dao.Dealer;
import dao.Special;
import dao.Vehicle;

import java.util.List;

public interface AbstractPersistent {
    List<Dealer> getAllDealers();
    void writeDealers(List<Dealer> dealers);
    List<Special> getAllSpecials(String dealerId);
    void writeSpecial(Special special, String dealerId);
    List<Vehicle> getAllVehicles();
    void writeVehicles(List<Vehicle> vehicles);
    List<Lead> getAllLeads();
    void writeLead(Lead lead);
}
