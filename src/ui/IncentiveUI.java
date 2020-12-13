package ui;

import dao.Special;
import dao.VehicleModel;
import service.CountdownTimeJob;
import service.IncentiveApiImpl;

import javax.swing.*;
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
    //private JLabel disclaimer;
    private JTextArea disclaimerTextArea;
    private JTextArea countdownLabelTextArea;
    private JTextArea discountPeriodTextArea;
    private JTextArea priceAfterDiscountTextArea;
    private JTextArea discountValueTextArea;
    private JTextArea discountTypeTextArea;
    private JTextArea descriptionTextArea;
    private JTextArea titleTextArea;


    public IncentiveUI() {
        initialize();
    }

    private void initialize() {
        final int DEFAULT_FONT_SIZE = 16;
        final Color DEFAULT_COLOR = Color.black;

        this.setLayout(new GridLayout(10, 1));
        this.setPreferredSize(new Dimension(730, 300));
        // create the great label and add the label to panel.
        JLabel greatLabel = new JLabel("<html><font color=orange>&#128663;</font><font color=red>" +
                "Great incentive for you!</font><font color=orange>&#128077;</font></html>");
        greatLabel.setFont(new Font("Serif", Font.BOLD, 36));
        // place the great label on the middle.
        greatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(greatLabel);

        // create the title label and add the label to panel.
        title = new JLabel();
        titleTextArea=new JTextArea(1,25);
        addLabelAndTextArea(title,"Title: ",titleTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,DEFAULT_COLOR);
        //addSingleLabelInOneLine(title, "Title: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Description label and add the label to panel.
        description = new JLabel();
        descriptionTextArea=new JTextArea(1,40);
        addLabelAndTextArea(description,"Description: ", descriptionTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,DEFAULT_COLOR);
        //addSingleLabelInOneLine(description, "Description: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount type label and add the label to panel.
        discountType = new JLabel();
        discountTypeTextArea=new JTextArea(1,38);
        addLabelAndTextArea(discountType, "Discount type: ", discountTypeTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,DEFAULT_COLOR);
        //addSingleLabelInOneLine(discountType, "Discount type: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the Discount value label and add the label to panel.
        /* the value attribute in Special.java represents: how much
        discount the dealer want to give , CANNOT be null
         */
        discountValue = new JLabel();
        discountValueTextArea=new JTextArea(1,25);
        addLabelAndTextArea(discountValue, "Discount value: ", discountValueTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,DEFAULT_COLOR);
        //addSingleLabelInOneLine(discountValue, "Discount value: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR);

        // create the price after discount label and add the label to panel.
        priceAfterDiscount = new JLabel();
        priceAfterDiscountTextArea=new JTextArea(1,25);
        addLabelAndTextArea(priceAfterDiscount,"Price after discount: ", priceAfterDiscountTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,Color.red);
        // addTwoLabelsInOneLine(new JLabel(), "Price after discount: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
        //priceAfterDiscount, "$", DEFAULT_FONT_SIZE, Color.red);

        // create the countdown label and add the label to panel.
        countdownLabel = new JLabel();
        //addTwoLabelsInOneLine(new JLabel(), "Ends in: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
        //countdownLabel, countdownLabel.getText(), DEFAULT_FONT_SIZE, Color.red);
        countdownLabelTextArea =new JTextArea(1,25);
        addLabelAndTextArea(countdownLabel,"Ends in:", countdownLabelTextArea,DEFAULT_FONT_SIZE,DEFAULT_COLOR,Color.red);

        // create the Discount period label and add the label to panel.
        discountPeriod = new JLabel();
        discountPeriodTextArea=new JTextArea(1,25);
        addLabelAndTextArea(discountPeriod, "Discount period: ", discountPeriodTextArea,DEFAULT_FONT_SIZE, DEFAULT_COLOR,Color.orange);
        //addTwoLabelsInOneLine(new JLabel(), "Discount period: ", DEFAULT_FONT_SIZE, DEFAULT_COLOR,
        //      discountPeriod, "", DEFAULT_FONT_SIZE, Color.orange);

        // create the disclaimer label and add the label to panel.
        //disclaimer = new JLabel();
        // addSingleLabelInOneLine(disclaimer, "Disclaimer: ", 12, Color.gray);
        //disclaimer = new JLabel();
        disclaimerTextArea =new JTextArea(5,70);
        addTextArea(disclaimerTextArea,12,Color.gray);
        //addLabelAndTextArea(disclaimer,"Disclaimer: ", disclaimerTextArea,12,Color.gray,Color.gray);
        // creat the disclaimer textarea and add the textarea to panel.
    }

    //add label and label and textArea in one line
    private void addLabelAndTextArea(JLabel label, String message,
                                     JTextArea textArea, int fontSize, Color color1,Color color2){
        label.setText(message);
        // set the label's font.
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        // set the label's fore ground color.
        label.setForeground(color1);
        //textArea
        textArea.setFont(new Font("Serif",Font.BOLD,fontSize));
        textArea.setSelectedTextColor(color2);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(color2);
        textArea.setOpaque(false);


        JPanel smallPanel = new JPanel();
        smallPanel.add(label);
        smallPanel.add(textArea);
        // smallPanel.add(new JScrollPane(textArea));
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(smallPanel);
    }

    private void addTextArea(JTextArea textArea, int fontSize, Color color){

        textArea.setFont(new Font("Serif",Font.BOLD,fontSize));
        textArea.setSelectedTextColor(color);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(color);
        textArea.setOpaque(false);


        JPanel smallPanel = new JPanel();

        smallPanel.add(textArea);
        // smallPanel.add(new JScrollPane(textArea));
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(smallPanel);
    }

/*
    private void addSingleLabelInOneLine(JLabel label, String message, int fontSize, Color color) {
        // set the text to the label.
        label.setText(message);
        // set the label's font.
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        // set the label's fore ground color.
        label.setForeground(color);
        label.setPreferredSize(new Dimension(700,50));
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
        label1.setPreferredSize(new Dimension(150,50));
        // create the 2nd label and set the properties.
        label2.setText(message2);
        label2.setFont(new Font("Serif", Font.BOLD, fontSize2));
        label2.setForeground(color2);
        label2.setPreferredSize(new Dimension(400,80));

        // use a panel to hold the two labels in one line.
        JPanel smallPanel = new JPanel();
        smallPanel.add(label1);
        smallPanel.add(label2);
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(smallPanel);
    }

    private void addSingleTextAreaInOneLine(JTextArea textArea, String message, int fontSize, Color color) {
        // set the text.
        textArea.setText(message);
        // set the font.
        textArea.setFont(new Font("Serif", Font.BOLD, fontSize));
        // set the fore ground color.
        textArea.setForeground(color);
        // ???
        textArea.setLineWrap(true);

        // the reason I create a panel is to make the label become
        // left-justified with the two labels in one line.
        JPanel smallPanel = new JPanel();
        smallPanel.add(textArea);
        smallPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(smallPanel);
    }*/

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CountdownTimeJob) {
            CountdownTimeJob job = (CountdownTimeJob) arg;
            //countdownLabel.setText(job.getCountdownText());
            countdownLabelTextArea.setText(job.getCountdownText());

            VehicleModel VehicleModel = job.getVehicleModel();
            Special special = VehicleModel.getSpecial();

            //title.setText("Title: "+ special.getTitle());
            titleTextArea.setText(special.getTitle());
            //description.setText("Description: " + special.getDescription());
            descriptionTextArea.setText(special.getDescription());

            //discountType.setText("Discount type: " + VehicleModel.getIncentiveType()+IncentiveApiImpl.incentiveAppliedOn(special));
            //discountTypeTextArea.setText(VehicleModel.getIncentiveType()+IncentiveApiImpl.incentiveAppliedOn(special));
            discountTypeTextArea.setText(IncentiveApiImpl.incentiveAppliedOn(special));
            // this could be discount value or discount percentage.
            if (special.getDiscountValue() > 0) {
                //discountValue.setText("Discount value: " + special.getDiscountValue());
                discountValueTextArea.setText("$"+special.getDiscountValue());
            } else if (special.getDiscountPercent() > 0) {
                //discountValue.setText("Discount percentage: " + special.getDiscountPercent());
                discountValueTextArea.setText(special.getDiscountPercent() + "%");
            }
            //priceAfterDiscount.setText(VehicleModel.getSpecialPrice() + "");
            priceAfterDiscountTextArea.setText("$"+VehicleModel.getSpecialPrice());
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            //discountPeriod.setText(sdf.format(special.getStartDate()) + " to " + sdf.format(special.getEndDate()));
            discountPeriodTextArea.setText(sdf.format(special.getStartDate()) + " to " + sdf.format(special.getEndDate()));
            // ??? to be deleted.
            String disMess = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam corrupti sed iste! Ea deleniti perspiciatis minima dolorem laborum ut voluptatibus rem minus, quibusdam architecto voluptatum, quos omnis vel, incidunt aliquid!\n" +
                    "    Amet, repudiandae. Voluptas, architecto pariatur nihil quasi reprehenderit modi fuga unde quaerat beatae, qui fugit amet assumenda sed sequi magnam eaque porro hic dolores earum quisquam inventore libero. Voluptates, optio!\n" +
                    "    Libero tempora voluptas dicta, dolorem tenetur voluptates qui maxime aliquam totam sit beatae quas, nostrum velit expedita? Iusto rem officia, mollitia veritatis omnis id repudiandae quisquam quod distinctio veniam necessitatibus.";
            //disclaimer.setText("Disclaimer: " + disMess);
            //disclaimer.setText("Disclaimer: " + special.getDisclaimer());
            //disclaimer.setText("Disclaimer: " );
            disclaimerTextArea.setText(special.getDisclaimer());
            //disclaimerTextArea.setText("Disclaimer: "+disMess);
        }
        repaint();
    }
}