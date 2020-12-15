package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

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

    public InventoryUpdateJPanel(String id, String dealerId){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(UpdateJPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.idTextField.setText(id);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InventoryManagementJPanel panel = new InventoryManagementJPanel();

                    //test
                    System.out.println(111);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InventoryManagementJPanel panel = new InventoryManagementJPanel();
                    //test
                    System.out.println(221);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        });
    }
}
