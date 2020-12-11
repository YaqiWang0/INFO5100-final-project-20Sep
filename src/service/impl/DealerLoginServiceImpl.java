package service.impl;

import dao.DealerMapper;
import io.Dealer;
import service.DealerLoginService;

public class DealerLoginServiceImpl implements DealerLoginService {

    private DealerMapper dealerMapper;

    public boolean dealerVerifyLogin(Dealer id, Dealer password){
        Dealer dealer = dealerMapper.findById(id);
        if(null == dealer){
            return false;
        }else{
            if(dealer.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }
    }
}
