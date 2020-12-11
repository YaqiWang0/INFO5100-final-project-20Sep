package dao;

import io.Dealer;

public interface DealerMapper {
    int insert(Dealer record);

    int insertSelective(Dealer record);

    Dealer findById(Dealer id);
}