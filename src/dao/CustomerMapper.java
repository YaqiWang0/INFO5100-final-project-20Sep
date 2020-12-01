package dao;

import dto.Customer;

public interface CustomerMapper {
    int insert(Customer record);

    int insertSelective(Customer record);
}