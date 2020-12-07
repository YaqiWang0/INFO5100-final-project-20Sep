package service;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;

public interface IncentiveApi {

    VehicleModel updateSpecialPrice(Vehicle vehicle);

    Special getSpecial(String specialId);

	String incentiveType(String specialId);

}
