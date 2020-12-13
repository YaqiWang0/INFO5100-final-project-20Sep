package dao;

import io.Dealer;

import java.util.List;

public interface DealerMapper {
    int insert(Dealer record);

    int insertSelective(Dealer record);

    Dealer findById(Dealer id);

    List<dto.Dealer> dealerSearch();
}