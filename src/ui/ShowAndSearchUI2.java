    package ui;


    import dao.Special;
    import service.CountdownTimeJob;
    import service.IncentiveApi;
    import service.IncentiveApiImpl;
    import javax.imageio.ImageIO;
    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableCellRenderer;
    import javax.swing.table.TableColumn;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.net.MalformedURLException;
    import java.net.URL;
    import java.util.*;

    public class ShowAndSearchUI2 extends JFrame {

        private JPanel sortPanel;
        private HashSet<String> selected;
        private JScrollPane jScrollPane2;
        private JLabel pageHeading;
        private static JTable filterDisplay;
        private static JTable vehicleDisplay;
        private JPanel filterPanel;
        private JPanel mainDisplay;
        private DefaultTableModel model;
        private final int[] selectedSort = {0};
        private HashMap<String, HashSet<String>> container;
        String[] dealerInventoryData;
        ArrayList<String[]> fullInventoryData;
        static String dealerName= "gmps-aj-dohmann";
        private static final String PATH = "././data/";
        // to determine showing sorted data or default data
        private boolean sortingImplemented = false;

        // for case6
        private IncentiveApi incentiveApi;
        private IncentiveUI incentiveUI;
        private CountdownTimeJob timeJob;


        public ShowAndSearchUI2(String dealerName) {
            // for case6
            incentiveUI = new IncentiveUI();
            timeJob = new CountdownTimeJob();
            timeJob.addObserver(incentiveUI);

            this.dealerName=dealerName;
            initComponents();

        }

        public ShowAndSearchUI2() {
            initComponents();
        }


        @SuppressWarnings("unchecked")
        private void initComponents() {
            mainDisplay = new JPanel();
            pageHeading = new JLabel();
            filterPanel = new JPanel();
            jScrollPane2 = new JScrollPane();
            vehicleDisplay = new JTable();
            filterDisplay = new JTable();
            sortPanel = new JPanel();
            model = (DefaultTableModel) vehicleDisplay.getModel();
            fullInventoryData = new ArrayList<>();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainDisplay.setBackground(new Color(153, 153, 153));
            pageHeading.setFont(new Font("Tahoma", 0, 48)); // NOI18N
            pageHeading.setText("INVENTORY");
            filterPanel.setBackground(new Color(204, 204, 204));
            GroupLayout filterPanelLayout = new GroupLayout(getFilterPanel());
            filterPanel.setLayout(filterPanelLayout);
            filterPanelLayout.setHorizontalGroup(
                    filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );
            filterPanelLayout.setVerticalGroup(
                    filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 128, Short.MAX_VALUE)
            );
            jScrollPane2.setViewportView(getTable(dealerName));
            sortPanel.setBackground(new java.awt.Color(204, 204, 204));
            GroupLayout sortPanelLayout = new GroupLayout(getSortPanel());
            sortPanel.setLayout(sortPanelLayout);
            sortPanelLayout.setHorizontalGroup(
                    sortPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );
            sortPanelLayout.setVerticalGroup(
                    sortPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 117, Short.MAX_VALUE)
            );
            GroupLayout mainDisplayLayout = new GroupLayout(getMainDisplayPanel());
            mainDisplay.setLayout(mainDisplayLayout);
            mainDisplayLayout.setHorizontalGroup(
                    mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, mainDisplayLayout.createSequentialGroup()
                                    .addContainerGap(309, Short.MAX_VALUE)
                                    .addComponent(pageHeading, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                                    .addGap(288, 288, 288))
                            .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING)
                            .addComponent(filterPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sortPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            mainDisplayLayout.setVerticalGroup(
                    mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(mainDisplayLayout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(pageHeading)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(filterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sortPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(mainDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(mainDisplay,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
            );
            pack();
        }// </editor-fold>



        JPanel getMainDisplayPanel() {
            // init panel
            mainDisplay = new JPanel();
            // add Component to mailDisplayPanel;
            mainDisplay.add(getFilterPanel());
            mainDisplay.add(getSortPanel());
            mainDisplay.add(getTable(dealerName));
            return mainDisplay;
        }


        private JPanel getFilterPanel() {
            //System.out.println("filter panel loading...");
            //code for UI for filterPanel  ie filters for searching
            container = new HashMap<String, HashSet<String>>();
            filterPanel = new JPanel();
            filterPanel.setBackground(Color.red);
            filterPanel.add(new JLabel("FILTER"));
            addFilterChoice("MODEL", filterPanel);
            addFilterChoice("TYPE", filterPanel);
            addFilterChoice("ABOVE PRICE", filterPanel);
            addFilterChoice("BELOW PRICE", filterPanel);
            ((AbstractButton) filterPanel.add(new JButton("Clear All"))).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    container.clear();
                    //System.out.println(container.size());
                }
            });
            return filterPanel;
        }

        private void addFilterChoice(String choice, JPanel panel) {
            JButton choiceButton = new JButton(choice);
            JPopupMenu menu = new JPopupMenu();
            if (choice.equals("CATEGORY")) {
                menu.add(new JCheckBoxMenuItem("New"));
                menu.add(new JCheckBoxMenuItem("Certified Pre-Owned"));
                menu.add(new JCheckBoxMenuItem("Pre-Owned"));
            } else if (choice.equals("MAKE")) {
                menu.add(new JCheckBoxMenuItem("Chevrolet"));
                menu.add(new JCheckBoxMenuItem("BOW"));
                menu.add(new JCheckBoxMenuItem("Jeep"));
                menu.add(new JCheckBoxMenuItem("Mini"));
                menu.add(new JCheckBoxMenuItem("Nissan"));
                menu.add(new JCheckBoxMenuItem("Toyota"));
            } else if (choice.equals("MODEL")) {
                menu.add(new JCheckBoxMenuItem("Acadia"));
                menu.add(new JCheckBoxMenuItem("Blazer"));
                menu.add(new JCheckBoxMenuItem("Bolt EV"));
            } else if (choice.equals("TYPE")) {
                menu.add(new JCheckBoxMenuItem("Car"));
                menu.add(new JCheckBoxMenuItem("Cargo Van"));
                menu.add(new JCheckBoxMenuItem("SUV"));
                menu.add(new JCheckBoxMenuItem("Truck"));
                menu.add(new JCheckBoxMenuItem("Van"));
                menu.add(new JCheckBoxMenuItem("Wagon"));
            } else if (choice.equals("BODY STYLE")) {
                menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Long Bed"));
                menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Short Bed"));
                menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Standard Bed"));
            } else if (choice.equals("Year")) {
                menu.add(new JCheckBoxMenuItem("2009"));
                menu.add(new JCheckBoxMenuItem("2010"));
                menu.add(new JCheckBoxMenuItem("2011"));
                menu.add(new JCheckBoxMenuItem("2012"));
                menu.add(new JCheckBoxMenuItem("2013"));
                menu.add(new JCheckBoxMenuItem("2014"));
                menu.add(new JCheckBoxMenuItem("2015"));
                menu.add(new JCheckBoxMenuItem("2016"));
                menu.add(new JCheckBoxMenuItem("2017"));
                menu.add(new JCheckBoxMenuItem("2018"));
                menu.add(new JCheckBoxMenuItem("2019"));
                menu.add(new JCheckBoxMenuItem("2020"));
                menu.add(new JCheckBoxMenuItem("2021"));
            } else if (choice.equals("ABOVE PRICE")) {
                menu.add(new JCheckBoxMenuItem("1000"));
                menu.add(new JCheckBoxMenuItem("5000"));
                menu.add(new JCheckBoxMenuItem("10000"));
                menu.add(new JCheckBoxMenuItem("15000"));
                menu.add(new JCheckBoxMenuItem("20000"));
                menu.add(new JCheckBoxMenuItem("25000"));
                menu.add(new JCheckBoxMenuItem("30000"));
            } else if (choice.equals("BELOW PRICE")) {
                menu.add(new JCheckBoxMenuItem("1000"));
                menu.add(new JCheckBoxMenuItem("5000"));
                menu.add(new JCheckBoxMenuItem("10000"));
                menu.add(new JCheckBoxMenuItem("15000"));
                menu.add(new JCheckBoxMenuItem("20000"));
                menu.add(new JCheckBoxMenuItem("25000"));
                menu.add(new JCheckBoxMenuItem("30000"));
                menu.add(new JCheckBoxMenuItem("35000"));
                menu.add(new JCheckBoxMenuItem("40000"));
                menu.add(new JCheckBoxMenuItem("45000"));
            } else {
                menu.add(new JCheckBoxMenuItem("Wait for coming"));
            }
            // add action listener to each ChekcBoxMenuItem
            for (Component item: menu.getComponents()) {
                ((AbstractButton) item).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String cur = e.toString().split(",")[1].substring(4);
                        if (!((JCheckBoxMenuItem)item).isSelected()) {
                            container.get(choice).remove(cur);
                        } else {
                            if (!container.containsKey(choice)) {
                                container.put(choice, new HashSet<String>());
                            }
                            container.get(choice).add(cur.toLowerCase());
                        }
                        //System.out.println(container);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                });}

            // combine each button with CheckBoxMenu
            choiceButton.setAction(new AbstractAction(choice) {
                /**
                 *
                 */
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.show(choiceButton, 0, choiceButton.getHeight());
                }
            });

            panel.add(choiceButton);
        }



        private JPanel getSortPanel() {
            // System.out.println("sort panel is loading...");
            //UI for sorting Panel for sorting the result on the basis on certain criterias
            sortPanel = new JPanel();
            sortPanel.setSize(600, 200);
            sortPanel.add(new Label("How would you like to sort the results?"));
            JPanel temp = new JPanel();
            JTextArea text = new JTextArea("");
            temp.add(text);
            String[] sortOptions = {"No Preference", "Price: High to low", "Price: Low to High", "Year: High to Low",
                    "Year: Low to High"};
            JComboBox sortList = new JComboBox(sortOptions);
            sortList.setSelectedIndex(0);
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int s = (int) sortList.getSelectedIndex();
                    switch(s) {
                        case 0:
                            selectedSort[0] = 0;
                        case 1:
                            text.setText("Selected: Sort price from high to low");
                            selectedSort[0] = 1;
                            break;
                        case 2:
                            text.setText("Selected: Sort price form low to high");
                            selectedSort[0] = 2;
                            break;
                        case 3:
                            text.setText("Selected: Sort year from high to low");
                            selectedSort[0] = 3;
                            break;
                        case 4:
                            text.setText("Selected: Sort year from low to high");
                            selectedSort[0] = 4;
                            break;
                    }
                    text.setEditable(false);
                }
            };
            sortList.addActionListener(actionListener);
            sortPanel.add(sortList);
            sortPanel.add(temp);
            JButton confirm = new JButton("Confirm");
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // call the sort method to implement sorting based on user's selection
                    ArrayList<String[]> filteredList = FiltAndSort.filter(fullInventoryData, container);
                    ArrayList<String[]> sortedList = FiltAndSort.sort(selectedSort[0], filteredList);
    //                System.out.println(container);
    //               for (String[] a : sortedList) {
    //                    System.out.println(Arrays.toString(a));
    //               }
    //                setTableCellValues(sortedList);
                    jScrollPane2.setViewportView(getTable(sortedList));
                }
            });
            sortPanel.add(confirm);
            sortPanel.setBackground(Color.white);
            return sortPanel;
        }


        private JTable getTable(ArrayList<String[]> filterVehicle) {
            //System.out.println("filtertable is loading...");
            fullInventoryData = readDealerInventory(ShowAndSearchUI2.dealerName); //Reading Inventory data of dealer
            filterDisplay = new JTable();
            ShowAndSearchUI2.filterDisplay.setRowHeight(100);
            Object[] columns = new Object[]{"Model", "Type", "Year", "Price", "View More", "Show Incentives", "Image", "Special Price"};
            DefaultTableModel model = (DefaultTableModel) ShowAndSearchUI2.filterDisplay.getModel();
            model.setColumnIdentifiers(columns);
            ShowAndSearchUI2.filterDisplay.setModel(model);
            try{
                setTableCellValues(filterVehicle, ShowAndSearchUI2.filterDisplay);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            addVehicleDisplayTableListner(ShowAndSearchUI2.filterDisplay);

            return ShowAndSearchUI2.filterDisplay;
        }

        private JTable getTable(String dealerName) {
            //System.out.println("table is loading...");
            fullInventoryData=readDealerInventory(ShowAndSearchUI2.dealerName); //Reading Inventory data of dealer
            vehicleDisplay.setRowHeight(150);
            Object[] columns=new Object[]{"Model","Type","Year","Price","View More","Show Incentives","Image","Special Price"};
            vehicleDisplay = new JTable();
            DefaultTableModel model=(DefaultTableModel) vehicleDisplay.getModel();
            model.setColumnIdentifiers(columns);
            vehicleDisplay.setModel(model);
            try {
                setTableCellValues(fullInventoryData,vehicleDisplay);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            addVehicleDisplayTableListner(vehicleDisplay);

            return vehicleDisplay;
        }

        private void addVehicleDisplayTableListner(JTable vehicleDisplay) {
            vehicleDisplay.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String[] data=new String[10];
                    JTable source = (JTable)e.getSource();
                    int row = source.rowAtPoint( e.getPoint() );
                    int column = source.columnAtPoint( e.getPoint() );
                    //Storing selected data for UC3
                    ArrayList<String[]> selectedRowData = new ArrayList<>();
                    selectedRowData.add(fullInventoryData.get(row));
                    for(int i=0;i<selectedRowData.size();i++){
                        for(int j=0;j<selectedRowData.get(i).length;j++){
                            data=selectedRowData.get(i);
                            System.out.println(selectedRowData.get(i)[j]);
                        }
                    }
                    if(column==4) {
                        // JOptionPane.showMessageDialog(null, "Call UC3");
                        new VehicleDetails(data).frame.setVisible(true);
                    }
                    if(column==5){
                        //JOptionPane.showMessageDialog(null, "Call UC6");
                        // for case6
                        if (incentiveApi == null)
                            incentiveApi = new IncentiveApiImpl();
                            String dealerName = fullInventoryData.get(row)[1];
                            String vehicleId = fullInventoryData.get(row)[0];
                            String price = fullInventoryData.get(row)[8];
                            SpecialModel specialModel = incentiveApi.updateSpecialPrice(dealerName, vehicleId, price);
                            Special special = specialModel.getSpecial();

                        if (special != null) {
                            timeJob.start(specialModel);
                            JOptionPane.showConfirmDialog(null, incentiveUI, "Incentive details",
                                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                            if (specialModel.getSpecial().getEndDate().getTime() < new Date().getTime()) {
                                source.getComponents();
                            }
                            timeJob.stop();
                        }
                    }


                }
                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }
            });
        }

        private void setTableCellValues(ArrayList<String[]> arrayListOfString,JTable jTable) throws MalformedURLException {
            //System.out.println("table cell values is loading...");
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            jTable.setModel(model);
            String vehicleImagePath;
            URL url = null;
            Image image = null;
            ImageIcon imageIcon;

            jTable.getColumn("Image").setCellRenderer(new TableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    TableColumn tbImage = jTable.getColumn("Image");
                    tbImage.setMaxWidth(200);
                    tbImage.setMinWidth(200);
                    return (Component) value;
                }
            });
            jTable.getColumn("View More").setCellRenderer(new TableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    TableColumn tbButton = jTable.getColumn("View More");
                    tbButton.setMaxWidth(150);
                    tbButton.setMaxWidth(150);
                    return (Component) value;
                }
            });
            jTable.getColumn("Show Incentives").setCellRenderer(new TableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    TableColumn tbButton = jTable.getColumn("Show Incentives");
                    tbButton.setMaxWidth(150);
                    tbButton.setMaxWidth(150);
                    return (Component) value;
                }
            });

            //System.out.println(vehicleImagePath);

            for (int i = 0; i < arrayListOfString.size(); i++) {
                vehicleImagePath = arrayListOfString.get(i)[9];
                // System.out.println(vehicleImagePath);
                try {
                    url = new URL(vehicleImagePath);
                    image = ImageIO.read(url);
                    imageIcon = new ImageIcon(image);
                } catch (MalformedURLException e) {
                    //e.printStackTrace();
                    vehicleImagePath = PATH + "404NotFound.png";
                    imageIcon = new ImageIcon(vehicleImagePath);

                } catch (IOException e) {
                    vehicleImagePath = PATH + "404NotFound.png";
                    imageIcon = new ImageIcon(vehicleImagePath);

                }
                Image img = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel();
                JButton viewMore_button = new JButton("View More");
                JButton showIncentives = new JButton("Show Incentives");
                // for case6
                String specialPrice = "none";
                if (!"price".equals(arrayListOfString.get(i)[8])) {
                    incentiveApi = new IncentiveApiImpl();

                    String dealerName = arrayListOfString.get(i)[1];
                    String vehicleId = arrayListOfString.get(i)[0];
                    String price = arrayListOfString.get(i)[8];
                    SpecialModel specialModel = incentiveApi.updateSpecialPrice(dealerName, vehicleId, price);
                    Special special = specialModel.getSpecial();
                    if (special != null) {
                        float sPrice = specialModel.getSpecialPrice();
                        specialPrice = String.valueOf(sPrice);
                    } else {
                        showIncentives.setVisible(false);
                        specialPrice = "none";
                    }
                    imageLabel.setIcon(new ImageIcon(img));
                    model.addRow(new Object[]{arrayListOfString.get(i)[5], arrayListOfString.get(i)[7], arrayListOfString.get(i)[3],
                            arrayListOfString.get(i)[8], viewMore_button, showIncentives, imageLabel, specialPrice});
                }

            }
        }


        public ArrayList<String[]> readDealerInventory (String dealerName){
            fullInventoryData = new ArrayList<String[]>();
            {
                String line = "";
                String splitBy = "~";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(PATH + dealerName));
                    while ((line = br.readLine()) != null) {

                        dealerInventoryData = line.split(splitBy);
                        fullInventoryData.add(dealerInventoryData);
    //                    for(int i=0;i<fullInventoryData.size();i++){
    //                        for(int j=0;j<fullInventoryData.get(i).length;j++){
    //                            System.out.println(fullInventoryData.get(i)[j]);
    //                        }
    //
    //                    }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return fullInventoryData;
        }


        public static void main(String args[]) {
            //System.out.println("GUI start....");
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ShowAndSearchUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(ShowAndSearchUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(ShowAndSearchUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(ShowAndSearchUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new ShowAndSearchUI2(dealerName).setVisible(true);
                }
            });

        }
    }


