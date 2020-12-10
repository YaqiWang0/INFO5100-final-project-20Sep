package FinalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class dealerLog extends JFrame {

	private JPanel contentPane;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField textField;
	private JPanel panel_4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JPasswordField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel loginJPanel;
	private JButton loginButton;

	/**
	 * Create the frame.
	 */
	
	public dealerLog() {
		setTitle("Dealer Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 430);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_2 = new JLabel("User Name");
		panel_2.add(lblNewLabel_2);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		
		lblNewLabel_3 = new JLabel("Password");
		panel_3.add(lblNewLabel_3);
		
		textField_1 = new JPasswordField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPasswordField password = (JPasswordField)e.getSource();
				System.out.println(password.getText());
				
			}
		});
		textField_1.setEchoChar('*');
		
		loginJPanel = new JPanel();
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// check 
				
				// if success login
				
			}
		});
		
		loginJPanel.add(loginButton);
		
		panel.add(loginJPanel);
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("You are a customer? ");
		panel_1.add(lblNewLabel_1);
		
		btnNewButton = new JButton("Go to customer login page");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Logintest();
			}
		});
		panel_1.add(btnNewButton);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Welcome dealer login page");
		panel_4.add(lblNewLabel);
		setVisible(true);
	}

}
