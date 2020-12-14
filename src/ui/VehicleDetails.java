package ui;

//import dao.VehicleDetailsObj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


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
    private String[] data;
    private JTextField modelTxt;

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
			public void run() {
				try { 
					VehicleDetails window = new VehicleDetails(new String[]{"2228104413","gmps-aj-dohmann","new","2014","Cadillac","CTS Sedan","3.6L V6 AWD Luxury","CAR","57620.0","http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg"});
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }

   
   

    public VehicleDetails(String[] data) {
        if (data == null || data.length == 0) {
            String str = "2228104413~gmps-aj-dohmann~new~2014~Cadillac~CTS Sedan~3.6L V6 AWD Luxury~CAR~57620.0~http://inventory-dmg.assets-cdk.com/5/1/7/13411480715x90.jpg";
            data = str.split("~");
        }else {
            this.data = data;
        }
        initialize();
    }

    /* Initialize the contents of the frame.*/
    

    private void initialize() {
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(105,105,105)));
        System.out.println("data = " + Arrays.toString(data));
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(250, 250, 210));
        frame.getContentPane().setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        frame.setBounds(100, 100, 819, 542);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);



		//Save
		JButton saveButton = new JButton("Save");
		saveButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		saveButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton.setBounds(139, 379, 179, 21);
		frame.getContentPane().add(saveButton);

        //Start Buying Process
        JButton buyingButton = new JButton("Start Buying Process");
        buyingButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        buyingButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        buyingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buyingButton.setBounds(509, 348, 180, 21);
        frame.getContentPane().add(buyingButton);
        

		//Monthly Calc
		JButton calculatorButton = new JButton("Monthly Payment Calculator");
		calculatorButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		calculatorButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		calculatorButton.addActionListener((ActionEvent) -> new MonthPayCalc());
		calculatorButton.setBounds(509, 379, 179, 26);
		frame.getContentPane().add(calculatorButton);

        JLabel detailLabel = new JLabel("Car Details");
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setBounds(155, 53, 103, 21);
        frame.getContentPane().add(detailLabel);

        JLabel modelLabel = new JLabel("Model");
        modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        modelLabel.setBounds(139, 212, 45, 13);
        frame.getContentPane().add(modelLabel);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeLabel.setBounds(139, 235, 45, 16);
        frame.getContentPane().add(typeLabel);

        JLabel modelYearLabel = new JLabel("Model Year");
        modelYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        modelYearLabel.setBounds(139, 258, 83, 13);
        frame.getContentPane().add(modelYearLabel);

        modelTxt = new JTextField();
        modelTxt.setEnabled(false);
        modelTxt.setBounds(222, 209, 96, 19);
        frame.getContentPane().add(modelTxt);
        modelTxt.setColumns(10);
        modelTxt.setText(String.valueOf(data[5]));

        typeTxt = new JTextField();
        typeTxt.setEnabled(false);
        typeTxt.setFont(new Font("Tahoma", Font.BOLD, 10));
        typeTxt.setBounds(222, 232, 96, 19);
        frame.getContentPane().add(typeTxt);
        typeTxt.setColumns(10);
        typeTxt.setText(String.valueOf(data[7]));

        modelYearTxt = new JTextField();
        modelYearTxt.setEnabled(false);
        modelYearTxt.setBounds(222, 255, 96, 19);
        frame.getContentPane().add(modelYearTxt);
        modelYearTxt.setColumns(10);
        modelYearTxt.setText(String.valueOf(data[3]));

        JLabel billLabel = new JLabel("Price Details");
        billLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        billLabel.setBounds(509, 53, 111, 21);
        frame.getContentPane().add(billLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        priceLabel.setBounds(509, 101, 45, 13);
        frame.getContentPane().add(priceLabel);

        JLabel incentiveLabel = new JLabel("Incentives");
        incentiveLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        incentiveLabel.setBounds(509, 127, 67, 13);
        frame.getContentPane().add(incentiveLabel);

        JLabel taxLabel = new JLabel("Tax");
        taxLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        taxLabel.setBounds(509, 158, 45, 13);
        frame.getContentPane().add(taxLabel);

        JLabel totalLabel = new JLabel("Total Price");
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        totalLabel.setBounds(509, 181, 67, 13);
        frame.getContentPane().add(totalLabel);

        priceTxt = new JTextField();
        priceTxt.setEnabled(false);
        priceTxt.setToolTipText("");
        priceTxt.setBounds(593, 98, 96, 19);
        frame.getContentPane().add(priceTxt);
        priceTxt.setColumns(10);
        priceTxt.setText(String.valueOf(data[8]));

        incentiveTxt = new JTextField();
        incentiveTxt.setEnabled(false);
        incentiveTxt.setBounds(593, 124, 96, 19);
        frame.getContentPane().add(incentiveTxt);
        incentiveTxt.setColumns(10);
        //incentiveTxt.setText(String.valueOf(list.get(0).get));

        taxTxt = new JTextField();
        taxTxt.setEnabled(false);
        taxTxt.setBounds(593, 153, 96, 19);
        frame.getContentPane().add(taxTxt);
        taxTxt.setColumns(10);

        totalTxt = new JTextField();
        totalTxt.setEnabled(false);
        totalTxt.setBounds(593, 178, 96, 19);
        frame.getContentPane().add(totalTxt);
        totalTxt.setColumns(10);

        //Request Quote
        JButton reqQuoteBtn = new JButton("Request Quote");
        reqQuoteBtn.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        reqQuoteBtn.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        reqQuoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        reqQuoteBtn.setBounds(139, 348, 179, 21);
        frame.getContentPane().add(reqQuoteBtn);

        JLabel engineLabel = new JLabel("Engine");
        engineLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        engineLabel.setBounds(139, 281, 67, 21);
        frame.getContentPane().add(engineLabel);

        JLabel conditionLabel = new JLabel("Condition");
        conditionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        conditionLabel.setBounds(139, 304, 67, 21);
        frame.getContentPane().add(conditionLabel);

        engineTxt = new JTextField();
        engineTxt.setEnabled(false);
        engineTxt.setBounds(222, 279, 96, 19);
        frame.getContentPane().add(engineTxt);
        engineTxt.setColumns(10);
        engineTxt.setText(data[5]);

        conditionTxt = new JTextField();
        conditionTxt.setEnabled(false);
        conditionTxt.setBounds(222, 302, 96, 19);
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
        panel_1.setBounds(10, 39, 394, 52);
        frame.getContentPane().add(panel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.inactiveCaption);
        panel_2.setBounds(401, 39, 394, 52);
        frame.getContentPane().add(panel_2);
    }
}
