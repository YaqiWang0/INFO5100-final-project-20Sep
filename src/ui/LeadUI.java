import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class LeadUI extends JFrame {

    private JLabel quote, dealerInfo, firstName, lastName, emailAddress, phone, message, zipcode,
            contactPreference, purpose, time, privacyConsent;
    private JTextField firstNameText, lastNameText, emailText, phoneDigit, zipcodeDigit;
    private JTextArea msgArea;
    private JComboBox<String> usePurpose, timeSpan;
    private JRadioButton selectEmail, selectPhone;
    private ButtonGroup contactMethods;
    private JButton submit, cancel;


    public LeadUI() {
        createUI();
        addComponents();
        addActions();
        setSize(1000, 1000);
        setVisible(true);
        pack();

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

    }

    private void actionPerformed(ActionEvent ae) throws IOException {

        // lead data saved when Submit clicked.
        if (ae.getSource() == submit) {

            ArrayList<String> leadInput = new ArrayList<>();
            leadInput.add(firstNameText.getText());
            leadInput.add(lastNameText.getText());
            leadInput.add(emailText.getText());
            leadInput.add(phoneDigit.getText());
            leadInput.add(zipcodeDigit.getText());
            leadInput.add((String) usePurpose.getSelectedItem());
            leadInput.add((String) timeSpan.getSelectedItem());
            leadInput.add(contactMethods.getSelection().getActionCommand());

            String leadFile = "leadOutput.csv";
            File leadOutput = new File(leadFile);
            FileWriter fw = null;
            if (!leadOutput.exists()) {
                fw = new FileWriter(leadOutput, true);
                leadOutput.createNewFile();
                fw.append("First Name");
                fw.append(",");
                fw.append("Last Name");
                fw.append(",");
                fw.append("Email");
                fw.append(",");
                fw.append("Phone Number");
                fw.append(",");
                fw.append("Zip Code");
                fw.append(",");
                fw.append("Use Purpose");
                fw.append(",");
                fw.append("Contact Time");
                fw.append(",");
                fw.append("Contact Preference");
                fw.append("\n");
                fw.close();
            }

            try {
                fw = new FileWriter(leadOutput, true);
                fw.append(String.join(",", leadInput));
                fw.append("\n");

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                fw.close();
            }

            System.exit(0);
        }

        if (ae.getSource() == cancel) {
            System.exit(0);
        }

    }


    private void createUI() {
        // label description for request info
        quote = new JLabel("Request A Quote");
        dealerInfo = new JLabel("AUTO Dealer Name " + " Address: " + "Dealer Address");
        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        emailAddress = new JLabel("Email:");
        phone = new JLabel("Phone:");
        message = new JLabel("Message:");
        zipcode = new JLabel("Zip Code:");
        purpose = new JLabel("Use Purpose");
        time = new JLabel("Available Time");
        contactPreference = new JLabel("Contact Preference");
        privacyConsent = new JLabel("View Privacy Notice and Consumer Rights.");

        // column for input info
        firstNameText = new JTextField(20);
        lastNameText = new JTextField(20);
        emailText = new JTextField(20);
        phoneDigit = new JTextField(20);
        zipcodeDigit = new JTextField(10);
        msgArea = new JTextArea(5, 30);

        String[] purposes = {"--Select Use Purpose--", "Business", "Personal"};
        usePurpose = new JComboBox<>(purposes);
        usePurpose.setSelectedIndex(0);
        String[] times = {"--Select Time--", "9AM-12PM", "12PM-3PM", "3PM-6PM"};
        timeSpan = new JComboBox<>(times);
        timeSpan.setSelectedIndex(0);

        selectEmail = new JRadioButton("Email");
        selectPhone = new JRadioButton("Phone");
        selectEmail.setActionCommand("Email");
        selectPhone.setActionCommand("Phone");
        contactMethods = new ButtonGroup();
        contactMethods.add(selectEmail);
        contactMethods.add(selectPhone);

        submit = new JButton("Submit");
        cancel = new JButton("Cancel");

    }

    private void addComponents() {
        // general layout setup
        GridLayout g = new GridLayout(8, 1);
        this.setLayout(g);

        JPanel panel = new JPanel();
        panel.add(quote);
        this.add(panel);

        panel = new JPanel();
        panel.add(dealerInfo);
        this.add(panel);

        panel = new JPanel();
        panel.add(firstName);
        panel.add(firstNameText);
        panel.add(lastName);
        panel.add(lastNameText);
        this.add(panel);

        panel = new JPanel();
        panel.add(emailAddress);
        panel.add(emailText);
        panel.add(phone);
        panel.add(phoneDigit);
        this.add(panel);

        panel = new JPanel();
        panel.add(zipcode);
        panel.add(zipcodeDigit);
        panel.add(purpose);
        panel.add(usePurpose);
        this.add(panel);

        panel = new JPanel();
        panel.add(message);
        panel.add(msgArea);
        this.add(panel);

        panel = new JPanel();
        panel.add(contactPreference);
        panel.add(selectEmail);
        panel.add(selectPhone);
        panel.add(time);
        panel.add(timeSpan);
        this.add(panel);

        panel = new JPanel();
        panel.add(privacyConsent);
        panel.add(submit);
        panel.add(cancel);
        this.add(panel);

    }


}
