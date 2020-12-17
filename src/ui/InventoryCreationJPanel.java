package ui;

import service.CreateInventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class InventoryCreationJPanel {
    private JTextField idTextField;
    private JTextField yearTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField isNewTextField;
    private JTextField priceTextField;
    private JTextField bodyTypeTextField;
    private JTextField featuresTextField;
    private JTextField imagesTextField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel CreateJPanel;

    public InventoryCreationJPanel(String dealerId, JFrame oldFrame, JPanel InventoryMgmtPanel){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(CreateJPanel);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(imagesTextField.getText().equals("") || imagesTextField.getText().equals("~")){
                    imagesTextField.setText("null");
                }
                CreateInventory.createInventoryByDealer(idTextField.getText(), dealerId, isNewTextField.getText(), yearTextField.getText(), brandTextField.getText(), modelTextField.getText(), featuresTextField.getText(), bodyTypeTextField.getText(), priceTextField.getText(), imagesTextField.getText());
                try {
//                    oldFrame.remove(InventoryMgmtPanel);
                    oldFrame.dispose();
                    frame.dispose();
                    InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args){
//        InventoryCreationJPanel ic = new InventoryCreationJPanel();
    }
}
