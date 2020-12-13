package dao;

import dto.Customer;

import java.util.List;

public interface CustomerMapper {
    int insert(Customer record);

    int insertSelective(Customer record);
    
    Customer findById(String id);

    List<Customer> customerSearch();
}
