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
    
    private LeadDataHelper helper;
    private List<Lead> leads;
    
    private LeadsTableModel tableModel;
    private int selectedRow = -1;
    
    private String[] FILTER_ITEMS = {"--Select filter--", "1", "2"};
    private String[] SORT_BY_ITEMS = {"--Select sort--", "Name", "Phone Number", "Email", 
                                      "Contact Preference","Contact Time", "Use Purpose", "Contacted"};

    public LeadsTableUI(String dealerName) {
        helper = LeadDataHelper.instance();
        leads = helper.getMergedLeads(dealerName);
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setupHeaderUI(JPanel mainPanel) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        // Filter Label 
        JLabel filterLabel = new JLabel("Filter");
        headerPanel.add(filterLabel);
        
        // Filter ComboBox
        JComboBox<String> filterCombo = new JComboBox<String>(FILTER_ITEMS);
        filterCombo.addItemListener(new FilterItemChangeListener());
        headerPanel.add(filterCombo);
        
        // Sort By Label
        JLabel sortByLabel = new JLabel("Sort by");
        headerPanel.add(sortByLabel);
        
        //Sort By ComboxBox
        JComboBox<String> sortByCBox = new JComboBox<String>(SORT_BY_ITEMS);
        sortByCBox.addItemListener(new SortByItemChangeListener());
        headerPanel.add(sortByCBox);
        
        // Export Button
        JButton exportBtn = new JButton("Export");
        exportBtn.addActionListener(new ExportActionListener());
        headerPanel.add(exportBtn);
        
        // Detail Button
        detailBtn = new JButton("Detail");
        detailBtn.addActionListener(new DetailActionListener());
        headerPanel.add(detailBtn);
        
        // Delete Button
        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new DeleteActionListener());
        headerPanel.add(deleteBtn);
        
        // disable details and delete buttons
        updateHeaderBtnStatus(false);
        
        mainPanel.add(headerPanel);
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
                if (selectedRow == -1) {
                    updateHeaderBtnStatus(false);
                }
                else {
                    updateHeaderBtnStatus(true);
                }
            }
        });

        // Set table fonts
        table.getTableHeader().setFont(new Font("Baskerville", Font.BOLD, 14));
        table.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        // Set Table style
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setRowHeight(30);
        table.setBackground(getContentPane().getBackground());
        
        // jScrollPane
        JScrollPane jScrollPane = new JScrollPane(table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(1000, 600));
        jScrollPane.setBackground(getContentPane().getBackground());
        
        mainPanel.add(jScrollPane);
    }
    
    private void display() {
        this.setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        pack();
    }
    
    private void openDetailWindow(int row) {
        Lead lead = leads.get(row);
        Vehicle[] vehicles = helper.getVehiclesByEmail(lead.getDealerName(), lead.getEmailAddress());
        new LeadDetailsUI(lead, vehicles).buildGUI();
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
        public void itemStateChanged(ItemEvent event) {
           if (event.getStateChange() == ItemEvent.SELECTED) {
              String item = (String)event.getItem();
              //TODO do something with object
              System.out.println(item);
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
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    Lead lead = leads.get(selectedRow);
                    tableModel.removeRow(selectedRow);
                    helper.removeAndSave(lead);
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

