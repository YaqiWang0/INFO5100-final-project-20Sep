package ui.leads;

import dao.*;
import dto.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.List;


public class LeadsTableUI extends JFrame {

    private static final long serialVersionUID = -1484589037032558776L;
    
    private JButton detailBtn, deleteBtn;
    private JComboBox<String> secondLevelFilterCombo;
    
    private LeadDataHelper helper;
    private List<Lead> leads;
    private List<Lead> originalLeads;
    
    private LeadsTableModel tableModel;
    private int selectedRow = -1;
    
    private String[] FILTER_ITEMS = {"ALL", "Contact Preference", "Use Purpose", "Read", "Contacted"};
    private String[] SORT_BY_ITEMS = {"--Select sort--", "Name", "Phone Number", "Email", 
                                      "Contact Preference","Contact Time", "Use Purpose", "Read", "Contacted"};

    public LeadsTableUI(String dealerId) {
        
        helper = LeadDataHelper.instance();
        String dealerName = helper.getDealerName(dealerId);
        originalLeads = helper.getMergedLeads(dealerName);
        leads = originalLeads;
        tableModel = new LeadsTableModel(leads);

        setupUI();
        display();
    }
    
    private void setupUI() {
        JPanel mainPanel = new JPanel();

        setupMainPanel(mainPanel);
        setupHeaderUI(mainPanel);
        setupTableUI(mainPanel);
    }
    
    private void setupMainPanel(JPanel mainPanel) {
        mainPanel.setLayout(new BorderLayout(10, 5));
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setupHeaderUI(JPanel mainPanel) {
        JPanel headerPanel = new JPanel();
        
        headerPanel.setPreferredSize(new Dimension(this.getContentPane().getWidth(), 80));
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Filter Label 
        JLabel filterLabel = new JLabel("Filter");
        headerPanel.add(filterLabel, gbc);
        
        // Filter ComboBox
        JComboBox<String> filterCombo = new JComboBox<String>(FILTER_ITEMS);
        filterCombo.addItemListener(new FilterItemChangeListener());
        filterCombo.setPrototypeDisplayValue("xxxxxxxxx");
        headerPanel.add(filterCombo, gbc);
        
        // Second level filter
        secondLevelFilterCombo = new JComboBox<String>();
        secondLevelFilterCombo.addItemListener(new SecondLevelFilterItemChangeListener());
        secondLevelFilterCombo.setEnabled(false);
        secondLevelFilterCombo.setPrototypeDisplayValue("xxxxxxxxx");
        headerPanel.add(secondLevelFilterCombo, gbc);
        
        // Sort By Label
        JLabel sortByLabel = new JLabel("Sort by");
        headerPanel.add(sortByLabel, gbc);
        
        //Sort By ComboxBox
        JComboBox<String> sortByCBox = new JComboBox<String>(SORT_BY_ITEMS);
        sortByCBox.addItemListener(new SortByItemChangeListener());
        headerPanel.add(sortByCBox, gbc);
        
        // Export Button
        JButton exportBtn = new JButton("Export");
        exportBtn.addActionListener(new ExportActionListener());
        headerPanel.add(exportBtn, gbc);
        
        // Detail Button
        detailBtn = new JButton("Detail");
        detailBtn.addActionListener(new DetailActionListener());
        headerPanel.add(detailBtn, gbc);
        
        // Delete Button
        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new DeleteActionListener());
        headerPanel.add(deleteBtn, gbc);
        
        // disable details and delete buttons
        updateHeaderBtnStatus(false);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }
    
    private void setupTableUI(JPanel mainPanel) {
        // Table
        JTable table = new JTable(tableModel);
        
        // Add double click Listener
        table.addMouseListener(new TableMouseAdapter());
        
        // Add selected row changed Listener
        // Update header buttons according the selecting status
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                selectedRow = table.getSelectedRow();
                updateHeaderBtnStatus(selectedRow != -1);
            }
        });

        // Set table fonts
        table.getTableHeader().setFont(new Font("Baskerville", Font.BOLD, 14));
        table.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        // Set Table style
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setRowHeight(30);
        table.setBackground(getContentPane().getBackground());
        
        // Set Table column width
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);
        
        // jScrollPane
        JScrollPane jScrollPane = new JScrollPane(table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(1000, 600));
        jScrollPane.setBackground(getContentPane().getBackground());
        
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
    }
    
    private void display() {
        this.setPreferredSize(new Dimension(1000, 800));
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void openDetailWindow(int row) {
        Lead lead = leads.get(row);
        Vehicle[] vehicles = helper.getVehiclesByEmail(lead.getDealerName(), lead.getEmailAddress());
        if (vehicles.length > 0) {
            new LeadDetailsUI(lead, vehicles).buildGUI();
        } else {
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showConfirmDialog(dialog, "No vehicle found in the inventory, please check data!",
                    "Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
    
    private void updateLeadRead(int row) {
        leads.get(row).setRead(true);
        tableModel.fireTableDataChanged();
    }
    
    private void updateHeaderBtnStatus(boolean enable) {
        detailBtn.setEnabled(enable);
        deleteBtn.setEnabled(enable);
    }
    
    class FilterItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            String item = (String) e.getItem();
            secondLevelFilterCombo.removeAllItems();
            secondLevelFilterCombo.setEnabled(true);
            switch (item) {
                case "ALL":
                    secondLevelFilterCombo.setEnabled(false);
                    leads = originalLeads;
                    tableModel.updateLeads(leads);
                    tableModel.fireTableDataChanged();
                    break;
                case "Contact Preference":
                    secondLevelFilterCombo.addItem("Email");
                    secondLevelFilterCombo.addItem("Phone");
                    break;
                case "Use Purpose":
                    secondLevelFilterCombo.addItem("Business");
                    secondLevelFilterCombo.addItem("Personal");
                    break;
                case "Read":
                    secondLevelFilterCombo.addItem("Read");
                    secondLevelFilterCombo.addItem("Unread");
                    break;
                case "Contacted":
                    secondLevelFilterCombo.addItem("Contacted");
                    secondLevelFilterCombo.addItem("Not Contacted");
                    break;
            }
            
        }
    }

    class SecondLevelFilterItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) event.getItem();
                switch (item) {
                    case "Email":
                    case "Phone":
                        leads = helper.filter(originalLeads, "Contact Preference", item);
                        break;
                    case "Business":
                    case "Personal":
                        leads = helper.filter(originalLeads, "Use Purpose", item);
                        break;
                    case "Read":
                    case "Unread":
                        leads = helper.filter(originalLeads, "Read", item);
                        break;
                    case "Contacted":
                    case "Not Contacted":
                        leads = helper.filter(originalLeads, "Contacted", item);
                        break;
                }
                tableModel.updateLeads(leads);
                tableModel.fireTableDataChanged();
            }
        }
    }
    
    class SortByItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
           if (event.getStateChange() == ItemEvent.SELECTED) {
               
               switch ((String) event.getItem()) {
                   case "Name":
                       leads.sort(Comparator.comparing(a -> a.getFirstName() + " " + a.getLastName()));
                       break;
                   case "Phone Number":
                       leads.sort(Comparator.comparing(a -> a.getPhoneNumber()));
                       break;
                   case "Email":
                       leads.sort(Comparator.comparing(a -> a.getEmailAddress()));
                       break;
                   case "Contact Preference":
                       leads.sort(Comparator.comparing(a -> a.getContactPreference()));
                       break;
                   case "Contact Time":
                       leads.sort(Comparator.comparing(a -> a.getContactTime()));
                       break;
                   case "Use Purpose":
                       leads.sort(Comparator.comparing(a -> a.getUsePurpose()));
                       break;
                   case "Read":
                       leads.sort(Comparator.comparing(a -> a.getRead()));
                       break;
                   case "Contacted":
                       leads.sort(Comparator.comparing(a -> a.getContacted()));
                       break;
                   default:
                       break;
               }
               tableModel.fireTableDataChanged();
           }
        }
    }
    
    class ExportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setSelectedFile(new File("leads.csv"));

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
                    for (Lead l : leads) {
                        fw.write(l.toCSVLine() + "\n");
                    }
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    class DetailActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRow != -1) {
                int row = selectedRow;
                updateLeadRead(row);
                openDetailWindow(row);
            }
        }
    }
    
    class DeleteActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (selectedRow != -1) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Do you want to continue to delete the contact",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    Lead lead = leads.get(selectedRow);
                    tableModel.removeRow(selectedRow);
                    helper.removeAndSave(lead);
                    
                    // sync delete between leads and originalLeads
                    for (int i = originalLeads.size() - 1; i >= 0 ; i--)
                    {
                        if (originalLeads.get(i).getEmailAddress().equals(lead.getEmailAddress())) {
                            originalLeads.remove(i);
                        }
                    }
                }
            }
        }
    }
    
    class TableMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2 && selectedRow != -1) {
                int row = selectedRow;
                updateLeadRead(row);
                openDetailWindow(row);
            }
        }
    }
    
    public static void main(String[] args) {
        new LeadsTableUI("bae705d7-20da-4ee2-871f-345b2271992b");
    }
}

