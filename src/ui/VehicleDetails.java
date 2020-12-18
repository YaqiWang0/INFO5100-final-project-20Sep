package ui;

//import dao.VehicleDetailsObj;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.image.BufferedImage;
        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileReader;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Scanner;

        import javax.imageio.ImageIO;
        import javax.swing.*;
        import javax.swing.plaf.ColorUIResource;

//import AutomotiveDealer.VehicleDetails;

/**
 * Override JFrame
 */
public class VehicleDetails extends JFrame{

    public JFrame frame;
    private JTextField typeTxt;
    private JTextField modelYearTxt;
    private JTextField priceTxt;
    private JTextField incentiveTxt;
    private JTextField taxTxt;
    private JTextField totalTxt;
    private JTextField engineTxt;
    private JTextField conditionTxt;
    private static String[] data;
    private static String defaultData = "2228104413~gmps-aj-dohmann~new~2014~VolksWagen~CTS Sedan~3.6L V6 AWD Luxury~CAR~517620.0~http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg";
    private JTextField modelTxt;
    private JLabel image;

    //    /*------------------------------------------------------------------------*/
    public static void main(String[] args) throws Exception {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String str = "2228104413~gmps-aj-dohmann~new~2014~Cadillac~CTS Sedan~3.6L V6 AWD Luxury~CAR~57620.0~http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg";
//                    String[] split = str.split("~");
//                    VehicleDetails details = new VehicleDetails(split);
//                    details.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        //readData();
        //System.out.println(list.get(0).getVehicleId()
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	VehicleDetails window = new VehicleDetails(defaultData.split("~"));
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VehicleDetails(String[] data) {
        if (data == null || data.length == 0) {
            VehicleDetails.data = defaultData.split("~");
        }else {
        	VehicleDetails.data = data;
        }
        
        initialize();
    }

    /* Initialize the contents of the frame.*/

    public static String getIncentives(String selectedVehicle) {
        selectedVehicle = Arrays.toString(data);
        double res = 0;
        if (selectedVehicle.toLowerCase().contains("chevrolet") && selectedVehicle.toLowerCase().contains("new")) {
            res = 1500;
        }

        if (selectedVehicle.toLowerCase().contains("cadillac") && Double.parseDouble(data[8]) > 57000.0) {
            res = 1500;
        }
        return String.valueOf(res);
    }

    public static String getTax(String selectVehicle) {
        selectVehicle = data.toString();
        double res = 0.0;
        double incentives = Double.parseDouble(getIncentives(selectVehicle));
        res = (Double.parseDouble(data[8]) - incentives) * 0.1;

        return String.valueOf(res);
    }

    public static String calculateTotalPrice(String selectVehicle) {
        selectVehicle = data.toString();
        double res = 0.0;
        double incentives = Double.parseDouble(getIncentives(selectVehicle));
        res = Double.parseDouble(data[8]) + Double.parseDouble(getTax(selectVehicle)) - incentives;
        return String.valueOf(res);
    }

    private void initialize() {
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(105,105,105)));
        System.out.println("data = " + Arrays.toString(data));
        for (int i = 0; i < data.length; i++) {
            System.out.println(i + ": " + data[i]);
        }
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(250, 250, 210));
        frame.getContentPane().setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        frame.setBounds(100, 100, 819, 542);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
         
        //Save
		JButton saveButton = new JButton("Save");
		saveButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		saveButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton.setBounds(141, 442, 92, 26);
		frame.getContentPane().add(saveButton);
        
        //Unsave
        JButton unsaveButton = new JButton("Unsave");
        unsaveButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        unsaveButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        unsaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        unsaveButton.setBounds(267, 442, 92, 26);
        frame.getContentPane().add(unsaveButton);
        
        //Request Quote
        JButton reqQuoteBtn = new JButton("Request Quote");
        reqQuoteBtn.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        reqQuoteBtn.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        reqQuoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LeadUI constructor:
                //LeadUI(String,String,String) {
                //
                //
                //}
                //
                String vehicleId = data[0];
                String dealerName = data[1];
                String vehicleYearMakeModel = data[3] + "," + data[4] + "," + data[5];
                new LeadUI(vehicleId,dealerName,vehicleYearMakeModel);
            }
        });
        reqQuoteBtn.setBounds(509, 410, 203, 26);
        frame.getContentPane().add(reqQuoteBtn);

		//Monthly Calc
		JButton calculatorButton = new JButton("Monthly Payment Calculator");
		calculatorButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		calculatorButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		//calculatorButton.addActionListener((ActionEvent) -> new MonthPayCalc());
		calculatorButton.setBounds(509, 442, 203, 26);
		frame.getContentPane().add(calculatorButton);
		calculatorButton.addActionListener((ActionEvent) -> new MonthPayCalc());

        JLabel detailLabel = new JLabel("Car Details");
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setBounds(155, 53, 103, 21);
        frame.getContentPane().add(detailLabel);

        JLabel modelLabel = new JLabel("Model");
        modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        modelLabel.setBounds(140, 217, 45, 13);
        frame.getContentPane().add(modelLabel);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeLabel.setBounds(140, 254, 45, 16);
        frame.getContentPane().add(typeLabel);

        JLabel modelYearLabel = new JLabel("Model Year");
        modelYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        modelYearLabel.setBounds(140, 300, 83, 13);
        frame.getContentPane().add(modelYearLabel);

        modelTxt = new JTextField();
        modelTxt.setEnabled(false);
        modelTxt.setBounds(222, 209, 148, 33);
        frame.getContentPane().add(modelTxt);
        modelTxt.setColumns(10);
        modelTxt.setText(String.valueOf(data[5]));

        typeTxt = new JTextField();
        typeTxt.setEnabled(false);
        typeTxt.setFont(new Font("Tahoma", Font.BOLD, 10));
        typeTxt.setBounds(222, 248, 148, 33);
        frame.getContentPane().add(typeTxt);
        typeTxt.setColumns(10);
        typeTxt.setText(String.valueOf(data[7]));

        modelYearTxt = new JTextField();
        modelYearTxt.setEnabled(false);
        modelYearTxt.setBounds(222, 292, 148, 33);
        frame.getContentPane().add(modelYearTxt);
        modelYearTxt.setColumns(10);
        modelYearTxt.setText(String.valueOf(data[3]));

        JLabel billLabel = new JLabel("Price Details");
        billLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        billLabel.setBounds(509, 53, 111, 21);
        frame.getContentPane().add(billLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        priceLabel.setBounds(509, 126, 45, 13);
        frame.getContentPane().add(priceLabel);

        JLabel incentiveLabel = new JLabel("Incentives");
        incentiveLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        incentiveLabel.setBounds(509, 183, 67, 13);
        frame.getContentPane().add(incentiveLabel);

        JLabel taxLabel = new JLabel("Tax (10%)");
        taxLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        taxLabel.setBounds(509, 234, 100, 13);
        frame.getContentPane().add(taxLabel);

        JLabel totalLabel = new JLabel("Total Price");
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        totalLabel.setBounds(509, 288, 67, 13);
        frame.getContentPane().add(totalLabel);

        priceTxt = new JTextField();
        priceTxt.setEnabled(false);
        priceTxt.setToolTipText("");
        priceTxt.setBounds(593, 118, 122, 33);
        frame.getContentPane().add(priceTxt);
        priceTxt.setColumns(10);
        priceTxt.setText(String.valueOf(data[8]));

        incentiveTxt = new JTextField();
        incentiveTxt.setEnabled(false);
        incentiveTxt.setBounds(593, 175, 122, 33);
        frame.getContentPane().add(incentiveTxt);
        incentiveTxt.setColumns(10);
        incentiveTxt.setText(getIncentives(Arrays.toString(data)));
        //incentiveTxt.setText(String.valueOf(list.get(0).get));

        taxTxt = new JTextField();
        taxTxt.setEnabled(false);
        taxTxt.setBounds(593, 226, 122, 33);
        frame.getContentPane().add(taxTxt);
        taxTxt.setColumns(10);
        taxTxt.setText(getTax(Arrays.toString(data)));

        totalTxt = new JTextField();
        totalTxt.setEnabled(false);
        totalTxt.setBounds(593, 280, 122, 33);
        frame.getContentPane().add(totalTxt);
        totalTxt.setColumns(10);
        totalTxt.setText(calculateTotalPrice(Arrays.toString(data)));

        JLabel engineLabel = new JLabel("Engine");
        engineLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        engineLabel.setBounds(140, 338, 67, 21);
        frame.getContentPane().add(engineLabel);

        JLabel conditionLabel = new JLabel("Condition");
        conditionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        conditionLabel.setBounds(140, 379, 67, 21);
        frame.getContentPane().add(conditionLabel);

        engineTxt = new JTextField();
        engineTxt.setEnabled(false);
        engineTxt.setBounds(222, 335, 148, 31);
        frame.getContentPane().add(engineTxt);
        engineTxt.setColumns(10);
        engineTxt.setText(data[6]);

        conditionTxt = new JTextField();
        conditionTxt.setEnabled(false);
        conditionTxt.setBounds(222, 376, 148, 31);
        frame.getContentPane().add(conditionTxt);
        conditionTxt.setColumns(10);
        conditionTxt.setText(String.valueOf(data[2]));

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 805, 31);
        frame.getContentPane().add(panel);

        JLabel headerLabel = new JLabel("                "+data[4]);
        panel.add(headerLabel);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.inactiveCaption);
        panel_1.setBounds(0, 39, 404, 52);
        frame.getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.inactiveCaption);
        panel_2.setBounds(401, 39, 404, 52);
        frame.getContentPane().add(panel_2);

        image = new JLabel("");
        image.setBounds(150, 101, 210, 98);
        frame.getContentPane().add(image);
        ImageIcon imageFromUrl = getImageFromUrl(data[9]);
        image.setIcon(imageFromUrl);
    }

    private ImageIcon getImageFromUrl(String url) {
        ImageIcon imageIcon = null;

        try {
            Image read = ImageIO.read(new URL(url));
            imageIcon = new ImageIcon(read);
        } catch (IOException e) {
        	System.out.print("not found");
            imageIcon= new ImageIcon("././data/404NotFound.png");
        }
        //Set the image to an adaptive JLabel size
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(image.getWidth(),image.getHeight(),Image.SCALE_DEFAULT));
        return imageIcon;
    }
}
