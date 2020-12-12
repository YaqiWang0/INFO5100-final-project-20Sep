package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;
import dto.AbstractPersistent;
import dto.DataPersistence;

public final class IncentiveApiImpl implements IncentiveApi {

	private static AbstractPersistent dao;

	public IncentiveApiImpl() {
		dao = new DataPersistence();
	}

	private static Date getCurrentTime() {
		Date currentTime = new Date();
		return currentTime;
	}

	public static boolean timeCheck(Date startDate, Date endDate, Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String sd = formatter.format(startDate);
		String ed = formatter.format(endDate);
		String ct = formatter.format(currentTime);
		if (sd.equals(ed) || ed.equals(ct)) {
			return true;
		}
		return startDate.before(currentTime) && endDate.after(currentTime);
	}


	// read price after discount here
	// Can an incentive be both percent and value discount? No

	/*
	 * Assumption: 1. An incentive can not be percentage discount and direct
	 * discount at the same time 2. Vehicle: Price string only contains float
	 * numbers
	 */
	@Override
	public VehicleModel updateSpecialPrice(Vehicle vehicle) {

		String id = vehicle.getVehicleId();
		List<Special> sList = dao.getAllSpecials();
		VehicleModel model = new VehicleModel(vehicle);
		float price = Float.parseFloat(vehicle.getPrice());

		List<Special> allSpecials = new ArrayList<>();

		TreeMap<Float, Special> pairs = new TreeMap<>();

		for (int i = 0; i < sList.size(); i++) {
			if (sList.get(i).getScope().contains(id)) {
				allSpecials.add(sList.get(i));
				if (timeCheck(sList.get(i).getStartDate(), sList.get(i).getEndDate(), getCurrentTime())) {
					if(sList.get(i).getDiscountValue() != 0) {
						pairs.put(price - sList.get(i).getDiscountValue(), sList.get(i));

					}else if(sList.get(i).getDiscountPercent() != 0) {
						pairs.put((price - price*((float)sList.get(i).getDiscountPercent()/100)), sList.get(i));
					}
				}
			}
		}

		model.setSpecialPrice(pairs.firstKey());
		model.setSpecial(pairs.get(pairs.firstKey()));
		model.setAllSpecials(allSpecials);

		if(model.getSpecial().getDiscountValue() != 0) {
			model.setIncentiveType("Value discount");
		}else if(model.getSpecial().getDiscountPercent() != 0) {
			model.setIncentiveType("Percentage discount");
		}
		return model;

	}

}