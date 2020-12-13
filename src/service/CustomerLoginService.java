package service;

import io.Customer;

import java.io.IOException;

public interface CustomerLoginService {
	
	public boolean customerVerifyLogin(String id, String password) throws IOException;

}
