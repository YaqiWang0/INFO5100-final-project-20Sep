package service;

import dao.Special;
import dao.Vehicle;

public interface IncentiveApi {

    void updateSpecialPrice(String specialId);

    Special getSpecial(String specialId);

	String incentiveType(String specialId);

}
