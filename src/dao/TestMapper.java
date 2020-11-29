package dao;

import dto.Test;

import java.util.List;

public interface TestMapper {
    int insert(Test record);

    int insertSelective(Test record);

    List<Test> dealerSearch();
}