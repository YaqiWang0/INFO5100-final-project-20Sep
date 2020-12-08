package ui;

import javax.swing.*;

public class InventoryUpdateJPanel {
    private JPanel UpdateJPanel;
    private JTextField idTextField;
    private JTextField dealerIdTextField;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.idTextField.setText(id);
        this.dealerIdTextField.setText(dealerId);
    }
}
