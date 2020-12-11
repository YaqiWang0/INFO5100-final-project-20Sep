package service;

import dao.Special;
import dao.VehicleModel;

import java.util.Date;
import java.util.Observable;

public class InventiveTimeJob extends Observable implements Runnable {

    private Thread thread;
    private boolean isDone;

    private Date endDate;
    private String countdownText;
    private VehicleModel VehicleModel;

    public String getCountdownText() {
        return countdownText;
    }
    public VehicleModel getVehicleModel() {
        return VehicleModel;
    }

    public void start(VehicleModel VehicleModel) {
        this.VehicleModel = VehicleModel;
        endDate = VehicleModel.getSpecial().getEndDate();

        isDone = false;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (thread == null) return;
        isDone = true;
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void run() {
        runLoop();
        thread = null;
    }

    private void runLoop() {
        while (!isDone) {
            Date now = new Date();
            long time = (endDate.getTime() - now.getTime()) / 1000;

            int seconds = (int) time % 60;
            int minutes = (int) time / 60;
            int hours = minutes / 60;
            int days = hours / 24;

            countdownText = setTimeStyle(days) + "Days" + setTimeStyle(hours) + "Hours"
                    + setTimeStyle(minutes) + "Minutes" + setTimeStyle(seconds) + "Seconds";

            if (time <= 0)
                countdownText = "Expired";

            setChanged();
            notifyObservers(this);

            if (time < 0)
                break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * style: 00Days00Hours00Minutes00Seconds
     * @param num
     * @return
     */
    private String setTimeStyle(int num) {
        return num <= 9 ? "0" + num : "" + num;
    }
}