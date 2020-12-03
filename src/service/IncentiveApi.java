package service;

import dao.Special;
import dao.Vehicle;

public interface IncentiveApi {

    Vehicle showSpecialPrice(Vehicle vehicle);

    Special showIncentive(String id);

}
