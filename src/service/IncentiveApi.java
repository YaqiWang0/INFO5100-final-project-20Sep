package service;

import dao.Special;
import dao.Vehicle;

public interface IncentiveApi {

    Vehicle updateSpecialPrice(Vehicle vehicle);

    Special getSpecial(String specialId);

	String incentiveType(String specialId);

}
