package dao;

import java.util.ArrayList;
import java.util.List;

public class testMain {
    public static void main(String args[]) throws Exception {
        Customer testCustomer = new Customer("123456","ben","gao");
        //Notification testNoti = new Notification("123456","123456 has bought the car","2020-12-11 10:30:30",false);
        NotiDaoImpl testDataBase = new NotiDaoImpl();
        testDataBase.insertNotification("test_run",testCustomer);
        /*
        List<Notification> testget = testDataBase.findAllNotification(testCustomer);
        for(Notification cont:testget){
            System.out.println(cont.getContent());
        }""   //test findallnotification
        */
        //testDataBase.markRead(testCustomer);
        //testDataBase.markReadAll(testCustomer);
        //testDataBase.markUnread(testCustomer);
        //testDataBase.deleteNotificationAll(testCustomer);
        //CarDaoImpl testCar = new CarDaoImpl();
        //testCar.insertVehicle("199729","654321",testCustomer);
        /*List<Vehicle> carList = testCar.findAllVehicle(testCustomer);
        for(Vehicle temp:carList){
            System.out.println(temp.getVehicleId());
        }*/
        //testCar.deleteVehicle("895472",testCustomer);
        //DealerDaoImpl dealertest = new DealerDaoImpl();
        //dealertest.insertDealer("235353","Tom",testCustomer);
        /*
        List<Dealer> dealerList= dealertest.findAllInventory(testCustomer);
        for(Dealer temp:dealerList){
            System.out.println(temp.getDealerName());
        }

         */
        //dealertest.deleteDealer("654321",testCustomer);
    }
}
