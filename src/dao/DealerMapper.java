package dao;

import io.Dealer;

import java.util.List;

public interface DealerMapper {
    int insert(Dealer record);

    List<dto.Dealer> dealerSearch();
}