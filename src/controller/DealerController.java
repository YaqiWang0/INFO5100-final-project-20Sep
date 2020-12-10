package controller;
import dao.SqlConnection;
import dto.Dealer;
import ui.DealerLogin;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class DealerController {
    public DealerLogin getDialog() { return dialog; }
    private DealerLogin dialog;

    public DealerController(JFrame frame)
    {
        dialog = new DealerLogin();
        dialog.loginButton.addActionListener(e -> {
            try {
                login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        dialog.passwordTextField.addActionListener(e -> {
            try {
                login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void show()
    {
        dialog.dispose();
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void login() throws IOException {
        String userName = dialog.userNameTextField.getText();
        String password = new String(dialog.passwordTextField.getPassword());
        if (dialog.isPasswordPresent() && dialog.isUserNamePresent())
        {
            SqlConnection sql=new SqlConnection();
            List<Dealer> dealerList=sql.SearchDealer();
            Dealer dealer=new Dealer();
            dealer.setId(userName);
            dealer.setPassword(password);
            if(dealerList.contains(dealer)){
                show();
            }

        }
    }
}
