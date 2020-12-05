package service;

import dao.Special;
import dao.Vehicle;

public interface IncentiveApi {

    Vehicle updateSpecialPrice(String specialId);

    Special getSpecial(String specialId);

	String incentiveType(String specialId);

}
