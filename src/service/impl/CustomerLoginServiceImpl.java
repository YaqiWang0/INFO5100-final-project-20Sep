package service.impl;

import dao.CustomerMapper;
import io.Customer;
import service.CustomerLoginService;

public class CustomerLoginServiceImpl implements CustomerLoginService{
	
	private CustomerMapper customerMapper;
	
	public boolean customerVerifyLogin(Customer id , Customer password) {
		Customer customer = customerMapper.findById(id);
		if(null==customer) {
			return false;
		}else {
			if(customer.getPassword().equals(password)) {
				return true;
			}else {
				return false;
				}
			}
		}
	}
