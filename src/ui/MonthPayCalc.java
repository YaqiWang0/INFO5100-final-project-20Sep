package ui;// Team 10 - Use Case 12 - Calculate the monthly car loan payment

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.awt.*;
import java.text.*;


public class MonthPayCalc extends JFrame {

    private JPanel calculatorTitlePanel;
    private JLabel calculatorTitle;

    private JPanel resultMessagePanel;
    private JLabel resultMessage;
    private String incompleteMessage = ("Please enter or select a numerical value for each category to view your estimated monthly payment.");
    private String completeMessage = ("Based on the values entered below, your estimated monthly payment is $");

    private JPanel vehiclePricePanel;
    private JLabel vehiclePriceLabel;
    private JTextField vehiclePriceInput;

    private JPanel warrantyPanel;
    private JLabel warrantyLabel;
    private JCheckBox warrantySelection;
    private JTextField warrantyInput;

    private JPanel accessoriesPanel;
    private JLabel accessoriesLabel;
    private JCheckBox accessoriesSelection;
    private JTextField accessoriesInput;

    private JPanel otherFeesPanel;
    private JLabel otherFeesLabel;
    private JCheckBox otherFeesSelection;
    private JTextField otherFeesInput;

    private JPanel downPaymentPanel;
    private JLabel downPaymentLabel;
    private JTextField downPaymentInput;

    private JPanel taxRatePanel;
    private JLabel taxRateLabel;
    private JTextField taxRateInput;

    private JPanel aprPanel;
    private JLabel aprLabel;
    private JTextField aprInput;

    private JPanel loanTermPanel;
    private JLabel loanTermLabel;
    private String [] loanTermOptions= {"Select a Term", "12 Months", "24 Months", "36 Months", "48 Months", "60 Months", "72 Months", "84 Months", "96 Months"};
    private JComboBox loanTermInput;

    private JPanel clearPanel;
    private JButton clearButton;


    public MonthPayCalc() {

        create();
        addComponents();
        addActions();
        display();

    }


    private void create() {
        this.setTitle("Monthly Payment Calculator");
        calculatorTitlePanel = new JPanel ();
        calculatorTitle = new JLabel ("Monthly Payment Calculator");
        calculatorTitle.setFont(new Font("Tahoma", Font.BOLD, 18));

        resultMessagePanel = new JPanel();
        resultMessage = new JLabel (incompleteMessage);

        vehiclePricePanel = new JPanel();
        vehiclePriceLabel = new JLabel ("Vehicle Price ");
        vehiclePriceInput = new JTextField(10);

        warrantyPanel = new JPanel();
        warrantyLabel = new JLabel ("Warranty ");
        warrantySelection = new JCheckBox();
        warrantyInput = new JTextField(10);

        accessoriesPanel = new JPanel();
        accessoriesLabel = new JLabel ("Accessories ");
        accessoriesSelection = new JCheckBox();
        accessoriesInput = new JTextField(10);

        otherFeesPanel = new JPanel();
        otherFeesLabel = new JLabel ("Other Fees ");
        otherFeesSelection = new JCheckBox();
        otherFeesInput = new JTextField(10);

        downPaymentPanel = new JPanel();
        downPaymentLabel = new JLabel ("Down Payment ");
        downPaymentInput = new JTextField(10);

        taxRatePanel = new JPanel();
        taxRateLabel = new JLabel ("Tax Rate (%) ");
        taxRateInput = new JTextField(10);

        aprPanel = new JPanel();
        aprLabel = new JLabel ("APR (%) ");
        aprInput = new JTextField(10);

        loanTermPanel = new JPanel();
        loanTermLabel = new JLabel ("Loan Term ");
        loanTermInput = new JComboBox(loanTermOptions);

        clearPanel = new JPanel();
        clearButton = new JButton ("Clear");

    }


    private void addComponents() {

        GridLayout g = new GridLayout (11, 1);

        this.setLayout(g);

        calculatorTitlePanel.add(calculatorTitle);
        this.add(calculatorTitlePanel);

        resultMessagePanel.add(resultMessage);
        this.add(resultMessagePanel);

        vehiclePricePanel.add(vehiclePriceLabel);
        vehiclePricePanel.add(vehiclePriceInput);
        this.add(vehiclePricePanel);

        warrantyPanel.add(warrantyLabel);
        warrantyPanel.add(warrantySelection);
        this.add(warrantyPanel);

        accessoriesPanel.add(accessoriesLabel);
        accessoriesPanel.add(accessoriesSelection);
        this.add(accessoriesPanel);

        otherFeesPanel.add(otherFeesLabel);
        otherFeesPanel.add(otherFeesSelection);
        this.add(otherFeesPanel);

        downPaymentPanel.add(downPaymentLabel);
        downPaymentPanel.add(downPaymentInput);
        this.add(downPaymentPanel);

        taxRatePanel.add(taxRateLabel);
        taxRatePanel.add(taxRateInput);
        this.add(taxRatePanel);

        aprPanel.add(aprLabel);
        aprPanel.add(aprInput);
        this.add(aprPanel);

        loanTermPanel.add(loanTermLabel);
        loanTermPanel.add(loanTermInput);
        this.add(loanTermPanel);

        clearPanel.add(clearButton);
        this.add(clearPanel);

    }


    private void addActions() {

        vehiclePriceInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        warrantySelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (warrantySelection.isSelected()) {
                    warrantyPanel.add(warrantyInput);
                    warrantyPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
                else {
                    warrantyInput.setText("");
                    warrantyPanel.remove(warrantyInput);
                    warrantyPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
            }
        });

        warrantyInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        accessoriesSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (accessoriesSelection.isSelected()) {
                    accessoriesPanel.add(accessoriesInput);
                    accessoriesPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
                else {
                    accessoriesInput.setText("");
                    accessoriesPanel.remove(accessoriesInput);
                    accessoriesPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
            }
        });

        accessoriesInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        otherFeesSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (otherFeesSelection.isSelected()) {
                    otherFeesPanel.add(otherFeesInput);
                    otherFeesPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
                else {
                    otherFeesInput.setText("");
                    otherFeesPanel.remove(otherFeesInput);
                    otherFeesPanel.updateUI();
                    calculateMonthlyPayment(ae);
                }
            }
        });

        otherFeesInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        downPaymentInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        taxRateInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });

        aprInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                calculateMonthlyPayment(de);
            }
        });


        loanTermInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                calculateMonthlyPayment(ae);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                vehiclePriceInput.setText("");

                warrantySelection.setSelected(false);
                warrantyInput.setText("");
                warrantyPanel.remove(warrantyInput);
                warrantyPanel.updateUI();

                accessoriesSelection.setSelected(false);
                accessoriesInput.setText("");
                accessoriesPanel.remove(accessoriesInput);
                accessoriesPanel.updateUI();

                otherFeesSelection.setSelected(false);
                otherFeesInput.setText("");
                otherFeesPanel.remove(otherFeesInput);
                otherFeesPanel.updateUI();

                downPaymentInput.setText("");

                taxRateInput.setText("");

                aprInput.setText("");

                loanTermInput.setSelectedIndex(0);

                calculateMonthlyPayment(ae);

            }
        });

    }


    private void calculateMonthlyPayment (DocumentEvent de) {

        String vehiclePriceString = vehiclePriceInput.getText();
        String warrantyString =  warrantyInput.getText();
        String accessoriesString = accessoriesInput.getText();
        String otherFeesString = otherFeesInput.getText();
        String downPaymentString = downPaymentInput.getText();
        String taxRateString = taxRateInput.getText();
        String aprString = aprInput.getText();
        String loanTermInputString = loanTermInput.getSelectedItem().toString();


        double vehiclePriceDouble = 0;
        double warrantyDouble = 0;
        double accessoriesDouble = 0;
        double otherFeesDouble = 0;
        double downPaymentDouble = 0;
        double taxRateDouble = 0;
        double aprDouble = 0;
        double loanTermInputDouble = 0;

        double numerator = 0;
        double denominator = 0;
        double estimatedMonthlyPaymentValue = 0;


        try{

            if (!warrantySelection.isSelected()) {
                warrantyString = ("0");
            }
            if (!accessoriesSelection.isSelected()) {
                accessoriesString = ("0");
            }
            if (!otherFeesSelection.isSelected()) {
                otherFeesString = ("0");
            }

            vehiclePriceDouble = Double.parseDouble(vehiclePriceString);
            warrantyDouble = Double.parseDouble(warrantyString);
            accessoriesDouble = Double.parseDouble(accessoriesString);
            otherFeesDouble = Double.parseDouble(otherFeesString);
            downPaymentDouble = Double.parseDouble(downPaymentString);
            taxRateDouble = Double.parseDouble(taxRateString)/100;
            aprDouble = Double.parseDouble(aprString)/100;
            loanTermInputDouble = Double.parseDouble(loanTermInputString.replaceAll("[^0-9]", ""));

            numerator = (aprDouble/12)*(((vehiclePriceDouble+warrantyDouble+accessoriesDouble+otherFeesDouble)*(1+taxRateDouble))-downPaymentDouble);
            denominator = (1-Math.pow(1+(aprDouble/12), -1*loanTermInputDouble));
            estimatedMonthlyPaymentValue = numerator/denominator;

            DecimalFormat df = new DecimalFormat ("#.##");
            resultMessage.setText(completeMessage + df.format(estimatedMonthlyPaymentValue));

        }catch (NumberFormatException e) {
            resultMessage.setText(incompleteMessage);
        }

    }


    private void calculateMonthlyPayment (ActionEvent ae) {

        String vehiclePriceString = vehiclePriceInput.getText();
        String warrantyString =  warrantyInput.getText();
        String accessoriesString = accessoriesInput.getText();
        String otherFeesString = otherFeesInput.getText();
        String downPaymentString = downPaymentInput.getText();
        String taxRateString = taxRateInput.getText();
        String aprString = aprInput.getText();
        String loanTermInputString = loanTermInput.getSelectedItem().toString();


        double vehiclePriceDouble = 0;
        double warrantyDouble = 0;
        double accessoriesDouble = 0;
        double otherFeesDouble = 0;
        double downPaymentDouble = 0;
        double taxRateDouble = 0;
        double aprDouble = 0;
        double loanTermInputDouble = 0;

        double numerator = 0;
        double denominator = 0;
        double estimatedMonthlyPaymentValue = 0;


        try{

            if (!warrantySelection.isSelected()) {
                warrantyString = ("0");
            }
            if (!accessoriesSelection.isSelected()) {
                accessoriesString = ("0");
            }
            if (!otherFeesSelection.isSelected()) {
                otherFeesString = ("0");
            }

            vehiclePriceDouble = Double.parseDouble(vehiclePriceString);
            warrantyDouble = Double.parseDouble(warrantyString);
            accessoriesDouble = Double.parseDouble(accessoriesString);
            otherFeesDouble = Double.parseDouble(otherFeesString);
            downPaymentDouble = Double.parseDouble(downPaymentString);
            taxRateDouble = Double.parseDouble(taxRateString)/100;
            aprDouble = Double.parseDouble(aprString)/100;
            loanTermInputDouble = Double.parseDouble(loanTermInputString.replaceAll("[^0-9]", ""));

            numerator = (aprDouble/12)*(((vehiclePriceDouble+warrantyDouble+accessoriesDouble+otherFeesDouble)*(1+taxRateDouble))-downPaymentDouble);
            denominator = (1-Math.pow(1+(aprDouble/12), -1*loanTermInputDouble));
            estimatedMonthlyPaymentValue = numerator/denominator;

            DecimalFormat df = new DecimalFormat ("#.##");
            resultMessage.setText(completeMessage + df.format(estimatedMonthlyPaymentValue));

        }catch (NumberFormatException e) {
            resultMessage.setText(incompleteMessage);
        }

    }

    private void display() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);

    }

//
//    public static void main (String args[]) {
//
//        new MonthPayCalc();
//
//    }


}