package service.impl;

import dao.DealerMapper;
import dao.SqlConnection;
import dto.Customer;
import dto.Dealer;
import service.DealerLoginService;

import java.io.IOException;
import java.util.List;

public class DealerLoginServiceImpl implements DealerLoginService {


    public boolean dealerVerifyLogin(String id, String password) throws IOException {
        SqlConnection sql = new SqlConnection();
        List<Dealer> dealers = sql.SearchDealer();
        for (Dealer o : dealers) {
            if (id.equals(o.getId()) && password.equals(o.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
