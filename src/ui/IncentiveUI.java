package ui;

import javax.swing.*;
import java.awt.*;

public class IncentiveUI {
    JFrame frame;

    public IncentiveUI() {
        initialize();
    }

    private void initialize() {
        final int DEFAULT_WIDTH = 600;
        final int DEFAULT_HEIGHT = 300;

        JFrame frame = new JFrame();
        frame.setTitle("Incentive details");
        // set the width and height of JFrame.
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // place the JFrame on the center of the screen.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        frame.add(panel);

        // create the great label.
        // ??? font
        String greatMessage = "Great incentive for you!";
        JLabel greatLabel = new JLabel(greatMessage);
        // set the message red.
        greatLabel.setForeground(Color.red);
        // set the font.
        greatLabel.setFont(new Font("Serif", Font.BOLD, 36));
        // place the great label on the middle.
        greatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(greatLabel);

        // create the title label
        // ??? how to pass title to it.
        String titleMessage = "Title: " + "XXXXX";
        JLabel titleLabel = new JLabel(titleMessage);
        // set the font.
        titleLabel.setFont(new Font("Serif", 1, 24));
        panel.add(titleLabel);

        // create the Description label
        // ??? how to pass data to it.
        String descriptionMessage = "Description: " + "XXXXX";
        JLabel descriptionLabel = new JLabel(descriptionMessage);
        // set the font.
        descriptionLabel.setFont(new Font("Serif", 1, 24));
        panel.add(descriptionLabel);

        // create the Discount type label
        // ??? how to pass data to it.
        String discountTypeMessage = "Discount type: " + "XXXXX";
        JLabel discountTypeLabel = new JLabel(discountTypeMessage);
        // set the font.
        discountTypeLabel.setFont(new Font("Serif", 1, 24));
        panel.add(discountTypeLabel);

        // create the Discount value label
        // ??? how to pass data to it.
        String discountValueMessage = "Discount value: " + "XXXXX";
        JLabel discountValueLabel = new JLabel(discountValueMessage);
        // set the font.
        discountValueLabel.setFont(new Font("Serif", 1, 24));
        panel.add(discountValueLabel);

        // create the Discount period label
        // ??? how to pass data to it.
        String discountPeriodMessage = "Discount period: " + "MM/DD/YYYY - MM/DD/YYYY";
        JLabel discountPeriodLabel = new JLabel(discountPeriodMessage);
        // set the font.
        discountPeriodLabel.setFont(new Font("Serif", 1, 24));
        panel.add(discountPeriodLabel);

        // create the disclaimer label
        // ??? how to pass data to it.
        String disclaimerMessage = "Disclaimer: " + "XXXXXXXXXXXXXXXXXXXXXX"
                + "\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        JLabel disclaimerLabel = new JLabel(disclaimerMessage);
        // set the font.
        disclaimerLabel.setFont(new Font("Serif", 1, 12));
        panel.add(disclaimerLabel);
    }

}
