package ui;

import controller.CustomerController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerLogin extends JFrame {

	private JPanel contentPane;
	private JPanel userNamePanel;
	private JPanel passwordPanel;
	public JTextField userNameTextField;
	private JPanel northPanel;
	private JLabel title;
	private JLabel bottomLabel;
	private JButton skipButton;
	public JPasswordField passwordField;
	public JLabel userLabel;
	public JLabel passwordLabel;
	private JPanel loginJPanel;
	public JButton loginButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CustomerLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerLogin() {
		setTitle("Customer Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 430);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		userNamePanel = new JPanel();
		mainPanel.add(userNamePanel);
		userNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		userLabel = new JLabel("User Name");
		userNamePanel.add(userLabel);

		userNameTextField = new JTextField();
		userNamePanel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passwordPanel = new JPanel();
		mainPanel.add(passwordPanel);
		
		passwordLabel = new JLabel("Password");
		passwordPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordPanel.add(passwordField);
		passwordField.setColumns(10);
		
		passwordField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPasswordField password = (JPasswordField)e.getSource();
				System.out.println(password.getText());
				
			}
		});
		passwordField.setEchoChar('*');
		
		loginJPanel = new JPanel();
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// check
				try {
					boolean response = new CustomerController().login(userNameTextField.getText(), passwordField.getText());
					if(response){
						dispose();
					}else{
						new mistake();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		loginJPanel.add(loginButton);
		
		mainPanel.add(loginJPanel);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		bottomLabel = new JLabel("You are a dealer? ");
		buttonPanel.add(bottomLabel);
		
		skipButton = new JButton("Go to dealer login page");
		skipButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DealerLogin();
				
			}
		});
		buttonPanel.add(skipButton);
		
		northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		title = new JLabel("Welcome customer login page");
		northPanel.add(title);
		setVisible(true);
	}


}
