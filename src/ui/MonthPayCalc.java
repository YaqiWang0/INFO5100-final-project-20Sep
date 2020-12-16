package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.awt.*;
import java.text.*;


public class MonthPayCalc extends JFrame {

    private JLabel calculatorTitle;
    private JLabel firstMessage;
    private JLabel paymentNumMessage;

    private String incompleteMessage = ("Please enter or select a numerical value for each category to view your estimated monthly payment.");
    private String completeMessage = ("Based on the values entered below, your estimated monthly payment is $");

    private JLabel vehiclePriceLabel,extraFeesLabel, downPaymentLabel, taxRateLabel, aprLabel, loanTermLabel;
    private JTextField vehiclePriceInput,extraFeesInput, downPaymentInput, taxRateInput, aprInput, tfield;
    private JComboBox loanTermInput;
    private JButton addExtraFields;
    int count;

    public MonthPayCalc() {
        create();
        addComponents();
        addActions();
        display();
    }

    private void create() {

        calculatorTitle = new JLabel ("Monthly Payment Calculator");

        firstMessage = new JLabel (incompleteMessage);
        paymentNumMessage = new JLabel("");

        vehiclePriceLabel = new JLabel ("1) Vehicle Price: ");
        extraFeesLabel = new JLabel ("2) Extra Fees (Warranty, etc.): ");
        downPaymentLabel = new JLabel ("3) Down Payment: ");
        taxRateLabel = new JLabel ("4) Tax Rate (%): ");
        aprLabel = new JLabel ("5) APR (%): ");
        loanTermLabel = new JLabel ("6) Loan Term: ");


        vehiclePriceInput = new JTextField(10);
        extraFeesInput = new JTextField(10);
        downPaymentInput = new JTextField(10);
        taxRateInput = new JTextField(10);
        aprInput = new JTextField(10);
        String [] loanTermOptions= {"Select a Term", "12 Months", "24 Months", "36 Months", "48 Months", "60 Months", "72 Months", "84 Months", "96 Months"};
        loanTermInput = new JComboBox(loanTermOptions);
        addExtraFields = new JButton("+");
        count = 0;


    }

    private void addComponents() {
        GridLayout g = new GridLayout (10 + count, 1);

        this.setLayout(g);

        JPanel temp = new JPanel();
        temp.add(calculatorTitle);
        this.add(temp);

        temp = new JPanel();
        temp.add(firstMessage);
        this.add(temp);

        temp = new JPanel();
        temp.add(vehiclePriceLabel);
        temp.add(vehiclePriceInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(extraFeesLabel);
        temp.add(extraFeesInput);
        //temp.add(addExtraFields);
        this.add(temp);

        temp = new JPanel();
        temp.add(downPaymentLabel);
        temp.add(downPaymentInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(taxRateLabel);
        temp.add(taxRateInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(aprLabel);
        temp.add(aprInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(loanTermLabel);
        temp.add(loanTermInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(paymentNumMessage);
        this.add(temp);

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

        extraFeesInput.getDocument().addDocumentListener(new DocumentListener() {
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

        addExtraFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfield = new JTextField();
                tfield.setName("Extra: ");

            }
        });


    }

    private void calculateMonthlyPayment (DocumentEvent de) {

        String vehiclePriceString = vehiclePriceInput.getText();
        String extraFeesString = extraFeesInput.getText();
        String downPaymentString = downPaymentInput.getText();
        String taxRateString = taxRateInput.getText();
        String aprString = aprInput.getText();
        String loanTermInputString = loanTermInput.getSelectedItem().toString();

        double vehiclePriceDouble = 0;
        double extraFeesDouble = 0;
        double downPaymentDouble = 0;
        double taxRateDouble = 0;
        double aprDouble = 0;
        double loanTermInputDouble = 0;
        double numerator = 0;
        double denominator = 0;
        double estimatedMonthlyPaymentValue = 0;


        try{
            vehiclePriceDouble = Double.parseDouble(vehiclePriceString);
            extraFeesDouble = Double.parseDouble(extraFeesString);
            downPaymentDouble = Double.parseDouble(downPaymentString);
            taxRateDouble = Double.parseDouble(taxRateString)/100;
            aprDouble = Double.parseDouble(aprString)/100;
            loanTermInputDouble = Double.parseDouble(loanTermInputString.replaceAll("[^0-9]", ""));



            numerator = (aprDouble/12)*(((vehiclePriceDouble+extraFeesDouble)*(1+taxRateDouble))-downPaymentDouble);
            denominator = (1-Math.pow(1+(aprDouble/12), -1*loanTermInputDouble));
            estimatedMonthlyPaymentValue = numerator/denominator;

            DecimalFormat df = new DecimalFormat ("#.##");
            paymentNumMessage.setText(completeMessage + df.format(estimatedMonthlyPaymentValue));

        }catch (NumberFormatException e) {
            firstMessage.setText(incompleteMessage);
        }
    }

    private void calculateMonthlyPayment (ActionEvent ae) {

        String vehiclePriceString = vehiclePriceInput.getText();
        String extraFeesString = extraFeesInput.getText();
        String downPaymentString = downPaymentInput.getText();
        String taxRateString = taxRateInput.getText();
        String aprString = aprInput.getText();
        String loanTermInputString = loanTermInput.getSelectedItem().toString();

        double vehiclePriceDouble = 0;
        double extraFeesDouble = 0;
        double downPaymentDouble = 0;
        double taxRateDouble = 0;
        double aprDouble = 0;
        double loanTermInputDouble = 0;
        double numerator = 0;
        double denominator = 0;
        double estimatedMonthlyPaymentValue = 0;

        String incompleteMessage = ("Please enter or select a numerical value for each category to view your estimated monthly payment.");
        String completeMessage = ("Based on the values entered below, your estimated monthly payment is $");

        try{
            vehiclePriceDouble = Double.parseDouble(vehiclePriceString);
            extraFeesDouble = Double.parseDouble(extraFeesString);
            downPaymentDouble = Double.parseDouble(downPaymentString);
            taxRateDouble = Double.parseDouble(taxRateString)/100;
            aprDouble = Double.parseDouble(aprString)/100;
            loanTermInputDouble = Double.parseDouble(loanTermInputString.replaceAll("[^0-9]", ""));

            numerator = (aprDouble/12)*(((vehiclePriceDouble+extraFeesDouble)*(1+taxRateDouble))-downPaymentDouble);
            denominator = (1-Math.pow(1+(aprDouble/12), -1*loanTermInputDouble));
            estimatedMonthlyPaymentValue = numerator/denominator;

            DecimalFormat df = new DecimalFormat ("#.##");
            paymentNumMessage.setText(completeMessage + df.format(estimatedMonthlyPaymentValue));

        }catch (NumberFormatException e) {
            firstMessage.setText(incompleteMessage);
        }
    }

    private void display() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    public static void main (String args[]) {
        new MonthPayCalc();
    }


}
