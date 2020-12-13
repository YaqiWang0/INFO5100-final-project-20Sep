package service;

import io.Customer;

public interface CustomerLoginService {
	
	public boolean customerVerifyLogin(Customer id , Customer password);

}
