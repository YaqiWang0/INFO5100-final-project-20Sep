package service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import dao.Special;
import dto.Dealer;
import ui.SpecialModel;
import dao.Vehicle;
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
	public SpecialModel updateSpecialPrice(String dealerName, String vehicleId, String oldPrice) {
		String dealerId = "";
		List<dao.Dealer> dealerList = dao.getAllDealers();
		for (dao.Dealer dealer: dealerList) {
			if (dealerName.equals(dealer.getDealerName()))
				dealerId = dealer.getId();
		}

		if (dealerId==null || "".equals(dealerId)) {
			return new SpecialModel();
		}

		if (vehicleId==null || "".equals(vehicleId)) {
			return new SpecialModel();
		}

		if (oldPrice==null || "".equals(oldPrice)) {
			return new SpecialModel();
		}

		String id = vehicleId;
		List<Special> sList = dao.getAllSpecials(dealerId);

		SpecialModel model = new SpecialModel();
		float price = Float.parseFloat(oldPrice);

		List<SpecialModel> allSpecials = new ArrayList<>();
		List<Boolean> allHaveSpecial = new ArrayList<>();

		TreeMap<Float, Special> pairs = new TreeMap<>();

		for (int i = 0; i < sList.size(); i++) {
			Special s = sList.get(i);
			if (s.getScope().contains(id)) {
				if (timeCheck(s.getStartDate(), s.getEndDate(), getCurrentTime())) {
					if (s.getDiscountValue() > 0) {

						float newPrice1 = (price - s.getDiscountValue());
						float newPrice = new BigDecimal(newPrice1).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						pairs.put(newPrice > 0 ? newPrice : 0, s);
						float specialPrice = pairs.firstKey();
						model.setSpecialPrice(specialPrice);
						model.setSpecial(pairs.get(pairs.firstKey()));
						

						allHaveSpecial.add(true);

					} else if (s.getDiscountPercent() > 0) {

						float newPrice2 = (price - price * ((float) s.getDiscountPercent() / 100));
						float newPrice = new BigDecimal(newPrice2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						pairs.put(newPrice > 0 ? newPrice : 0, s);
						float specialPrice = pairs.firstKey();
						model.setSpecialPrice(specialPrice);
						model.setSpecial(pairs.get(pairs.firstKey()));

						allHaveSpecial.add(true);

					} else {
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
		
		//generate allSpecials
		for (int j = 0; j < sList.size(); j++) {
			Special s = sList.get(j);
			SpecialModel vm = new SpecialModel(s);
			if (s.getScope().contains(id)) {
				if (s.getDiscountValue() > 0) {
					float newPrice = (price - s.getDiscountValue());
					vm.setSpecialPrice(newPrice > 0 ? newPrice : 0);
					allSpecials.add(vm);
				}else if(s.getDiscountPercent() > 0) {
					float newPrice = price - price * ((float) s.getDiscountPercent() / 100);
					vm.setSpecialPrice(newPrice > 0 ? newPrice : 0);
					allSpecials.add(vm);
				} 
			}
		}
		
		model.setAllSpecials(allSpecials);

		return model;

	}

	public static String incentiveAppliedOn(Special s) {
		StringBuilder sb = new StringBuilder();
		if (s.getIsValidOnCashPayment()) {
			sb.append(" Cash payment discount ");
		}
		if (s.getIsValidOnCheckPayment()) {
			sb.append(" Check payment discount ");
		}
		if (s.getIsValidOnLease()) {
			sb.append(" Lease discount ");
		}
		if (s.getIsValidOnLoan()) {
			sb.append(" Loan discount ");
		}
		if (sb.length() == 0) {
			return " Please contact dealer for details ";
		}
		return sb.toString().replace("  ", " , ");
	}

}
