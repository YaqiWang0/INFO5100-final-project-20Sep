package ui;

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

public class CostumerLogin extends JFrame {

	private JPanel contentPane;
	private JPanel userNamePanel;
	private JPanel passwordPanel;
	private JTextField userName;
	private JPanel northPanel;
	private JLabel title;
	private JLabel bottomLable;
	private JButton skipButton;
	private JPasswordField passwordFeild;
	private JLabel userLable;
	private JLabel passwordLable;
	private JPanel loginJPanel;
	private JButton loginButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CostumerLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CostumerLogin() {
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
		
		userLable = new JLabel("User Name");
		userNamePanel.add(userLable);
		
		userName = new JTextField();
		userNamePanel.add(userName);
		userName.setColumns(10);
		
		passwordPanel = new JPanel();
		mainPanel.add(passwordPanel);
		
		passwordLable = new JLabel("Password");
		passwordPanel.add(passwordLable);
		
		passwordFeild = new JPasswordField();
		passwordPanel.add(passwordFeild);
		passwordFeild.setColumns(10);
		
		passwordFeild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPasswordField password = (JPasswordField)e.getSource();
				System.out.println(password.getText());
				
			}
		});
		passwordFeild.setEchoChar('*');
		
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
		
		mainPanel.add(loginJPanel);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		bottomLable = new JLabel("You are a dealer? ");
		buttonPanel.add(bottomLable);
		
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
