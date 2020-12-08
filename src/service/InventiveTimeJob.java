package service;

import dao.Special;

import java.util.Date;
import java.util.Observable;

public class InventiveTimeJob extends Observable implements Runnable {

    private Thread thread;
    private boolean isDone;

    private Date endDate;
    private String countdownText;
    private Special special;

    public String getCountdownText() {
        return countdownText;
    }
    public Special getSpecial() {
        return special;
    }

    public void start(Special special) {
        this.special = special;
        endDate = special.getEndDate();

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
            // get current time.
            Date now = new Date();
            // counting the time between current time and the endDate.
            long time = (endDate.getTime() - now.getTime()) / 1000;

            // if it is at the endDate, dispose the whole frame,
            // because at this time the sales promotion is ended.
            if (time < 0) {
                break;
            }

            // counting the remaining days, hours, minutes and seconds according to the time.
            int days = (int) (time / (60 * 60 * 24));
            int hours = (int) ((time % (60 * 60 * 24)) / (60 * 60));
            int minutes = (int) ((time % (60 * 60)) / 60);
            int seconds = (int) (time % 60);


            // update the text on countdownLabel.
            countdownText = setTimeStyle(days) + "Days" + setTimeStyle(hours) + "Hours"
                    + setTimeStyle(minutes) + "Minutes" + setTimeStyle(seconds) + "Seconds";

            setChanged();
            notifyObservers(this);

            try {
                // update the text on countdownLabel every second.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // set the style of time showing, if I do not do so, then,
    // the countdownLabel may show like this: 12Seconds and 2Seconds,
    // I think 02Seconds seems better than 2Seconds.
    private String setTimeStyle(int num) {
        return num <= 9 ? "0" + num : "" + num;
    }
}
