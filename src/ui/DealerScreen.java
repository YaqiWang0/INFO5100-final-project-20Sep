/* Use Case I Screen */
package ui;
import incentive.*;
import dao.*;

import service.*;
//import ui.*; 
import validation.*;
import dto.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;



public class DealerScreen{
    private JFrame frame;
    private JPanel panelLeft, panelRight;
    // private JTextField textFieldDealerName;
    private JTextField textFieldZipCode;
  //  private JComboBox<String> comboBox;
    private JButton btnSearch;
    private JRadioButton c1,c2;
    private JTable table;
        String dName;
   
    int signal = 0;
    
    private ArrayList<Dealer> dealerList=new ArrayList<>(); 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DealerScreen window = new DealerScreen();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private DealerScreen() {

    	        frameAndPanel();
    	        // dealerName();
    	        jRadioBtn();
    	        zipCode();      
    	        initializeSearchButton();
    	    }

    	    // complete frame
    	    private void frameAndPanel() {
    	        frame = new JFrame();
    	        frame.setTitle("Dealer Locator Website");
    	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        frame.setSize(1000,650);
    	        frame.getContentPane().setLayout(new BorderLayout());
    	        initializeLeftPanel();
    	        initializeRightPanel();
    	    }
    	    
    	    // left panel containing the form
    	    private void initializeLeftPanel() {
    	        panelLeft= new JPanel();
    	        panelLeft.setBackground(new Color(165, 194, 147));
    	        panelLeft.setLayout(null);
    	        panelLeft.setPreferredSize(new Dimension(280,650));
    	        frame.getContentPane().add(panelLeft,BorderLayout.WEST);
    	    }

    	    // right panel containing the table
    	    private void initializeRightPanel() {
    	        panelRight= new JPanel();
    	        panelRight.setPreferredSize(new Dimension(720,650));
    	        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
    	        frame.getContentPane().add(panelRight,BorderLayout.CENTER);
    	    }

    	    
    	    // initializing dealer's name
    	    /*
    	    private void dealerName() {
    	        JLabel lblName = new JLabel("Dealer Name is Optional: ");
    	        lblName.setBounds(10, 50, 200, 14);
    	        lblName.setForeground(Color.WHITE);
    	        panelLeft.add(lblName);
    	        textFieldDealerName = new JTextField();
    	        textFieldDealerName.setBounds(10, 90, 200, 20);
    	        textFieldDealerName.setColumns(10);
    	        panelLeft.add(textFieldDealerName);
    	        highlightDealerName();
    	        dealerNameValidation();
    	    }*/
    	    
    	   // validating dealer's name
    	    private void dealerNameValidation(){
    	    	textFieldZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
    	            public void focusGained(java.awt.event.FocusEvent evt) {
    	            }
    	            public void focusLost(java.awt.event.FocusEvent evt) {
    	                String dealerName = textFieldZipCode.getText();
    	                // !dealerName.isEmpty()&&!Validator.isValidDealerName(dealerName)
    	                if(!dealerName.isEmpty()&&!Validator.isValidDealerName(dealerName)) {
    	                    JOptionPane.showMessageDialog(frame, "Invalid dealer name input. Please try again.");
    	                }
    	            }
    	        });
    	    }
    	  /*  // highlighting if dealer's name textfield is empty
    	    private void highlightDealerName(){
    	        Border defaultBorder = textFieldDealerName.getBorder();
    	        textFieldDealerName.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
    	        textFieldDealerName.getDocument().addDocumentListener(new DocumentListener() {
    	        // Interface DocumentListener
    	            @Override
    	            public void insertUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }
    	            @Override
    	            public void removeUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }
    	            @Override
    	            public void changedUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }
    	            private void maybeHighlight() {
    	                if (textFieldDealerName.getText().trim().length() != 0) {
    	                    textFieldDealerName.setBorder(defaultBorder);
    	                } else {
    	                    textFieldDealerName.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
    	                }
    	            }
    	        });
    	    }
    		*/
    	    
    	    
    	    private void jRadioBtn(){
    	    	
    	    	 c1=new JRadioButton("Zipcode");
    	    	 c2=new JRadioButton("Dealer Name");
    	    	 ButtonGroup group = new ButtonGroup();  
    	     	 group.add(c1);
    	     	 group.add(c2); 
    	     	c1.setBackground(new Color(165, 194, 147));
    	     	c1.setForeground(Color.WHITE);
    	     	c2.setForeground(Color.WHITE);
    	    	c2.setBackground(new Color(165, 194, 147));
    	    //	c1.setBorder(BorderFactory.createLineBorder(new Color(210,105,30), 3));
    	    //	c2.setBorder(BorderFactory.createLineBorder(new Color(210,105,30), 3));
    	    	c1.setBounds(10, 230, 100, 30);
    	        
    	    	c2.setBounds(120, 230, 100, 30);
    	    	
    	    	
    	    	  c1.setActionCommand("Zipcode");
    	          c2.setActionCommand("Dealer Name");
    	    	panelLeft.add(c1);
    	    	panelLeft.add(c2);
    	    //	panelLeft.add(c2,BorderLayout.SOUTH);	
    	    //c1.addActionListener(actionEvent ae->{boolean c1.isSelected()=true;});
    	   
    	    }

    	    // handling zip code
    	    private void zipCode() {
    	        JLabel lblPhone = new JLabel("Search by: ");
    	        lblPhone.setBounds(10, 90, 270,20);
    	        lblPhone.setForeground(Color.WHITE);
    	        lblPhone.setFont(new java.awt.Font("Dialog", 1, 18));
    	        panelLeft.add(lblPhone);
    	        textFieldZipCode = new JTextField();
    	        textFieldZipCode.setBounds(10, 130, 200, 30);
    	        textFieldZipCode.setColumns(10);
    	        panelLeft.add(textFieldZipCode);
    	        
    	        highlightZipCode();
    	        if(textFieldZipCode.getText()==null){}
    	        else {
    	        if(c1.isSelected()){
    	        validateZipCode();}else if (c2.isSelected()){
    	        	dealerNameValidation();
    	        }}
    	        
    	    }

    	    // highlighting zipcode textfield if it is empty
    	    private void highlightZipCode(){
    	        Border defaultBorder2 = textFieldZipCode.getBorder();
    	        textFieldZipCode.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
    	        textFieldZipCode.getDocument().addDocumentListener(new DocumentListener() {
    	            @Override
    	            public void insertUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }

    	            @Override
    	            public void removeUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }

    	            @Override
    	            public void changedUpdate(DocumentEvent documentEvent) {
    	                maybeHighlight();
    	            }

    	            private void maybeHighlight() {
    	                if (textFieldZipCode.getText().trim().length() != 0)
    	                {
    	                    textFieldZipCode.setBorder(defaultBorder2);
    	                }
    	                else
    	                {
    	                    textFieldZipCode.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
    	                }
    	            }
    	        });
    	    }

    	    // validation for zip code'e length
    	    private void validateZipCode(){
    	        textFieldZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
    	            public void focusGained(java.awt.event.FocusEvent evt) {
    	            }
    	            public void focusLost(java.awt.event.FocusEvent evt) {
    	                String zipCode=textFieldZipCode.getText();
    	                try {
    	                //  && !Validator.isValidZipCodeRange(zipCode)
    		                if(!textFieldZipCode.getText().isEmpty()&& !Validator.isValidZipCodeRange(zipCode)) {
    		                	// if zip code is invalid
    		                    JOptionPane.showMessageDialog(frame, "This is a invalid Zip Code, Please enter again \nHint: Zip Code should be 5-digit or 9-digit (ZIP+4)."); 
    		                }
    	                }
    	                catch(Exception e) {
    	                	e.printStackTrace();
    	                }
    	            }
    	        });
    	    }
    	    
    	    // method to handle search button
    	    private void initializeSearchButton() {
    	        btnSearch = new JButton("Search");
    	        btnSearch.setBounds(10, 300, 180, 40);
    	        panelLeft.add(btnSearch);

    	        //Action Listener for SearchButton
    	        btnSearch.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent arg0) {
    	            	
    	            	if(c1.isSelected()){
    	                if (!Validator.isValidZipCodeRange(textFieldZipCode.getText())) {
    	                    JOptionPane.showMessageDialog(frame, "This is a invalid Zip Code, Please enter again \nHint: Zip Code should be 5-digit or 9-digit (ZIP+4). ");
    	                } 
    	                
    	                
    	                
    	                else { //call method to get list of dealers
    	                    frame.getContentPane().remove(panelRight);
    	                    initializeRightPanel();
    	                    panelRight.revalidate();
    	                    panelRight.repaint();
    	                    try {
    	                        initialiseAndCreateTable1();
    	                    } catch (Exception e) {
    	                        e.printStackTrace();
    	                    }
    	                }}else if(c2.isSelected()){
    	                	
    	                	if (!Validator.isValidDealerName(textFieldZipCode.getText())) {
    	                        JOptionPane.showMessageDialog(frame, "This is a invalid dealer name, Please enter again \nHint: dealer name should be words connnect by -. ");
    	                    } 
    	                    
    	                    
    	                    
    	                    else { //call method to get list of dealers
    	                        frame.getContentPane().remove(panelRight);
    	                        initializeRightPanel();
    	                        panelRight.revalidate();
    	                        panelRight.repaint();
    	                        try {
    	                            initialiseAndCreateTable2();
    	                        } catch (Exception e) {
    	                            e.printStackTrace();
    	                        }
    	                    }	
    	                }  
    	            }
    	        });
    	    }
    	    
    	    //CreationOfTable
    	    private void initialiseAndCreateTable1() throws Exception {
    	        String[] columns = {"ID","Dealer Name", "Miles Away","ZipCode"};
    	        DefaultTableModel model = new DefaultTableModel(columns, 0);
    	        SearchDealer dsf= new SearchDealer();     
    	        dealerList = (ArrayList<Dealer>) dsf.searchByZipCode(textFieldZipCode.getText());
    	        if(dealerList==null) {
    	            JLabel lblIncorrectUSZipcode= new JLabel(textFieldZipCode.getText() + " is not a USA Zip Code.");
    	            lblIncorrectUSZipcode.setForeground(new Color(0, 0, 0));
    	            lblIncorrectUSZipcode.setFont(new Font("Arial", Font.PLAIN, 15));
    	            lblIncorrectUSZipcode.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    	            lblIncorrectUSZipcode.setPreferredSize(new Dimension(700, 650));
    	            panelRight.add(lblIncorrectUSZipcode);
    	            panelRight.setEnabled(true);
    	        } else if(dealerList.size()>0){
    	        	//////////////////////////////////////////////////////////
    	            for(Dealer detail : dealerList) {
    	                Vector<String> row = new Vector<>();
    	                row.add(detail.getDealerId()+ " ");
    	                row.add(detail.getDealerName());
    	                row.add(detail.getDistanceInMiles()+" ");
    	                row.add(detail.getDealerAddress().getZipCode());
    	                model.addRow(row);
    	            }

    	            JTable table = new JTable(model){
    	                public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
    	                    //this is to have alternative color in the table row
    	                    Component returnComp = super.prepareRenderer(renderer, row, column);
    	                    Color darkShade = new Color(192,192,192);
    	                    Color lightShade = new Color(255,255,255);
    	                    if (!returnComp.getBackground().equals(getSelectionBackground())){
    	                        Color bg = (row % 2 == 0 ? darkShade : lightShade);
    	                        returnComp .setBackground(bg);
    	                        bg = null;
    	                    }
    	                    return returnComp;
    	                }
    	            };

    	            table.setForeground(Color.BLACK);
    	            table.setShowGrid(false);
    	            table.setShowHorizontalLines(true);
    	            table.setRowHeight(table.getRowHeight() + 20); // set row height
    	            table.setDefaultEditor(Object.class, null); // to stop the editing of table cell on double click of mouse

    	            // to make text Center Align
    	            DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
    	            rendar.setHorizontalAlignment(JLabel.CENTER);
    	            for(int x=0;x<table.getColumnCount();x++){
    	                table.getColumnModel().getColumn(x).setCellRenderer( rendar );
    	            }

    	            // Table Header
    	            JTableHeader header = table.getTableHeader();
    	            header.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    	            header.setBackground(new Color(165, 194, 147));
    	            header.setForeground(Color.BLACK);
    	            // set header size
    	            table.getTableHeader().setPreferredSize(new Dimension(700,table.getRowHeight()));
    	            // set header text to Center Align
    	            ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    	            // set different width for different columns
    	            setJTableColumnsWidth(table, 700, 10, 30, 30, 20,10);
    	            JScrollPane jScrollPane = new JScrollPane(table);
    	            panelRight.add(jScrollPane);
    	            
    	        
    	            table.addMouseListener(new java.awt.event.MouseAdapter(){

    	   	             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应

    	   	                //得到选中的行列的索引值

    	   	               int r= table.getSelectedRow();

    	   	               int c= table.getSelectedColumn();

    	   	               //得到选中的单元格的值，表格中都是字符串

    	   	               Object value= table.getValueAt(r, 1);

    	   	            String info=value.toString();

    	   	            javax.swing.JOptionPane.showMessageDialog(null,"Click OK to see the inventory of " + info +".");
    	   	            ShowAndSearchUI2 newInvent  =   new ShowAndSearchUI2(info);
    	   	            newInvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	            
    	   	            newInvent .setVisible(true);
    	   	        
    	   	        
    	   	     
    	   	             }

    	   	         });
    	        
    	        }
    	            
    	        
    	     
   	        
    	        
    	        
    	        
    	        
    	        
    	        
    	            /*
    	            use case 2 here
    	            table.addMouseListener(new MouseAdapter() {
    	                public void mouseReleased(MouseEvent me) {
    	                    if(me.getClickCount()==2){
    	                    int row = table.rowAtPoint(me.getPoint());
    	                    if(row!=-1) {
    	                        new AutomobileDealerInventoryUI02(dealerList.get(row),frame);
    	                    }}
    	                }
    	            });
    	       }  else  {
    	             JLabel lblNoDataFound= new JLabel("No Record Available" + ((textFieldDealerName.getText().length()>0)?(" with Dealer Name " +textFieldDealerName.getText().toUpperCase()):"") +
    	                     " within " + (comboBox.getSelectedItem().toString()) + " Miles of ZipCode "+ textFieldZipCode.getText());
    	             lblNoDataFound.setForeground(new Color(0, 113, 238));
    	             lblNoDataFound.setFont(new Font("Arial", Font.PLAIN, 15));
    	             lblNoDataFound.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    	             lblNoDataFound.setPreferredSize(new Dimension(700, 650));
    	             panelRight.add(lblNoDataFound);
    	             panelRight.setEnabled(true);
    	       }
    	       */
    	    }
    	    
    	    private void initialiseAndCreateTable2() throws Exception {
    	        String[] columns = {"ID","Dealer Name", "Miles Away","ZipCode"};
    	        DefaultTableModel model = new DefaultTableModel(columns, 0);
    	        SearchDealer dsf= new SearchDealer();     
    	        dealerList = (ArrayList<Dealer>) dsf.searchByName(textFieldZipCode.getText());
    	        if(dealerList==null) {
    	            JLabel lblIncorrectUSZipcode= new JLabel(textFieldZipCode.getText() + " is not a USA dearler name.");
    	            lblIncorrectUSZipcode.setForeground(new Color(0, 0, 0));
    	            lblIncorrectUSZipcode.setFont(new Font("Arial", Font.PLAIN, 15));
    	            lblIncorrectUSZipcode.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    	            lblIncorrectUSZipcode.setPreferredSize(new Dimension(700, 650));
    	            panelRight.add(lblIncorrectUSZipcode);
    	            panelRight.setEnabled(true);
    	        } else if(dealerList.size()>0){
    	        	//////////////////////////////////////////////////////////
    	            for(Dealer detail : dealerList) {
    	                Vector<String> row = new Vector<>();
    	                row.add(detail.getDealerId()+ " ");
    	                row.add(detail.getDealerName());
    	                row.add(detail.getDistanceInMiles()+" ");
    	                row.add(detail.getDealerAddress().getZipCode());
    	                model.addRow(row);
    	            }

    	            JTable table = new JTable(model){
    	                public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
    	                    //this is to have alternative color in the table row
    	                    Component returnComp = super.prepareRenderer(renderer, row, column);
    	                    Color darkShade = new Color(192,192,192);
    	                    Color lightShade = new Color(255,255,255);
    	                    if (!returnComp.getBackground().equals(getSelectionBackground())){
    	                        Color bg = (row % 2 == 0 ? darkShade : lightShade);
    	                        returnComp .setBackground(bg);
    	                        bg = null;
    	                    }
    	                    return returnComp;
    	                }
    	            };

    	            table.setForeground(Color.BLACK);
    	            table.setShowGrid(false);
    	            table.setShowHorizontalLines(true);
    	            table.setRowHeight(table.getRowHeight() + 20); // set row height
    	            table.setDefaultEditor(Object.class, null); // to stop the editing of table cell on double click of mouse

    	            // to make text Center Align
    	            DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
    	            rendar.setHorizontalAlignment(JLabel.CENTER);
    	            for(int x=0;x<table.getColumnCount();x++){
    	                table.getColumnModel().getColumn(x).setCellRenderer( rendar );
    	            }

    	            // Table Header
    	            JTableHeader header = table.getTableHeader();
    	            header.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    	            header.setBackground(new Color(165, 194, 147));
    	            header.setForeground(Color.BLACK);
    	            // set header size
    	            table.getTableHeader().setPreferredSize(new Dimension(700,table.getRowHeight()));
    	            // set header text to Center Align
    	            ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    	            // set different width for different columns
    	            setJTableColumnsWidth(table, 700, 10, 30, 30, 20,10);
    	            JScrollPane jScrollPane = new JScrollPane(table);
    	            panelRight.add(jScrollPane);
    	            
        	        table.addMouseListener(new java.awt.event.MouseAdapter(){

        	        public void mouseClicked(MouseEvent e) {
        	               int r= table.getSelectedRow();
        	               int c= table.getSelectedColumn();
        	               Object value= table.getValueAt(r, 1);
        	               String info=  value.toString();
        	               javax.swing.JOptionPane.showMessageDialog(null,"Click OK to see the inventory of "+info +". This may take a moment.");
        	               //    new ShowAndSearchUI(info).setVisible(true);
	        	              ShowAndSearchUI2 newInvent  =   new ShowAndSearchUI2(info);
	     	   	           newInvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	            
	     	   	           newInvent .setVisible(true);
        	          }

        	        });
    	        
    	        
    	        }
    	      
    	        
    	        
    	        
    	        
    	        
    	        
    	        
    	        
    	        
    	        /*
    	            use case 2 here
    	            table.addMouseListener(new MouseAdapter() {
    	                public void mouseReleased(MouseEvent me) {
    	                    if(me.getClickCount()==2){
    	                    int row = table.rowAtPoint(me.getPoint());
    	                    if(row!=-1) {
    	                        new AutomobileDealerInventoryUI02(dealerList.get(row),frame);
    	                    }}
    	                }
    	            });
    	       }  else  {
    	             JLabel lblNoDataFound= new JLabel("No Record Available" + ((textFieldDealerName.getText().length()>0)?(" with Dealer Name " +textFieldDealerName.getText().toUpperCase()):"") +
    	                     " within " + (comboBox.getSelectedItem().toString()) + " Miles of ZipCode "+ textFieldZipCode.getText());
    	             lblNoDataFound.setForeground(new Color(0, 113, 238));
    	             lblNoDataFound.setFont(new Font("Arial", Font.PLAIN, 15));
    	             lblNoDataFound.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    	             lblNoDataFound.setPreferredSize(new Dimension(700, 650));
    	             panelRight.add(lblNoDataFound);
    	             panelRight.setEnabled(true);
    	       }
    	       */
    	    }
    	    


    	    // method to set the column width dynamically
    	    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
    	                                             double... percentages) {
    	        double total = 0;
    	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
    	            total += percentages[i];
    	        }

    	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
    	            TableColumn column = table.getColumnModel().getColumn(i);
    	            column.setPreferredWidth((int)
    	                    (tablePreferredWidth * (percentages[i] / total)));
    	        }
    	    }
    	    

    	}