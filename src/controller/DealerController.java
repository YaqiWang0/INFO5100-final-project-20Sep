package controller;

import service.DealerLoginService;
import service.impl.DealerLoginServiceImpl;

import java.io.IOException;

public class DealerController {

    DealerLoginService dealerLoginService = new DealerLoginServiceImpl();

    public boolean login(String id, String password) throws IOException {
        boolean response = dealerLoginService.dealerVerifyLogin(id, password);
        return response;
    }
}
