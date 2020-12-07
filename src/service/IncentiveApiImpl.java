package service;

import dao.Special;
import dao.Vehicle;
import dto.AbstractPersistent;
import dto.DataPersistence;

import java.util.Calendar;
import java.util.Date;

public final class IncentiveApiImpl implements IncentiveApi {

    private final AbstractPersistent dao = new DataPersistence();

    @Override
    /**
     * case 2
     */
    public Vehicle showSpecialPrice(Vehicle vehicle) {
        // read incentive from dao


        // find a certain incentive rule: today is between startdate and enddate


        // check vehicleFilter


        // calc special price


        // vehicleVO setter -> change price


        return vehicle; // do not have special price in vehicleVO
    }

    @Override
    /**
     * UI for case6(pop-up)
     */
    public Special showIncentive(String id) {
        // read incentiveVO from dao(database)
//        return new Special();
        // ??? just for demo, should be deleted for the final version.
        Special obj = new Special();
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.DAY_OF_MONTH, -5);
        Calendar calEnd = Calendar.getInstance();
//        calEnd.add(Calendar.DAY_OF_MONTH, 10);
        calEnd.add(Calendar.SECOND, 15);
        obj.setStartDate(calStart.getTime());
        obj.setEndDate(calEnd.getTime());
        obj.setTitle("Incentive demo");
        obj.setDescription("Demo description  XXXXXX");
        obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        obj.setValue("500");
        obj.setBrand("Honda");
        return obj;
    }
}
