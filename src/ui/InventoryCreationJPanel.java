package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class InventoryCreationJPanel {
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
    private JPanel CreateJPanel;

    public InventoryCreationJPanel(String dealerId){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(CreateJPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args){
//        InventoryCreationJPanel ic = new InventoryCreationJPanel();
    }
}
