package dao;

import io.Customer;

public interface CustomerMapper {
    int insert(Customer record);

    int insertSelective(Customer record);
    
    Customer findById(Customer id);
}
