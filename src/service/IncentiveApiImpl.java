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

	private static boolean timeCheck(Date startDate, Date endDate, Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String sd = formatter.format(startDate);
		String ed = formatter.format(endDate);
		String ct = formatter.format(currentTime);
		if (sd.equals(ct) || ed.equals(ct)) {
			return true;
		}
		return startDate.before(currentTime) && endDate.after(currentTime);
	}

	// read price after discount here

	/*
	 * Assumption: An incentive can not be percentage discount and direct discount
	 * at the same time.
	 */
	@Override
	public VehicleModel updateSpecialPrice(Vehicle vehicle) {

		String id = vehicle.getVehicleId();
		List<Special> sList = dao.getAllSpecials(vehicle.getDealerId());

		VehicleModel model = new VehicleModel(vehicle);
		float price = Float.parseFloat(vehicle.getPrice());

		List<Special> allSpecials = new ArrayList<>();
		List<Boolean> allHaveSpecial = new ArrayList<>();

		TreeMap<Float, Special> pairs = new TreeMap<>();

		for (int i = 0; i < sList.size(); i++) {
			if (sList.get(i).getScope().contains(id)) {
				if (timeCheck(sList.get(i).getStartDate(), sList.get(i).getEndDate(), getCurrentTime())) {
					if (sList.get(i).getDiscountValue() != 0) {

						allSpecials.add(sList.get(i));
						pairs.put((price - sList.get(i).getDiscountValue()), sList.get(i));

						allHaveSpecial.add(true);
						float specialPrice = pairs.firstKey();
						model.setSpecialPrice(specialPrice);
						model.setSpecial(pairs.get(pairs.firstKey()));
						model.setAllSpecials(allSpecials);
						model.setIncentiveType("Value discount");

					} else if (sList.get(i).getDiscountPercent() != 0) {

						allSpecials.add(sList.get(i));
						pairs.put((price - price * ((float) sList.get(i).getDiscountPercent() / 100)), sList.get(i));

						allHaveSpecial.add(true);
						float specialPrice = pairs.firstKey();
						model.setSpecialPrice(specialPrice);
						model.setSpecial(pairs.get(pairs.firstKey()));
						model.setAllSpecials(allSpecials);
						model.setIncentiveType("Percentage discount");

					} else if (sList.get(i).getDiscountValue() == 0 && sList.get(i).getDiscountPercent() == 0) {
						allHaveSpecial.add(false);
					}
				} else {
					allHaveSpecial.add(false);
				}
			} else {
				allHaveSpecial.add(false);
			}
		}

		if (allHaveSpecial.contains(true)) {
			model.setHaveSpecial(true);
		} else {
			model.setHaveSpecial(false);
		}

		return model;

	}
	
	public static String incentiveAppliedOn(Special s) {
		StringBuilder sb = new StringBuilder();
		if(s.getIsValidOnCashPayment()) {
			sb.append(" Cash payment discount ");
		}
		if(s.getIsValidOnCheckPayment()) {
			sb.append(" Check payment discount ");
		}
		if(s.getIsValidOnLease()) {
			sb.append(" Lease discount ");
		}
		if(s.getIsValidOnLoan()) {
			sb.append(" Loan discount ");
		}
		if(sb.length() == 0) {
			return " Please contact dealer for details ";
		}
		return sb.toString().replace("  ", " & ");
	}

}
