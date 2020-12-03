import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;


public class LeadFormDemo extends JFrame {

    private JLabel quote, dealerInfo, firstName, lastName, emailAddress, phone, message, zipcode,
            contactPreference, purpose, time, privacyConsent;
    private JTextField firstNameText, lastNameText, emailText, phoneDigit, zipcodeDigit;
    private JTextArea msgArea;
    private JComboBox<String> usePurpose, timeSpan;
    private JRadioButton selectEmail, selectPhone;
    private JButton submit, cancel;


    public LeadFormDemo() {
        createUI();
        addComponents();
        addActions();
        setSize(1000, 1000);
        setVisible(true);
        pack();

    }

    private void addActions() {

        usePurpose.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timeSpan.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        selectEmail.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        selectPhone.addActionListener((ActionEvent ae) -> {
            try {
                actionPerformed(ae);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
// initialize member variables to maintain these data, access via getters?
//        String fName = firstNameText.getText();
//        String lName = lastNameText.getText();
//        String emailAdd = emailText.getText();
//        String phoneNum = phoneDigit.getText();
//        String zipCode = zipcodeDigit.getText();
//        String msg = msgArea.getText();

        String purpose = "use purpose not selected";
        String selectTime = "contact time not selected";
        String contactBy = "contact method not selected";

        if (ae.getSource() == usePurpose) {
            purpose = (String) usePurpose.getSelectedItem();
        }

        if (ae.getSource() == timeSpan) {
            selectTime = (String) timeSpan.getSelectedItem();
        }

        contactBy = ae.getActionCommand();

        // assume Save data happens when click Submit.
        if (ae.getSource() == submit) {

            // ObjectOutputStream not yet fulfilled and tested
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lead.txt"));
            PrintStream ps = new PrintStream(oos);
            ps.println(firstNameText.getText());
            ps.println(lastNameText.getText());
            ps.println(msgArea.getText());
            ps.close();

// another thought: using FileWriter?
//            FileWriter fileWriter = new FileWriter("lead.txt");
//            firstNameText.write(fileWriter);
//            lastNameText.write(fileWriter);
//            emailText.write(fileWriter);
//            phoneDigit.write(fileWriter);
//            zipcodeDigit.write(fileWriter);
//            msgArea.write(fileWriter);
//            fileWriter.close();


            System.exit(0);
        }
        if (ae.getSource() == cancel) {
            System.exit(0);
        }
    }

    private void createUI() {
        // label description for request info
        quote = new JLabel("Request A Quote");
        dealerInfo = new JLabel("AUTO Dealer.getName()" + "\nAddress: " + "Dealer.getAddress()");
        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        emailAddress = new JLabel("Email:");
        phone = new JLabel("Phone:");
        message = new JLabel("Message:");
        zipcode = new JLabel("Zipcode:");
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
        ButtonGroup contactChoices = new ButtonGroup();
        contactChoices.add(selectEmail);
        contactChoices.add(selectPhone);

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

    public static void main(String[] args) {
        new LeadFormDemo();
    }

}
