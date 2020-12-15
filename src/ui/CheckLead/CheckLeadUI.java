package ui.CheckLead;

import dao.*;
import dto.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.table.DefaultTableCellRenderer;


public class CheckLeadUI extends JFrame {
    private JPanel mainPanel, functionPanel, tablePanel;
    private JTable table;
    private JLabel first, second;
    private JButton export, detail, delete;
    LeadDataHelper helper;
    private List<Lead> forms;
    private TableModel tableModel;
    private JComboBox filter, sortBy;
    private Font tableFont, tableHeadFont;
    private JScrollPane jScrollPane;
    private List<List<String>> mergedVehicleIds;
    private Map<Lead, List<String>> LeadWithIds;

    public CheckLeadUI(String dealerName) {
        helper = LeadDataHelper.instance();
        helper.mergeLeadsHelper(dealerName);
        forms = helper.getMergedLeads();
        mergedVehicleIds = helper.getMergedVehicleIds();
        LeadWithIds = IntStream.range(0, forms.size()).boxed()
                .collect(Collectors.toMap(forms::get, mergedVehicleIds::get));
        tableModel = new LeadFormsTableModel(forms);
        table = new JTable(tableModel);
        create();
        addComponents();
        display();
    }

    private void addComponents() {

        functionPanel = new JPanel();
        functionPanel.setLayout(new FlowLayout());
        functionPanel.add(first);
        functionPanel.add(filter);
        functionPanel.add(second);
        functionPanel.add(sortBy);
        functionPanel.add(export);
        functionPanel.add(detail);
        functionPanel.add(delete);

        jScrollPane = new JScrollPane(table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(1000, 600));
        jScrollPane.setBackground(getContentPane().getBackground());
        mainPanel = new JPanel();
        tablePanel = new JPanel();
        tablePanel.add(jScrollPane);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(functionPanel);

        mainPanel.add(tablePanel);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    private void display() {
        this.setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        pack();
    }

    private void create() {
        first = new JLabel("Filter");
        second = new JLabel("Sort by");
        export = new JButton("Export");
        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    DataPersistence dp = new DataPersistence();
                    dp.writeLead(forms.get(i));
                }
            }
        });
        detail = new JButton("Detail");
        detail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    List<String> vehicleIds = LeadWithIds.get(forms.get(i));
                    Vehicle[] vehicles = new Vehicle[vehicleIds.size()];
                    for (int j = 0; j < vehicleIds.size(); j++) {
                        vehicles[j] = helper.getVehicle(vehicleIds.get(j));
                    }
                    new DetailsWindow(forms.get(i), vehicles).buildGUI();
                    forms.get(i).setRead(true);
                    table.updateUI();
                }
            }
        });

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    int result = JOptionPane.showConfirmDialog(table,
                            "Do you want to continue to delete the contact",
                            "Delete window",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION) {
                        ((LeadFormsTableModel) tableModel).removeRow(i);
                    }
                }
            }
        });


        String[] f = {"--Select filter--", "1", "2"};
        filter = new JComboBox(f);
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) filter.getSelectedItem();
                if (s == "car") {

                }
            }
        });

        String[] s = {"--Select sort--", "Name", "Phone Number", "Email", "Contact Preference",
                                        "Contact Time", "Use Purpose", "Contacted"};
        sortBy = new JComboBox(s);
        sortBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) sortBy.getSelectedItem();
                if (s == "Name") {
                    forms.sort(Comparator.comparing(a -> a.getFirstName() + " " + a.getLastName()));
                    table.updateUI();
                }
                if (s == "Phone Number") {
                    forms.sort(Comparator.comparing(a -> a.getPhoneNumber()));
                    table.updateUI();
                }
                if (s == "Email") {
                    forms.sort(Comparator.comparing(a -> a.getEmailAddress()));
                    table.updateUI();
                }
                if (s == "Contact Preference") {
                    forms.sort(Comparator.comparing(a -> a.getContactPreference()));
                    table.updateUI();
                }
                if (s == "Contact Time") {
                    forms.sort(Comparator.comparing(a -> a.getContactTime()));
                    table.updateUI();
                }
                if (s == "Use Purpose") {
                    forms.sort(Comparator.comparing(a -> a.getUsePurpose()));
                    table.updateUI();
                }
                if (s == "Contacted") {
                    forms.sort(Comparator.comparing(a -> a.getContacted()));
                    table.updateUI();
                }
            }
        });


        createTableComponents();


    }



    private void createTableComponents() {

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    if (e.getClickCount() == 2) {
                        List<String> vehicleIds = LeadWithIds.get(forms.get(i));
                        Vehicle[] vehicles = new Vehicle[vehicleIds.size()];
                        for (int j = 0; j < vehicleIds.size(); j++) {
                            vehicles[j] = helper.getVehicle(vehicleIds.get(j));
                        }
                        new DetailsWindow(forms.get(i), vehicles).buildGUI();
                        forms.get(i).setRead(true);
                        table.updateUI();
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                           int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (forms.get(row).getRead()) {
                    c.setFont(new Font("Baskerville", Font.PLAIN, 18));
                }
                return this;
            }
        });
        tableFont = new Font("Baskerville", Font.BOLD, 18);
        tableHeadFont = new Font("Baskerville", Font.PLAIN, 19);


        JTableHeader head = this.table.getTableHeader();
        head.setFont(tableHeadFont);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.setBackground(getContentPane().getBackground());


    }

    public static void main(String[] args) {
        new CheckLeadUI("bae705d7-20da-4ee2-871f-345b2271992b");
    }
}

