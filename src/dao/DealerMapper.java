package dao;

import dto.Dealer;

public interface DealerMapper {
    int insert(Dealer record);

    int insertSelective(Dealer record);
}