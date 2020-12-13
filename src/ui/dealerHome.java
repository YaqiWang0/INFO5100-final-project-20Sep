package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class dealerHome extends JFrame {
	private String id;
	private JPanel contentPane;
	private final JLabel lblNewLabel_2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		            Locale.setDefault(new Locale("en","US"));
					dealerHome frame = new dealerHome();
					
	}

	/**
	 * Create the frame.
	 */
	public dealerHome() {
//		Locale.setDefault(new Locale("en","US"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel NorthPanel = new JPanel();
		contentPane.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Hi, id");
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
		
		JButton case_4Button = new JButton("Manage My Inventory (case4)");
		case_4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open case4 page
			}
		});
		
		JLabel manageInvLabel = new JLabel("       Create/update/modify my Inventor :");
		manageInvLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(manageInvLabel);
		MainPanel.add(case_4Button);
		
		JLabel createIncLabel = new JLabel("      Create Incentives for different types of vehicles :");
		createIncLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(createIncLabel);
		
		JButton case_5Button = new JButton("Manage Incentives(case5)");
		case_5Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// open case5 page
				
			}
		});
		
		MainPanel.add(case_5Button);
		
		JLabel messageLabel = new JLabel("      Look at all the leads sent by the customers and respond :");
		messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		MainPanel.add(messageLabel);
		
		JButton case_8Button = new JButton("Show Customer Message (case 8)");
		case_8Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// open case8 page
				
			}
		});
		
		MainPanel.add(case_8Button);

		MainPanel.add(lblNewLabel_2);
		setVisible(true);
	}

}
