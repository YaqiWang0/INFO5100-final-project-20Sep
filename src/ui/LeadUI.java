package ui;

import dto.DataPersistence;
import dto.Lead;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class LeadUI extends JFrame {

    private JLabel quoteTitle, firstName, lastName, emailAddress, phone, message,
            zipcode, contactPreference, purpose, time, dealerInfo, vehicleInfo;
    private JTextField firstNameText, lastNameText, emailText, phoneDigit, zipcodeDigit;
    private JTextArea msgArea;
    private JComboBox<String> usePurpose;
    private JComboBox<String> contactTime;
    private JRadioButton selectEmail, selectPhone;
    private ButtonGroup contactMethods;
    private JEditorPane privacyConsent;
    private JButton submit, cancel;

    private String vehicleId;
    private String vehicleYearMakeModel;
    private String dealerName;


    public LeadUI(String vehicleId, String dealerName, String vehicleYearMakeModel) {
        this.vehicleId = vehicleId;
        this.dealerName = dealerName;
        this.vehicleYearMakeModel = vehicleYearMakeModel;
        createUI();
        addComponents();
        addActions();
        setSize(700, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void addActions() {

        submit.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cancel.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        privacyConsent.addHyperlinkListener(hyperlinkEvent -> {
            String consent = "By clicking \"Submit\" you agree us to collect your personal information for providing better services to you." +
                    "\nInformation collected is only for business use and it won't be revealed to the third party.";
            if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                JOptionPane.showMessageDialog(null, consent,
                        "Privacy Notice", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void actionPerformed(ActionEvent ae) throws IOException {

        // lead data saved when Submit clicked.
        if (ae.getSource() == submit) {

            if (firstNameText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your first name!",
                        "Reminder", JOptionPane.WARNING_MESSAGE);
            } else if (lastNameText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your last name!",
                        "Reminder", JOptionPane.WARNING_MESSAGE);
            } else if (emailText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your email address!",
                        "Reminder", JOptionPane.WARNING_MESSAGE);
            } else if (phoneDigit.getText().isEmpty() || phoneDigit.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "Please enter 10 digit phone number!",
                        "Reminder", JOptionPane.WARNING_MESSAGE);
            } else {
                
                Lead lead = new Lead(vehicleId, dealerName);
                lead.setFirstName(firstNameText.getText());
                lead.setLastName(lastNameText.getText());
                lead.setEmailAddress(emailText.getText());
                lead.setPhoneNumber(phoneDigit.getText());
                lead.setZipCode(zipcodeDigit.getText());
                if (usePurpose.getSelectedItem() == usePurpose.getItemAt(0)) {
                    lead.setUsePurpose("use purpose not provided");
                } else {
                    lead.setUsePurpose((String) usePurpose.getSelectedItem());
                }
                if (contactTime.getSelectedItem() == contactTime.getItemAt(0)) {
                    lead.setContactTime("contact time not provided");
                } else {
                    lead.setContactTime((String) contactTime.getSelectedItem());
                }
                if (selectEmail.isSelected() || selectPhone.isSelected()) {
                    lead.setContactPreference(contactMethods.getSelection().getActionCommand());
                } else {
                    lead.setContactPreference("contact method not provided");
                }
                lead.setMessage(msgArea.getText().replaceAll("\n", " "));

                DataPersistence dp = new DataPersistence();
                dp.writeLead(lead);

                dispose();
            }
        }

        if (ae.getSource() == cancel) {
            int cancelValue = JOptionPane.showConfirmDialog(null, "Cancel your quote and leave?",
                    "Reminder", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (cancelValue == JOptionPane.YES_OPTION) {
                dispose();
            }

        }
    }


    private void createUI() {
        // label description for request info
        quoteTitle = new JLabel("REQUEST QUOTE");
        quoteTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        dealerInfo = new JLabel(dealerName);
        dealerInfo.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        vehicleInfo = new JLabel(vehicleYearMakeModel);
        vehicleInfo.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        firstName = new JLabel("First Name*");
        firstName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lastName = new JLabel("Last Name*");
        lastName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        emailAddress = new JLabel("Email Address*");
        emailAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
        phone = new JLabel("Phone Number*");
        phone.setFont(new Font("Times New Roman", Font.BOLD, 15));
        message = new JLabel("Message");
        message.setFont(new Font("Times New Roman", Font.BOLD, 15));
        zipcode = new JLabel("Zip Code");
        zipcode.setFont(new Font("Times New Roman", Font.BOLD, 15));
        purpose = new JLabel("Business/Personal Use");
        purpose.setFont(new Font("Times New Roman", Font.BOLD, 15));
        time = new JLabel("Available Time");
        time.setFont(new Font("Times New Roman", Font.BOLD, 15));
        contactPreference = new JLabel("Contact Preference");
        contactPreference.setFont(new Font("Times New Roman", Font.BOLD, 15));

        // column for input info
        firstNameText = new JTextField(20);
        firstNameText.setBackground(Color.white);
        firstNameText.setToolTipText("Enter your first name");
        lastNameText = new JTextField(20);
        lastNameText.setBackground(Color.white);
        lastNameText.setToolTipText("Enter your last name");
        emailText = new JTextField(20);
        emailText.setBackground(Color.white);
        emailText.setToolTipText("Enter your email address");
        phoneDigit = new JTextField(16);
        phoneDigit.setBackground(Color.white);
        phoneDigit.setToolTipText("Enter your phone number");
        phoneDigit.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if ((input < '0' || input > '9') && input != '\b') {
                    e.consume();
                }
            }
        });
        phoneDigit.setDocument(new JTextFieldLimit(10));

        zipcodeDigit = new JTextField(20);
        zipcodeDigit.setBackground(Color.white);
        zipcodeDigit.setToolTipText("Enter your zip code");
        zipcodeDigit.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if ((input < '0' || input > '9') && input != '\b') {
                    e.consume();
                }
            }
        });
        zipcodeDigit.setDocument(new JTextFieldLimit(5));

        msgArea = new JTextArea(5, 40);
        msgArea.setDocument(new JTextFieldLimit(150));
        msgArea.setLineWrap(true);

        String[] purposes = {"--Select Use Purpose--", "Business", "Personal"};
        usePurpose = new JComboBox<>(purposes);
        usePurpose.setSelectedIndex(0);

        String[] times = {"--Select Time--", "WorkDay 9AM-12PM", "WorkDay 12PM-3PM", "WorkDay 3PM-6PM",
                "Weekend 9AM-12PM", "Weekend 12PM-3PM", "Weekend 3PM-6PM"};
        contactTime = new JComboBox<>(times);
        contactTime.setSelectedIndex(0);

        selectEmail = new JRadioButton("Email");
        selectPhone = new JRadioButton("Phone");
        selectEmail.setActionCommand("Email");
        selectPhone.setActionCommand("Phone");
        contactMethods = new ButtonGroup();
        contactMethods.add(selectEmail);
        contactMethods.add(selectPhone);

        privacyConsent = new JEditorPane();
        privacyConsent.setEditable(false);
        privacyConsent.setContentType("text/html");
        privacyConsent.setText("<html><body><a href= >" +
                "View Privacy Notice and Consumer Rights</a ></body></html>");

        submit = new JButton(" Submit ");
        submit.setBackground(new Color(20, 122, 200));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        submit.setBorder(BorderFactory.createRaisedBevelBorder());
        submit.setOpaque(true);

        cancel = new JButton(" Cancel ");
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        cancel.setBorder(BorderFactory.createRaisedBevelBorder());
        cancel.setOpaque(true);

    }

    private void addComponents() {
        // general layout setup
        GridLayout g = new GridLayout(10, 1);
        this.setLayout(g);

        JPanel panel = new JPanel();
        panel.add(quoteTitle);
        this.add(panel);

        panel = new JPanel();
        panel.add(dealerInfo);
        this.add(panel);
        panel = new JPanel();
        panel.add(vehicleInfo);
        this.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(firstName);
        panel.add(firstNameText);
        panel.add(lastName);
        panel.add(lastNameText);
        this.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(emailAddress);
        panel.add(emailText);
        panel.add(phone);
        panel.add(phoneDigit);
        this.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(zipcode);
        panel.add(zipcodeDigit);
        panel.add(purpose);
        panel.add(usePurpose);
        this.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(message);
        panel.add(msgArea);
        this.add(panel);

        panel = new JPanel();
        panel.add(contactPreference);
        panel.add(selectEmail);
        panel.add(selectPhone);
        panel.add(time);
        panel.add(contactTime);
        this.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(privacyConsent);
        this.add(panel);

        panel = new JPanel();
        panel.add(cancel);
        panel.add(submit);
        this.add(panel);

    }

}

/*
  create JTextFieldLimit class to set TextField length limit for digit input
 */
class JTextFieldLimit extends PlainDocument {
    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if ((getLength() + str.length() <= limit)) {
            super.insertString(offset, str, attr);
        }
    }
}
