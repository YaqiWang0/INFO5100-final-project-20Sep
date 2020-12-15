package ui;

import incentive.IncentiveManager;
import ui.CheckLead.CheckLeadUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DealerHome extends JFrame {
	private String dealerId;
	private JPanel contentPane;
	private final JLabel lblNewLabel_2 = new JLabel("");
	JButton case_4Button;
	JButton case_5Button;

	public void findID(String username) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/dealers-id.csv")));
		String line;
		while((line=br.readLine())!=null){
			String[] info = line.split(",");
			if (info[1].equals(username)){
				this.dealerId = info[0];
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		            Locale.setDefault(new Locale("en","US"));
					DealerHome frame = new DealerHome("xiang");
	}

	/**
	 * Create the frame.
	 */
	public DealerHome(String username) throws IOException {
//		Locale.setDefault(new Locale("en","US"));
		findID(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel NorthPanel = new JPanel();
		contentPane.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setText(this.dealerId);
		NorthPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure to log out?");
                JOptionPane.setRootFrame(null);
               if (a == JOptionPane.YES_OPTION) {
                   dispose();
                   // redirect to login
                   
               }
				
			}
		});
		NorthPanel.add(btnNewButton);
		
		JPanel MainPanel = new JPanel();
		contentPane.add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new GridLayout(4, 2, 5, 5));
		
		case_4Button = new JButton("Manage My Inventory (case4)");
		
		JLabel manageInvLabel = new JLabel("       Create/update/modify my Inventor :");
		manageInvLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(manageInvLabel);
		MainPanel.add(case_4Button);
		
		JLabel createIncLabel = new JLabel("      Create Incentives for different types of vehicles :");
		createIncLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(createIncLabel);
		
		case_5Button = new JButton("Manage Incentives(case5)");
		MainPanel.add(case_5Button);
		
		JLabel messageLabel = new JLabel("      Look at all the leads sent by the customers and respond :");
		messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(messageLabel);
		
		JButton case_8Button = new JButton("Show Customer Message (case 8)");
		MainPanel.add(case_8Button);
		case_8Button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckLeadUI(dealerId);
                
            }
        });
		
		
		MainPanel.add(lblNewLabel_2);
		setVisible(true);

		createInventory();
		createIncentive();
	}

	public void createInventory(){
		case_4Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
				} catch (MalformedURLException malformedURLException) {
					malformedURLException.printStackTrace();
				}
			}
		});
	}

	public void createIncentive(){
		case_5Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IncentiveManager im = new IncentiveManager();
				try {
					im.mainCaller("bae705d7-20da-4ee2-871f-345b2271992b");
				} catch (ParseException parseException) {
					parseException.printStackTrace();
				}
			}
		});
	}

}
