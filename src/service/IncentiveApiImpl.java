package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dao.Special;
import dao.Vehicle;
import dto.AbstractPersistent;
import dto.DataPersistence;

public final class IncentiveApiImpl implements IncentiveApi {

	private final AbstractPersistent dao = new DataPersistence();

	public static Date getCurrentTime() {
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

	@Override
	//read price after discount here
	//Assumption: an incentive can not be percentage discount and direct discount at the same time
	public Vehicle updateSpecialPrice (String specialId) {
		// read incentive from dao
		Special s = new Special();

		// find a certain incentive rule: today is between startdate and enddate
		if (timeCheck(s.getStartDate(), s.getEndDate(), getCurrentTime())) {
			// check vehicleFilter: by case 5, pending

			// calc special price

			// vehicleVO setter -> change price
			return new Vehicle();
		} else {
			return new Vehicle(); // do not have special price in vehicleVO
		}

	}

	
	// read title, description, discount value(percentage) and dates here
	@Override
	public Special getSpecial(String specialId) {
		/* TODO: test dao
		List<Special> list = dao.getAllSpecials();

		for (int i = 0; i < list.size(); i++) {
			if (specialId.equals(list.get(i).getSpecialId())) {

		 */
				// TODO: test code
				Special obj = new Special();
				Calendar calStart = Calendar.getInstance();
				calStart.add(Calendar.DAY_OF_MONTH, -5);
				Calendar calEnd = Calendar.getInstance();
				calEnd.add(Calendar.SECOND, 5);
				obj.setStartDate(calStart.getTime());
				obj.setEndDate(calEnd.getTime());
				obj.setTitle("Incentive demo");
				obj.setDescription("Demo description  XXXXXX");
				obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				obj.setValue("500");
				obj.setBrand("Honda");
				return obj;

		/*	TODO: test dao
				return list.get(i);
			}
		}
		return null;
		*/
	}

	// read incentive types here
	@Override
	public String incentiveType(String specialId) {
		Special s = getSpecial(specialId);
		// in case that one special has multiple types.
		StringBuilder sb = new StringBuilder();
		if (s.getIsValidOnCashPayment() == true) {
			sb.append("Cash payment discount ");
		}
		if (s.getIsValidOnCheckPayment() == true) {
			sb.append("Check payment discount ");
		}
		if (s.getIsValidOnLease() == true) {
			sb.append("Lease discount ");
		}
		if (s.getIsValidOnLoan() == true) {
			sb.append("Loan discount ");
		}
		if (sb.length() == 0) {
			return "No special price for this vehicle at this moment";
		}
		return sb.toString().replace("  ", " & ");
	}

}