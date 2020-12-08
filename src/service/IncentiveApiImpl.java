package service;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

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
		if (sd.equals(ed) || ed.equals(ct)) {
			return true;
		}
		return startDate.before(currentTime) && endDate.after(currentTime);
	}


	// read price after discount here
	// Can an incentive be both percent and value discount?
	// Vehicle: price 的 string 是只是有数字还是有别的? integer or float?

	/*
	 * Assumption: 1. An incentive can not be percentage discount and direct
	 * discount at the same time 2. Vehicle: Price string only contains float
	 * numbers
	 */
	@Override
	public VehicleModel updateSpecialPrice(Vehicle vehicle) {
		// get vehicle id from

		// read all incentive from dao

		// find a certain incentive rule

		// calc special price

		// save all UI necessary value to VehicleModel

		VehicleModel model = new VehicleModel(vehicle, getSpecialTest(vehicle.getVehicleId()));
		model.setSpecialPrice(12.56);
		return model;
	}

	// ??? to be deleted later.
	public VehicleModel updateSpecialPrice2(Vehicle vehicle) {
		VehicleModel model = new VehicleModel(vehicle, getSpecialTest2(vehicle.getVehicleId()));
		model.setSpecialPrice(20.1);
		return model;
	}

	// ??? to be deleted later.
	public VehicleModel updateSpecialPrice3(Vehicle vehicle) {
		VehicleModel model = new VehicleModel(vehicle, getSpecialTest3(vehicle.getVehicleId()));
		model.setSpecialPrice(9.99);
		return model;
	}

	// read incentive types here
	@Override
	public String incentiveType(Special s) {

		StringBuilder sb = new StringBuilder();
		if (s.getIsValidOnCashPayment() == true) {
			sb.append(" Cash payment discount ");
		}
		if (s.getIsValidOnCheckPayment() == true) {
			sb.append(" Check payment discount ");
		}
		if (s.getIsValidOnLease() == true) {
			sb.append(" Lease discount ");
		}
		if (s.getIsValidOnLoan() == true) {
			sb.append(" Loan discount ");
		}
		if (sb.length() == 0) {
			return " No special price for this vehicle at this moment ";

		}
		return sb.toString().replace("  ", " & ");
	}


	/**
	 * // TODO: remove after finished
	 * only for test
	 * @param specialId
	 * @return
	 */
    private Special getSpecialTest(String specialId) {

        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.DAY_OF_MONTH, -5);
        Calendar calEnd = Calendar.getInstance();
        calEnd.add(Calendar.SECOND, 15);

		Special obj = new Special();
        obj.setStartDate(calStart.getTime());
        obj.setEndDate(calEnd.getTime());
        obj.setTitle("Incentive demo" + specialId);
        obj.setDescription("Demo description  XXXXXX");
        obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        obj.setValue("500");
        obj.setBrand("Honda");
        return obj;
    }

    // ??? to be deleted later.
	private Special getSpecialTest2(String specialId) {

		Calendar calStart = Calendar.getInstance();
		calStart.add(Calendar.DAY_OF_MONTH, -5);
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 15);

		Special obj = new Special();
		obj.setStartDate(calStart.getTime());
		obj.setEndDate(calEnd.getTime());
		obj.setTitle("Incentive demo" + specialId);
		obj.setDescription("Demo description  XXXXXX");
		obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		obj.setValue("700");
		obj.setBrand("BENZ");
		return obj;
	}

	// ??? to be deleted later.
	private Special getSpecialTest3(String specialId) {

		Calendar calStart = Calendar.getInstance();
		calStart.add(Calendar.DAY_OF_MONTH, -5);
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 1);

		Special obj = new Special();
		obj.setStartDate(calStart.getTime());
		obj.setEndDate(calEnd.getTime());
		obj.setTitle("Incentive demo" + specialId);
		obj.setDescription("Demo description  XXXXXX");
		obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		obj.setValue("700");
		obj.setBrand("BMW");
		return obj;
	}
}
