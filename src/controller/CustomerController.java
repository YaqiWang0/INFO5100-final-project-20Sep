package controller;

import service.CustomerLoginService;
import service.impl.CustomerLoginServiceImpl;

import java.io.IOException;


public class CustomerController {

	CustomerLoginService customerLoginService = new CustomerLoginServiceImpl();

	public boolean login(String id, String password) throws IOException {
		boolean response = customerLoginService.customerVerifyLogin(id, password);
		return response;
	}
}
