package ui.CheckLead;


import dao.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.ThreadFactory;


public class DetailsWindow {
    private Customer customer;
    private Vehicle vehicles[];
    private int vehicleIndex;
    private String userNotes;
    private String[] tabNames = {"Customer Info", "Vehicle Info", "User Notes"};
    JTextArea vehicleInfoTextArea;
    JTextArea userNotesTextArea;
    JTextArea userNotesReplyTextArea;
    JButton replyButton, saveButton;
    JButton vehicleInfoPreviousButton, vehicleInfoNextButton;
    private JTabbedPane mainPanel;
    private JFrame theFrame;


    public DetailsWindow (Customer customer, Vehicle[] vehicles, String userNotes) {
	    this.customer = customer;
	    this.vehicles = vehicles;
	    this.userNotes = userNotes;
	    vehicleIndex = 0;
    }
    public static void main (String[] args) {
        Vehicle[] vehicles = {new Vehicle("1", "2015", "Honda", null, true,
                    "35000", "black", "black", BodyType.SUV, "0"),
                new Vehicle("2", "2020", "Toyota", null, false,
                        "25000", "black", "black", BodyType.CAR, "40000")};
        Address address = new Address("401 Terry Ave N #103", "","Seattle", "WA", "98109");
        Customer customer = new Customer("Xingfu", "Du", address, "(206) 467-5480", "Xingfu@gmail.com");
        String userNotes = "user notes here";
	    new DetailsWindow(customer, vehicles, userNotes).buildGUI();
    }

    public void buildGUI () {
	    theFrame = new JFrame("DetailsWindow");
	    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainPanel = new JTabbedPane();
	    layoutComponents();
	    theFrame.getContentPane().add(mainPanel);
	    theFrame.setPreferredSize(new Dimension(800, 600));
        theFrame.pack();
	    theFrame.setLocationRelativeTo(null);
        theFrame.setVisible(true);
    }

    private void layoutComponents() {
	    int i = 0;


	    /**
         * customer info panel
	    */
        JPanel customerInfoPanel = new JPanel();
        fillCustomerPanel(customerInfoPanel);
        mainPanel.addTab(tabNames[i++], null, customerInfoPanel, "first");



	    /**
         * vehicle info panel
	    */
    	JPanel vehicleInfoPanel = new JPanel();
        vehicleInfoPanel.setLayout(new BorderLayout());

        JToolBar vehicleToolBar = new JToolBar();
        JPanel vehicleInfoSubPanel = new JPanel();
        
        UpdateVehiclePanel(vehicleInfoSubPanel);
	    vehicleInfoAddButtons(vehicleToolBar, vehicleInfoSubPanel);
	
	    vehicleInfoPanel.add(vehicleToolBar,BorderLayout.PAGE_START);
        vehicleInfoPanel.add(vehicleInfoSubPanel,BorderLayout.CENTER);

	    mainPanel.addTab(tabNames[i++], null, vehicleInfoPanel, "second");




    	/**
         * user notes panel
    	*/
        JPanel userNotesPanel = new JPanel();
        fillUserNotesPanel(userNotesPanel);
        mainPanel.addTab(tabNames[i++], null, userNotesPanel, "third");


    }

    private void fillCustomerPanel(JPanel customerInfoPanel) {
        GridLayout customerGrid = new GridLayout(1,2);
        customerGrid.setHgap(10);
        customerInfoPanel.setLayout(customerGrid);
        customerInfoPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        JLabel userImageLabel = new JLabel();
        ImageIcon userImage = createImageIcon("./user_default_image.jpg");
        userImageLabel.setIcon(userImage);
        userImageLabel.setBounds(new Rectangle(10, 10));
        userImageLabel.setHorizontalAlignment(JLabel.CENTER);
        customerInfoPanel.add(userImageLabel);

        GridLayout grid = new GridLayout(0,2);
        grid.setHgap(1);
        grid.setVgap(1);
        /**
         * fill first name
         */
        JPanel customerInfoSubPanel = new JPanel(grid);
        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name: ");
        customerInfoSubPanel.add(firstNameLabel);
        JLabel firstName = new JLabel();
        firstName.setText(customer.firstName);
        customerInfoSubPanel.add(firstName);
        /**
         * fill last name
         */
        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name: ");
        customerInfoSubPanel.add(lastNameLabel);
        JLabel lastName = new JLabel();
        lastName.setText(customer.lastName);
        customerInfoSubPanel.add(lastName);
        /**
         * fill address
         */
        JLabel addressLabel = new JLabel();
        addressLabel.setText("Address: ");
        customerInfoSubPanel.add(addressLabel);
        JLabel address = new JLabel();
        address.setText("<html>" + customer.customerAddress.getAddressInfo() + "<br><br>"
            + customer.customerAddress.getCity() + ","
                + customer.customerAddress.getState() + ",<br><br>"
                + customer.customerAddress.getZipCode() );
        customerInfoSubPanel.add(address);
        /**
         * fill phone number
         */
        JLabel phoneLabel = new JLabel();
        phoneLabel.setText("Phone Number: ");
        customerInfoSubPanel.add(phoneLabel);
        JLabel phone = new JLabel();
        phone.setText(customer.phoneNumber);
        customerInfoSubPanel.add(phone);
        /**
         * fill email
         */
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Phone Number: ");
        customerInfoSubPanel.add(emailLabel);
        JLabel email= new JLabel();
        email.setText(customer.email);
        customerInfoSubPanel.add(email);

        customerInfoPanel.add(customerInfoSubPanel);

    }

    private void UpdateVehiclePanel(JPanel vehicleInfoSubPanel) {
        GridLayout vehicleGrid = new GridLayout(1,2);
        vehicleGrid.setHgap(10);
        vehicleInfoSubPanel.setLayout(vehicleGrid);
        vehicleInfoSubPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));           
        JLabel vehicleImageLabel = new JLabel();
        vehicleImageLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(
                "./vehicle_image_" + (vehicleIndex + 1) + ".jpg")).getImage().getScaledInstance(360, 280, Image.SCALE_SMOOTH)));
        vehicleImageLabel.setBounds(new Rectangle(10, 10));
        vehicleImageLabel.setHorizontalAlignment(JLabel.CENTER);
        vehicleInfoSubPanel.add(vehicleImageLabel);

        GridLayout grid = new GridLayout(0,2);
        grid.setHgap(1);
        grid.setVgap(1);
        /**
         * fill brand
         */
        JPanel vehicleDataPanel = new JPanel(grid);
        JLabel brandLabel = new JLabel();
        brandLabel.setText("Brand: ");
        vehicleDataPanel.add(brandLabel);
        JLabel brand = new JLabel();
        brand.setText(vehicles[vehicleIndex].getBrand());
        System.out.println(vehicleIndex);
        vehicleDataPanel.add(brand);
        /**
         * fill body type
         */
        JLabel bodyTypeLabel = new JLabel();
        bodyTypeLabel.setText("Body Type: ");
        vehicleDataPanel.add(bodyTypeLabel);
        JLabel bodyType = new JLabel();
        bodyType.setText(vehicles[vehicleIndex].getBodyType().toString());
        vehicleDataPanel.add(bodyType);
        /**
         * fill year
         */
        JLabel yearLabel = new JLabel();
        yearLabel.setText("Year: ");
        vehicleDataPanel.add(yearLabel);
        JLabel year = new JLabel();
        year.setText(vehicles[vehicleIndex].getYear());
        vehicleDataPanel.add(year);
        /**
         * fill status
         */
        JLabel statusLabel = new JLabel();
        statusLabel.setText("Status: ");
        vehicleDataPanel.add(statusLabel);
        JLabel status = new JLabel();
        status.setText(vehicles[vehicleIndex].getStatus()? "New" : "Used");
        vehicleDataPanel.add(status);
        /**
         * fill price
         */
        JLabel priceLabel = new JLabel();
        priceLabel.setText("Price: ");
        vehicleDataPanel.add(priceLabel);
        JLabel price = new JLabel();
        price.setText(vehicles[vehicleIndex].getPrice());
        vehicleDataPanel.add(price);
        /**
         * fill miles
         */
        JLabel milesLabel = new JLabel();
        milesLabel.setText("Miles: ");
        vehicleDataPanel.add(milesLabel);
        JLabel miles = new JLabel();
        miles.setText(vehicles[vehicleIndex].getMiles());
        vehicleDataPanel.add(miles);


        vehicleInfoSubPanel.add(vehicleDataPanel);

    }

    public void fillUserNotesPanel(JPanel userNotesPanel) {
        userNotesPanel.setLayout(new BorderLayout());

        GridLayout userNotesgrid = new GridLayout(2,1);
        userNotesgrid.setVgap(10);
        userNotesgrid.setHgap(1);
        JPanel userNotesSubPanel = new JPanel(userNotesgrid);
        /**
         * user notes
         */
        userNotesTextArea = new JTextArea(12,40);
        userNotesTextArea.setEditable(false);
        userNotesTextArea.setLineWrap(true);
        userNotesTextArea.setText(userNotes);

        JScrollPane userNotesTextScroller = new JScrollPane(userNotesTextArea);
        userNotesTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        userNotesTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        /**
         * reply notes
         */
        userNotesReplyTextArea = new JTextArea();
        userNotesReplyTextArea.setLineWrap(true);
        if (!customer.replyNotes.isEmpty()) {
            userNotesReplyTextArea.setText(customer.replyNotes);
        }

        JScrollPane userNotesReplyTextScroller = new JScrollPane(userNotesReplyTextArea);
        userNotesReplyTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        userNotesReplyTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        userNotesSubPanel.add(userNotesTextScroller);
        userNotesSubPanel.add(userNotesReplyTextScroller);

        userNotesPanel.add(BorderLayout.NORTH, userNotesSubPanel);
        userNotesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        replyButton = new JButton("Reply");
        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNotesReplyTextArea.getText().trim().isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "The message is empty!",
                                "Warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(theFrame,
                            "Do you want to continue sending the message",
                                                            "Reply window",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                        customer.replyNotes = userNotesReplyTextArea.getText();
                        replyButton.setText("message sent");
                        sendMessage(customer, userNotesReplyTextArea.getText());
                        replyButton.setEnabled(false);
                        userNotesReplyTextArea.setText(null);
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                replyButton.setText("reply");
                                replyButton.setEnabled(true);
                            }
                        });
                        timer.start();
                    }
                }
            }
        });
        /**
         * save reply notes manually
         */
        JLabel saveLabel = new JLabel();
        saveLabel.setText("");
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if (userNotesReplyTextArea.getText().trim().isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "The message is empty!",
                                "Warning",JOptionPane.WARNING_MESSAGE);
                    } else {
                        customer.replyNotes = userNotesReplyTextArea.getText();
                        saveLabel.setText("saved");
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                saveLabel.setText("");
                            }
                        });
                        timer.start();
                    }
                }

        });
        /**
         * save reply notes after losing focus
         */
        userNotesReplyTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                customer.replyNotes = userNotesReplyTextArea.getText();
                saveLabel.setText("saved");
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveLabel.setText("");
                    }
                });
                timer.start();
            }
        });

        /**
         * save reply notes every 60 seconds
         */
        java.util.Timer t = new java.util.Timer();
        t.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                customer.replyNotes = userNotesReplyTextArea.getText();
                saveLabel.setText("saved");
                /*Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveLabel.setText("");
                    }
                });
                timer.start();*/
                try {
                    Thread.sleep(1000);
                    saveLabel.setText("");
                } catch(Exception exp) {

                }
            }
        }, 0, 1000 * 60);


        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(saveLabel);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(saveButton);
        buttonPane.add(replyButton);



        userNotesPanel.add(BorderLayout.PAGE_END, buttonPane);
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DetailsWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Can not find image: " + path);
            return null;
        }
    }

    private void vehicleInfoAddButtons (JToolBar toolBar, JPanel vehicleInfoSubPanel) {
	    /**
	       previous button
	    */
	    vehicleInfoPreviousButton = makeNavigationButton("previous", "go to previous vehicle");
	    vehicleInfoPreviousButton.addActionListener(new ActionListener() {
                @Override
		        public void actionPerformed(ActionEvent e) {
                    if (vehicleIndex> 0) {
		            	vehicleInfoNextButton.setEnabled(true);
                        vehicleIndex--;
                        vehicleInfoSubPanel.removeAll();
                        vehicleInfoSubPanel.revalidate();
                        vehicleInfoSubPanel.repaint();
                        UpdateVehiclePanel(vehicleInfoSubPanel);
                        if (vehicleIndex== 0) {
                            vehicleInfoPreviousButton.setEnabled(false);
                        }
		            }
                }
	    });

	    /**
	       next button
	    */
	    vehicleInfoNextButton = makeNavigationButton("next", "go to next vehicle");
        vehicleInfoNextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (vehicleIndex< vehicles.length - 1) {
                        vehicleInfoPreviousButton.setEnabled(true);
                        vehicleIndex++;
                        vehicleInfoSubPanel.removeAll();
                        UpdateVehiclePanel(vehicleInfoSubPanel);
                        vehicleInfoSubPanel.revalidate();
                        vehicleInfoSubPanel.repaint();

                        if (vehicleIndex== vehicles.length - 1) {
                            vehicleInfoNextButton.setEnabled(false);
                        }
                    }
		        }
	    });

        if (vehicleIndex== 0) {
            vehicleInfoPreviousButton.setEnabled(false);
        }
        if (vehicleIndex== vehicles.length - 1) {
            vehicleInfoNextButton.setEnabled(false);
        }

        toolBar.add(vehicleInfoPreviousButton);
        toolBar.add(vehicleInfoNextButton);
    }



    private JButton makeNavigationButton (String actionCommand, String toolTipText) {
	    JButton button = new JButton();
	    button.setActionCommand(actionCommand);
	    button.setToolTipText(toolTipText);
	    button.setText(actionCommand);
	    return button;
    }

    private void sendMessage (Customer cust, String message) {
	
    }

}



class Customer {
    public String firstName;
    public String lastName;
    public Address customerAddress;
    public String phoneNumber;
    public String email;
    public String replyNotes;

    Customer (String firstName, String lastName, Address customerAddress, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.replyNotes = "";
    }
    public String toString () {
	    return "";
    }
}


