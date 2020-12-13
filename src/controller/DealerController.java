package controller;
import dao.SqlConnection;
import dto.Customer;
import dto.Dealer;
import service.DealerLoginService;
import service.impl.DealerLoginServiceImpl;
import ui.CustomerLogin;
import ui.DealerLogin;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class DealerController {

    private DealerLogin dialog = new DealerLogin();

    DealerLoginService dealerLoginService = new DealerLoginServiceImpl();

    public boolean login(String id, String password) throws IOException {
        boolean response = dealerLoginService.dealerVerifyLogin(id, password);
        return response;
    }
}
