package controller;
import dao.SqlConnection;
import dto.Customer;
import service.CustomerLoginService;
import service.impl.CustomerLoginServiceImpl;
import ui.CustomerLogin;
import ui.mistake;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class CustomerController {

	private CustomerLogin dialog = new CustomerLogin();

	CustomerLoginService customerLoginService = new CustomerLoginServiceImpl();

	public boolean login(String id, String password) throws IOException {
		boolean response = customerLoginService.customerVerifyLogin(id, password);
		return response;
	}
}
