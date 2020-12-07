package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementJPanel {
    private JPanel InventoryMgmtPanel;
    private JButton logoutButton;
    private JTextField searchTextField;
    private JButton allButton;
    private JButton searchButton;
    public JTable table1;
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
        String[][] tableVales = {{"1", "Dealer ID", "Year", "Brand1", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"},{"2", "Dealer ID", "Year", "Brand2", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"},{"3", "Dealer ID", "Year", "Brand2", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"}};
        TableModel tableModel = new DefaultTableModel(tableVales, columnNames);
        table1.setModel(tableModel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchContent = searchTextField.getText();
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table1.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(searchTextField.getText()));

                table1.setRowSorter(sorter);
            }
        });
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table1.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(""));

                table1.setRowSorter(sorter);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                InventoryUpdateJPanel panel = new InventoryUpdateJPanel(table1.getValueAt(row, 0).toString(), table1.getValueAt(row, 1).toString());
                System.out.println(table1.getValueAt(row, 0).toString() + " " + table1.getValueAt(row, 1).toString());
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryCreationJPanel panel = new InventoryCreationJPanel();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                table1.remove(table1.getSelectedRow());
            }
        });
    }

    public static void main(String[] args){
        InventoryManagementJPanel im = new InventoryManagementJPanel();
    }


}
