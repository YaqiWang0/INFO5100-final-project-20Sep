package dao;

import dto.Customer;

import java.util.List;

public interface CustomerMapper {
    int insert(Customer record);

    List<Customer> customerSearch();
}
