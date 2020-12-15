package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import service.UpdateInventory;

public class InventoryUpdateJPanel {
    private JPanel UpdateJPanel;
    private JTextField idTextField;
    private JTextField yearTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField isNewTextField;
    private JTextField priceTextField;
    private JTextField exteriorColorTextField;
    private JTextField interiorColorTextField;
    private JTextField bodyTypeTextField;
    private JTextField featuresTextField;
    private JTextField milesTextField;
    private JTextField imagesTextField;
    private JButton confirmButton;
    private JButton cancelButton;

    public InventoryUpdateJPanel(String id, String dealerId,String year,String brand, String model, String category, String price, String type, String feature,String image){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(UpdateJPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.idTextField.setText(id);
        this.yearTextField.setText(year);
        this.brandTextField.setText(brand);
        this.modelTextField.setText(model);
        this.isNewTextField.setText(category);
        this.priceTextField.setText(price);
        this.featuresTextField.setText(feature);
        this.bodyTypeTextField.setText(type);
        this.imagesTextField.setText(image);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
                } catch (IOException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
                    //System.out.println(modelTextField.getText());

                    //update vehicle data

                    UpdateInventory.updateInventoryByDealer(idTextField.getText(),dealerId,isNewTextField.getText(),yearTextField.getText(),brandTextField.getText(),modelTextField.getText(),featuresTextField.getText(),bodyTypeTextField.getText(),priceTextField.getText(),imagesTextField.getText());
                    JOptionPane.showMessageDialog(null,"update successfully");

                } catch (IOException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        });
    }
}
