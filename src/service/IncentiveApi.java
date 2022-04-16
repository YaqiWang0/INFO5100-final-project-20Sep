package service;

import ui.SpecialModel;
import dao.Vehicle;

public interface IncentiveApi {

    SpecialModel updateSpecialPrice(String dealerId, String vehicleId, String oldPrice);

}
