package service.impl;

import dao.SqlConnection;
import dto.Customer;
import service.CustomerLoginService;

import java.io.IOException;
import java.util.List;

public class CustomerLoginServiceImpl implements CustomerLoginService {


	public boolean customerVerifyLogin(String id, String password) throws IOException {
		SqlConnection sql = new SqlConnection();
		List<Customer> customers = sql.SearchCustomer();
		for (Customer o : customers) {
			if (id.equals(o.getId()) && password.equals(o.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
