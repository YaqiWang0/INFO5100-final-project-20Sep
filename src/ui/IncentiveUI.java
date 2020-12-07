package ui;

import dao.Special;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IncentiveUI {

    private JFrame frame;
    private JPanel panel;
    // get data from showIncentive() in IncentiveApiImpl.
    private Special special;

    public IncentiveUI(Special special) {
        this.special = special;
        initialize(special);
    }

    public void showUI() {
//        EventQueue.invokeLater(() -> {
//            frame.setVisible(true);
//        });
        if (!salesIsEnded()) {
            frame.pack();
            // place the JFrame on the center of the screen.
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // if we press the closing button, just hide the frame.
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        } else {
            frame.dispose();
        }
    }

    private void initialize(Special special) {
//        final int DEFAULT_WIDTH = 500;
//        final int DEFAULT_HEIGHT = 500;
        final int DEFAULT_FONT_SIZE = 20;
        final Color DEFAULT_COLOR = Color.black;

        frame = new JFrame();
        frame.setTitle("Incentive details");
//        // set the width and height of JFrame.
//        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // create a panel.
        panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));
        frame.add(panel);

        // create the great label and add the label to panel.
        String greatMessage = "Great incentive for you!";
        JLabel greatLabel = new JLabel(greatMessage);
        greatLabel.setForeground(Color.red);
        greatLabel.setFont(new Font("Serif", Font.BOLD, 36));
//        addSingleLabelInOneLine(new JLabel(), greatMessage, 36, Color.red);
        // place the great label on the middle.
        greatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(greatLabel);

        // create the title label and add the label to panel.
        String titleMessage = "Title: " + special.getTitle();
        addSingleLabelInOneLine(new JLabel(), titleMessage, DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Description label and add the label to panel.
        String descriptionMessage = "Description: " + special.getDescription();
        addSingleLabelInOneLine(new JLabel(), descriptionMessage, DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount type label and add the label to panel.
        // ??? there is no discount type in Special.java.
        String discountTypeMessage = "Discount type: " + "XXXXXX";
        addSingleLabelInOneLine(new JLabel(), discountTypeMessage, DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount value label and add the label to panel.
        // the value attribute in Special.java represents:
        // how much discount the dealer want to give , CANNOT be null
        String discountValueMessage = "Discount value: " + special.getValue();
        addSingleLabelInOneLine(new JLabel(), discountValueMessage, DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount type label and add the label to panel.
        // ??? there is no price after discount in Special.java.
        String priceAfterDiscount = "$XXXXXX";
        addTwoLabelsInOneLine(new JLabel(), "Price after discount: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                new JLabel(), priceAfterDiscount, 28, Color.red);

        // create a countdown label and add the label to panel.
        JLabel countdownLabel = new JLabel();
            Date endDate = special.getEndDate();
//            Date endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(new IncentiveApiImpl().showIncentive("").getEndDate() + " 23:59:59");
//            Date endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(" 2020/12/04 23:35:00");
        countingDown(countdownLabel, endDate);
        addTwoLabelsInOneLine(new JLabel(), "Ends in: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                countdownLabel, "", 28, Color.red);

        // create the Discount period label and add the label to panel.
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        String discountPeriod = sdf.format(special.getStartDate()) + " to " + sdf.format(special.getEndDate());
        addTwoLabelsInOneLine(new JLabel(), "Discount period: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                new JLabel(), discountPeriod, 28, Color.orange);

        // create the disclaimer label and add the label to panel.
        String disclaimerMessage = "Disclaimer: " + special.getDisclaimer();
        addSingleLabelInOneLine(new JLabel(), disclaimerMessage, 12, Color.gray);

    }

    private void addSingleLabelInOneLine(JLabel label, String message, int fontSize, Color color) {
        // set the text to the label.
        label.setText(message);
        // set the label's font.
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        // set the label's fore ground color.
        label.setForeground(color);

        // the reason I create a panel is to make the label become
        // left-justified with the two labels in one line.
        JPanel smallPanel = new JPanel();
        smallPanel.add(label);
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(smallPanel);
    }

    private void addTwoLabelsInOneLine(JLabel label1, String message1, int fontSize1, Color color1,
                                       JLabel label2, String message2, int fontSize2, Color color2) {
        // create the 1st label and set the properties.
        label1.setText(message1);
        label1.setFont(new Font("Serif", Font.BOLD, fontSize1));
        label1.setForeground(color1);

        // create the 2nd label and set the properties.
        label2.setText(message2);
        label2.setFont(new Font("Serif", Font.BOLD, fontSize2));
        label2.setForeground(color2);

        // use a panel to hold the two labels in one line.
        JPanel smallPanel = new JPanel();
        smallPanel.add(label1);
        smallPanel.add(label2);
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(smallPanel);
    }

    // set the style of time showing, if I do not do so, then,
    // the countdownLabel may show like this: 12Seconds and 2Seconds,
    // I think 02Seconds seems better than 2Seconds.
    private String setTimeStyle(int num) {
        return num <= 9 ? "0" + num : "" + num;
    }

    // to update the text on countdownLabel.
    private void countingDown(JLabel countdownLabel, Date endDate) {
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
                        frame.dispose();
                        break;
                    }

                    // counting the remaining days, hours, minutes and seconds according to the time.
                    int days = (int) (time / (60 * 60 * 24));
                    int hours = (int) ((time % (60 * 60 * 24)) / (60 * 60));
                    int minutes = (int) ((time % (60 * 60)) / 60);
                    int seconds = (int) (time % 60);

                    // update the text on countdownLabel.
                    countdownLabel.setText(setTimeStyle(days) + "Days" + setTimeStyle(hours) + "Hours" 
                            + setTimeStyle(minutes) + "Minutes" + setTimeStyle(seconds) + "Seconds");

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

    // to determine whether the sales is ended or not.
    public boolean salesIsEnded() {
        Date now = new Date();
        return now.getTime() >= special.getEndDate().getTime();
    }
}
