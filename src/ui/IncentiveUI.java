package ui;

import dao.Special;
import dao.VehicleModel;
import service.InventiveTimeJob;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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

        // ??? need to count how many rows.
        this.setLayout(new GridLayout(9, 1));

        // create the great label and add the label to panel.
        /* ??? I tried to add a picture to the label, but failed because
        the size of the image could not change with the whole panel.
        Any solutions?
         */
//        String path = new File("").getAbsolutePath() + "/src/ui/pictures/car.png";
//        ImageIcon img = new ImageIcon(path);
//        URL carImgURL = getClass().getResource("/car.png");
//        JLabel greatLabel = new JLabel("<html><font color=orange><img src='" + carImgURL + "'></font><font color=red>" +
//                "Great incentive for you!</font><font color=orange>&#128077;</font></html>");
        JLabel greatLabel = new JLabel("<html><font color=orange>&#128663;</font><font color=red>" +
                "Great incentive for you!</font><font color=orange>&#128077;</font></html>");
//        // set the font of great label.
        greatLabel.setFont(new Font("Serif", Font.BOLD, 48));
        // place the great label on the middle.
        greatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // the great label to panel.
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
        discountValue = new JLabel();
        addSingleLabelInOneLine(discountValue, "Discount value: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);


        // create the price after discount label and add the label to panel.
        priceAfterDiscount = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Price after discount: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                priceAfterDiscount, "$", 28, Color.red);


        // create the countdown label and add the label to panel.
        countdownLabel = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Ends in: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                countdownLabel, countdownLabel.getText(), 28, Color.red);


        // create the discount period label and add the label to panel.
        discountPeriod = new JLabel();
        addTwoLabelsInOneLine(new JLabel(), "Discount period: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
                discountPeriod, "", 28, Color.orange);


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

        /* the reason I create a panel is to make the label become
        left-justified with the two labels in one line.
         */
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
        if (arg instanceof InventiveTimeJob) {
            InventiveTimeJob job = (InventiveTimeJob) arg;

            VehicleModel vehicleModel = job.getVehicleModel();
            Special special = vehicleModel.getSpecial();

            // set the text of countdownLabel.
            countdownLabel.setText(job.getCountdownText());

            // set the text of title.
            title.setText("Title: "+ special.getTitle());
            // set the text of description. ??? failed to read the data.
            description.setText("Description: " + special.getDescription());
            // set the text of discountType.
            discountType.setText("Discount type: " + vehicleModel.getIncentiveType());
            // set the text of discountValue.
            discountValue.setText("Discount value: " + special.getDiscountValue() );
            // set the text of priceAfterDiscount.
            priceAfterDiscount.setText("$" + vehicleModel.getSpecialPrice());
            // set the text of discountPeriod.
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            discountPeriod.setText(sdf.format(special.getStartDate()) + " to " + sdf.format(special.getEndDate()));
            // set the text of disclaimer. ??? failed to read the data.
            disclaimer.setText("Disclaimer: " + special.getDisclaimer());
        }
        repaint();
    }


}
