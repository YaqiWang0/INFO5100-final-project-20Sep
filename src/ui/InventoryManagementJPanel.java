package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

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

    public InventoryManagementJPanel() throws MalformedURLException {
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(InventoryMgmtPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] columnNames = {"ID", "Dealer ID", "Year", "Brand", "Model", "New/Used", "Price", "Exterior Color", "Interior Color", "Body Type", "Features", "Miles", "Images"};
        Object[][] tableVales = {{"2960297373", "gmps-roberts-tn", "2016", "Buick", "Cascada", "New", "37400.0", "Black", "Black", "CAR", "2dr Conv Premium", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2016/2945603/default/ext_GAR_deg01x90.jpg"))},{"2966525563", "gmps-roberts-tn", "2017", "Buick", "Enclave", "New", "46660.0", "Brown", "Black", "SUV", "Leather FWD", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2017/3273383/default/ext_G1F_deg01x90.jpg"))},{"2932765103", "gmps-roberts-tn", "2017", "Chevrolet", "Malibu", "New", "24140.0", "Black", "Black", "CAR", "1LS", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Chevrolet/2017/3343993/default/ext_GAZ_deg01x90.jpg"))}};
        TableModel tableModel = new DefaultTableModel(tableVales, columnNames){
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table1.setModel(tableModel);
        table1.getColumn(columnNames[12]).setPreferredWidth(100);
        table1.setRowHeight(60);

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

    public static void main(String[] args) throws MalformedURLException {
        InventoryManagementJPanel im = new InventoryManagementJPanel();
    }


}
