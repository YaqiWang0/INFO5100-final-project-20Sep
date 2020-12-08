package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;

public class VehicleDetails {

	private JFrame frame;
	private JTextField typeTxt;
	private JTextField seatTxt;
	private JTextField priceTxt;
	private JTextField incentiveTxt;
	private JTextField taxTxt;
	private JTextField totalTxt;
	private JTextField mileageTxt;
	private JTextField cateTxt;
	private static ArrayList<VehicleDetailsObj> list = new ArrayList<VehicleDetailsObj>();
	private JTextField modelTxt;
	
	public static void readData() throws IOException {
		File csv = new File("/Users/jiafeng/Desktop/Final Project/data/VehicleDataCSV_t.csv");
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String line = "";
		while((line = br.readLine()) != null)
		{
			String[] count = line.split(",");
			//System.out.println(count[0]+"  "+count[1]+"  "+count[2]+"  "+count[3]+"  "+count[4]+"  "+count[5]+"  "+count[6]+"  "+count[7]+"  "+count[8]);
			VehicleDetailsObj o = new VehicleDetailsObj();
			o.setVehicleId(count[0]);
			o.setCategory(count[1]);
			o.setYear(Integer.valueOf(count[2]));
			o.setMake(count[3]);
			o.setModel(count[4]);
			o.setType(count[5]);
			o.setSeatCount(Integer.valueOf(count[6]));
			o.setMileage(Float.parseFloat(count[7]));
			o.setPrice(count[8]);
			list.add(o);
			
		}
		
		
	}
/*------------------------------------------------------------------------*/
	public static void main(String[] args) throws Exception {
		//Scanner sc = new Scanner(new File("C:\\Users\\Pratt\\Downloads\\CarInventoryInformationTables.xlsx"));  
		//sc.useDelimiter(","); 
		readData();
		System.out.println(list.get(0).getVehicleId());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleDetails details = new VehicleDetails();
					details.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VehicleDetails() {
		initialize();
	}

	/* Initialize the contents of the frame.*/
	
	private void initialize() {
		 UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(105,105,105)));
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 250, 210));
		frame.getContentPane().setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		frame.setBounds(100, 100, 819, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton driveButton = new JButton("Request Test Drive");
		driveButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		driveButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		driveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		driveButton.setBounds(139, 379, 179, 21);
		frame.getContentPane().add(driveButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		saveButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton.setBounds(509, 379, 179, 21);
		frame.getContentPane().add(saveButton);
		
		JButton buyingButton = new JButton("Start Buying Process");
		buyingButton.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		buyingButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		buyingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyingButton.setBounds(509, 348, 180, 21);
		frame.getContentPane().add(buyingButton);
		
		
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
		typeLabel.setBounds(139, 235, 45, 13);
		frame.getContentPane().add(typeLabel);
		
		JLabel seatLabel = new JLabel("Seat Count");
		seatLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatLabel.setBounds(139, 258, 83, 13);
		frame.getContentPane().add(seatLabel);
		
		modelTxt = new JTextField();
		modelTxt.setEnabled(false);
		modelTxt.setBounds(222, 209, 96, 19);
		frame.getContentPane().add(modelTxt);
		modelTxt.setColumns(10);
		modelTxt.setText(String.valueOf(list.get(0).getModel()));
		
		typeTxt = new JTextField();
		typeTxt.setEnabled(false);
		typeTxt.setFont(new Font("Tahoma", Font.BOLD, 10));
		typeTxt.setBounds(222, 232, 96, 19);
		frame.getContentPane().add(typeTxt);
		typeTxt.setColumns(10);
		typeTxt.setText(String.valueOf(list.get(0).getType()));
		
		seatTxt = new JTextField();
		seatTxt.setEnabled(false);
		seatTxt.setBounds(222, 255, 96, 19);
		frame.getContentPane().add(seatTxt);
		seatTxt.setColumns(10);
		seatTxt.setText(String.valueOf(list.get(0).getSeatCount()));
		
		
		JLabel billLabel = new JLabel("Billing Details");
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
		priceTxt.setText(String.valueOf(list.get(0).getPrice()));
		
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
		
		JButton reqQuoteBtn = new JButton("Request Quote");
		reqQuoteBtn.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		reqQuoteBtn.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		reqQuoteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reqQuoteBtn.setBounds(139, 348, 179, 21);
		frame.getContentPane().add(reqQuoteBtn);
		
		JLabel headerLabel = new JLabel("                Car Name");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		headerLabel.setBounds(276, 10, 248, 13);
		frame.getContentPane().add(headerLabel);
		
		JLabel mileageLabel = new JLabel("Mileage");
		mileageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mileageLabel.setBounds(139, 281, 67, 21);
		frame.getContentPane().add(mileageLabel);
		
		JLabel cateLabel = new JLabel("Category");
		cateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cateLabel.setBounds(139, 304, 67, 21);
		frame.getContentPane().add(cateLabel);
		
		mileageTxt = new JTextField();
		mileageTxt.setEnabled(false);
		mileageTxt.setBounds(222, 279, 96, 19);
		frame.getContentPane().add(mileageTxt);
		mileageTxt.setColumns(10);
		mileageTxt.setText(String.valueOf(list.get(0).getMileage()));
		
		cateTxt = new JTextField();
		cateTxt.setEnabled(false);
		cateTxt.setBounds(222, 302, 96, 19);
		frame.getContentPane().add(cateTxt);
		cateTxt.setColumns(10);
		cateTxt.setText(String.valueOf(list.get(0).getCategory()));
	}

}
