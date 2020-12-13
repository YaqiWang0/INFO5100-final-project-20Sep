package ui;

import dao.SqlConnection;
import dto.Customer;
import dto.Dealer;

import java.awt.BorderLayout;

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
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;

public class DealerLogin extends JFrame {

	private JPanel contentPane;
	private JPanel userPanel;
	private JPanel passwordPanel;
	public JTextField userNameTextField;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JLabel askingLabel;
	private JButton transferButton;
	public JPasswordField passwordTextField;
	public JLabel nameLabel;
	public JLabel passwordLabel;
	private JPanel loginJPanel;
	public JButton loginButton;

	/**
	 * Create the frame.
	 */
	public DealerLogin() {
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

		userPanel = new JPanel();
		panel.add(userPanel);
		userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		nameLabel = new JLabel("User Name");
		userPanel.add(nameLabel);

		userNameTextField = new JTextField();
		userPanel.add(userNameTextField);
		userNameTextField.setColumns(10);

		passwordPanel = new JPanel();
		panel.add(passwordPanel);

		passwordLabel = new JLabel("Password");
		passwordPanel.add(passwordLabel);

		passwordTextField = new JPasswordField();
		passwordPanel.add(passwordTextField);
		passwordTextField.setColumns(10);

		passwordTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPasswordField password = (JPasswordField)e.getSource();
				System.out.println(password);
				
			}
		});
		passwordTextField.setEchoChar('*');
		
		loginJPanel = new JPanel();
		loginButton = new JButton("Login");

		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SqlConnection sql = new SqlConnection();
					List<Dealer> dealers = sql.SearchDealer();
					Dealer dealer=new Dealer();
					dealer.setId(userNameTextField.getText());
					dealer.setPassword(new String(passwordTextField.getPassword()));
					dealers.forEach(o->{
						if(dealer.getId().equals(o.getId()) && dealer.getPassword().equals(o.getPassword())) {
							dispose();
						}
					});
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		loginJPanel.add(loginButton);
		
		panel.add(loginJPanel);
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		askingLabel = new JLabel("You are a customer? ");
		panel_1.add(askingLabel);
		
		transferButton = new JButton("Go to customer login page");

		transferButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerLogin();
			}
		});
		panel_1.add(transferButton);

		titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		titleLabel = new JLabel("Welcome dealer login page");
		titlePanel.add(titleLabel);
		setVisible(true);
	}

	public boolean isUserNamePresent()
	{
		String userName = userNameTextField.getText();
		return userName != null && !userName.isEmpty();
	}

	public boolean isPasswordPresent()
	{
		char[] password = passwordTextField.getPassword();
		return password.length > 0;
	}

}
