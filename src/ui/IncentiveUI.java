package ui;

import dao.Special;
import dao.VehicleModel;
import service.CountdownTimeJob;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class IncentiveUI extends JPanel implements Observer {

    private JLabel title;
    private JLabel description;
    private JLabel discountType;
    private JLabel discountValue;
    private JLabel priceAfterDiscount;
    private JLabel countdownLabel;
    private JLabel discountPeriod;
    private JLabel disclaimer;

    public IncentiveUI() {
        initialize();
    }

    private void initialize() {
        final int DEFAULT_FONT_SIZE = 20;
        final Color DEFAULT_COLOR = Color.black;

        this.setLayout(new GridLayout(9, 1));

        // create the great label and add the label to panel.
        JLabel greatLabel = new JLabel("<html><font color=orange>&#128663;</font><font color=red>" +
                "Great incentive for you!</font><font color=orange>&#128077;</font></html>");
        greatLabel.setFont(new Font("Serif", Font.BOLD, 36));
        // place the great label on the middle.
        greatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(greatLabel);

        // create the title label and add the label to panel.
        title = new JLabel();
        addSingleLabelInOneLine(title, "Title: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Description label and add the label to panel.
        description = new JLabel();
        addSingleLabelInOneLine(description, "Description: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount type label and add the label to panel.
        discountType = new JLabel();
        addSingleLabelInOneLine(discountType, "Discount type: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount value label and add the label to panel.
        /* the value attribute in Special.java represents: how much
        discount the dealer want to give , CANNOT be null
         */
        discountValue = new JLabel();
        addSingleLabelInOneLine(discountValue, "Discount value: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the price after discount label and add the label to panel.
        priceAfterDiscount = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Price after discount: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                priceAfterDiscount, "$", DEFAULT_FONT_SIZE, Color.red);

        // create the countdown label and add the label to panel.
        countdownLabel = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Ends in: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                countdownLabel, countdownLabel.getText(), DEFAULT_FONT_SIZE, Color.red);

        // create the Discount period label and add the label to panel.
        discountPeriod = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Discount period: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                discountPeriod, "", DEFAULT_FONT_SIZE, Color.orange);

        // create the disclaimer label and add the label to panel.
        disclaimer = new JLabel();
        addSingleLabelInOneLine(disclaimer, "Disclaimer: ", 12, Color.gray);

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

        this.add(smallPanel);
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

        this.add(smallPanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CountdownTimeJob) {
            CountdownTimeJob job = (CountdownTimeJob) arg;
            countdownLabel.setText(job.getCountdownText());

            VehicleModel VehicleModel = job.getVehicleModel();
            Special special = VehicleModel.getSpecial();

            title.setText("Title: "+ special.getTitle());
            description.setText("Description: " + special.getDescription());
            discountType.setText("Discount type: " + VehicleModel.getIncentiveType());
            // this could be discount value or discount percentage.
            if (special.getDiscountValue() != 0) {
                discountValue.setText("Discount value: " + special.getDiscountValue());
            } else if (special.getDiscountPercent() != 0) {
                discountValue.setText("Discount percentage: " + special.getDiscountPercent());
            }
            priceAfterDiscount.setText(VehicleModel.getSpecialPrice() + "");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            discountPeriod.setText(sdf.format(special.getStartDate()) + " to " + sdf.format(special.getEndDate()));
            disclaimer.setText("Disclaimer: " + special.getDisclaimer());
        }
        repaint();
    }
}