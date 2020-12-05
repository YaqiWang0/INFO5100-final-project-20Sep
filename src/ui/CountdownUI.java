package ui;

import service.IncentiveApiImpl;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownUI {
    private JFrame frame;
    private JLabel countdownLabel;
    private Date endDate;

    public CountdownUI(JFrame frame, Date endDate) {
        this.frame = new JFrame();
        this.countdownLabel = new JLabel();
        this.endDate = endDate;
    }

    private String setTimeStyle(int time) {
        return time <= 9 ? "0" + time : "" + time;
    }

    public void countingDown() {
        new Thread() {
            public void run() {
                while (true) {
                    // get current time.
                    Date now = new Date();
                    // counting the time between current time and the endDate.
                    long time = (endDate.getTime() - now.getTime()) / 1000;

                    // if it is at the endDate, dispose the whole frame,
                    // because at this time the sales promotion is ended.
                    if (time <= 0) {
//                        throw new SalesPromotionEndedException();
                        frame.dispose();
                        break;
                    }

                    // counting the remaining days, hours, minutes and seconds according to the time.
                    int days = (int) (time / (60 * 60 * 24));
                    int hours = (int) ((time % (60 * 60 * 24)) / (60 * 60));
                    int minutes = (int) ((time % (60 * 60)) / 60);
                    int seconds = (int) (time % 60);

                    // update the text on countdownLabel.
                    countdownLabel.setText(setTimeStyle(days) + "Days" + setTimeStyle(hours) + "Hours" + setTimeStyle(minutes) + "Minutes" + setTimeStyle(seconds) + "Seconds");

                    try {
                        // update the text on countdownLabel every second.
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public JLabel getCountdownLabel() {
        return countdownLabel;
    }

//    class SalesPromotionEndedException extends Exception {
//        public SalesPromotionEndedException() {
//        }
//    }

}
