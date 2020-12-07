package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class InventoryManagementJPanel {
    private JPanel InventoryMgmtPanel;
    private JButton logoutButton;
    private JTextField searchTextField;
    private JButton allButton;
    private JButton searchButton;
    private JTable table1;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel searchLabel;
    private JPanel MainPanel;
    private JPanel SplitPanel;
    private JPanel TablePanel;

    public InventoryManagementJPanel(){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(InventoryMgmtPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] columnNames = {"ID", "Dealer ID", "Year", "Brand", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"};
        String[][] tableVales = {{"ID", "Dealer ID", "Year", "Brand", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"}};
        TableModel tableModel = new DefaultTableModel(tableVales, columnNames);
        table1.setModel(tableModel);
    }

    public static void main(String[] args){
        InventoryManagementJPanel im = new InventoryManagementJPanel();
    }
}
