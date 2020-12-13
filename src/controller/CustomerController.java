package controller;
import dao.SqlConnection;
import dto.Customer;
import ui.CustomerLogin;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class CustomerController {
	public CustomerLogin getDialog() {
		return dialog;
	}
	private CustomerLogin dialog;
	
	public CustomerController(JFrame frame) {
		dialog = new CustomerLogin();
		dialog.loginButton.addActionListener(e ->{
			try {
				login();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		});
		dialog.passwordField.addActionListener(e ->{
			try {
				login();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public void show() {
		dialog.dispose();
		dialog.setUndecorated(true);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	private void login() throws IOException{
		String userName = dialog.userNameTextField.getText();
		String password = new String(dialog.passwordField.getPassword());
		if(dialog.isPasswordPresent()&&dialog.isUserNamePresent()) {
			SqlConnection sql = new SqlConnection();
			List<Customer> customerList = sql.SearchCustomer();
			Customer customer = new Customer();
			customer.setId(userName);
			customer.setPassword(password);
			if(customerList.contains(customer)) {
				show();
			}
		}
	}
}
