package service;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;

public interface IncentiveApi {

    VehicleModel updateSpecialPrice(Vehicle vehicle);

	String incentiveType(Special s);

}
