package ui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.*;


public class MonthPayCalc extends JFrame {

    private JLabel vehiclePriceLabel,extraFeesLabel, downPaymentLabel, taxRateLabel, aprLabel, loanTermLabel, monthlyPaymentLabel, monthlyPaymentOutput, errorMessageLabel, endingLine, totalLoan, totalOutput;
    private JTextField vehiclePriceInput,extraFeesInput, downPaymentInput, taxRateInput, aprInput;
    private JComboBox loanTermInput;
    private JButton calculateButton, clearButton;

    public MonthPayCalc() {
        create();
        addComponents();
        addActions();
        display();
    }

    private void create() {
        vehiclePriceLabel = new JLabel ("Vehicle Price: ");
        extraFeesLabel = new JLabel ("Extra Fees (Warranty, etc.): ");
        downPaymentLabel = new JLabel ("Down Payment: ");
        taxRateLabel = new JLabel ("Tax Rate (%): ");
        aprLabel = new JLabel ("APR (%): ");
        loanTermLabel = new JLabel ("Loan Term: ");
        monthlyPaymentLabel = new JLabel ("Your Monthly Payment is: ");
        monthlyPaymentOutput = new JLabel ("");
        errorMessageLabel = new JLabel ("");
        endingLine = new JLabel("**This is Estimated Monthly Payment**");
        totalLoan = new JLabel("Total Loan is: ");
        totalOutput = new JLabel("");

        vehiclePriceInput = new JTextField(10);
        extraFeesInput = new JTextField(10);
        downPaymentInput = new JTextField(10);
        taxRateInput = new JTextField(10);
        aprInput = new JTextField(10);
        String [] loanTerms= {"Select a Term", "12 Months", "24 Months", "36 Months", "48 Months", "60 Months", "72 Months", "84 Months", "96 Months"};
        loanTermInput = new JComboBox(loanTerms);

        calculateButton = new JButton ("Calculate Monthly Payment!");
        clearButton = new JButton ("Clear");
    }

    private void addComponents() {
        GridLayout g = new GridLayout (12, 1);
        this.setLayout(g);

        JPanel temp = new JPanel();
        temp.add(vehiclePriceLabel);
        temp.add(vehiclePriceInput);
        this.add(temp);

        temp = new JPanel();
        temp.add(extraFeesLabel);
        temp.add(extraFeesInput);
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
        temp.add(calculateButton);
        temp.add(clearButton);
        this.add(temp);

        temp = new JPanel();
        temp.add(totalLoan);
        temp.add(totalOutput);
        this.add(temp);


        temp = new JPanel();
        temp.add(monthlyPaymentLabel);
        temp.add(monthlyPaymentOutput);
        this.add(temp);

        temp = new JPanel();
        temp.add(errorMessageLabel);
        this.add(temp);

        temp = new JPanel();
        temp.add(endingLine);
        this.add(temp);
    }

    private void addActions() {
        calculateButton.addActionListener((ActionEvent ae) -> calculateMonthlyPayment(ae));
        clearButton.addActionListener((ActionEvent ae) -> clearCalculator(ae));
    }

    private void calculateMonthlyPayment (ActionEvent ae) {
        String vehiclePriceString = vehiclePriceInput.getText();
        String extraFeesString = extraFeesInput.getText();
        String downPaymentString = downPaymentInput.getText();
        String taxRateString = taxRateInput.getText();
        String aprString = aprInput.getText();
        String loanTermInputString = loanTermInput.getSelectedItem().toString();

        String errorMsg = "";
        double vehiclePriceDouble = 0;
        double extraFeesDouble = 0;
        double downPaymentDouble = 0;
        double taxRateDouble = 0;
        double aprDouble = 0;
        double loanTermInputDouble = 0;
        double numerator = 0;
        double denominator = 0;
        double monthlyPaymentValue = 0;

        try{
            vehiclePriceDouble = Double.parseDouble(vehiclePriceString);
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        try{
            extraFeesDouble = Double.parseDouble(extraFeesString);
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        try{
            downPaymentDouble = Double.parseDouble(downPaymentString);
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        try{
            taxRateDouble = Double.parseDouble(taxRateString)/100;
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        try{
            aprDouble = Double.parseDouble(aprString)/100;
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        try{
            loanTermInputDouble = Double.parseDouble(loanTermInputString.replaceAll("[^0-9]", ""));
        }catch (NumberFormatException e) {
            errorMsg = "please enter or select a numerical value for each category ";
        }

        numerator = (aprDouble/12)*(((vehiclePriceDouble+extraFeesDouble)*(1+taxRateDouble))-downPaymentDouble);
        denominator = (1-Math.pow(1+(aprDouble/12), -1*loanTermInputDouble));
        monthlyPaymentValue = numerator/denominator;

        if(errorMsg.length()>0) {
            errorMessageLabel.setText(errorMsg);
            monthlyPaymentOutput.setText("ERROR");
        }else{
            errorMessageLabel.setText("");
            DecimalFormat df = new DecimalFormat ("#.##");
            monthlyPaymentOutput.setText("$" + df.format(monthlyPaymentValue));
            totalOutput.setText("$" + df.format(monthlyPaymentValue * loanTermInputDouble));

        }
    }

    private void clearCalculator(ActionEvent ae) {
        vehiclePriceInput.setText("");
        extraFeesInput.setText("");
        downPaymentInput.setText("");
        taxRateInput.setText("");
        aprInput.setText("");
        loanTermInput.setSelectedIndex(0);
        monthlyPaymentOutput.setText("");
        totalOutput.setText("");
    }

    private void display() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public static void main (String args[]) {
        new MonthPayCalc();
    }


}

/*
1. update the calculator that put out the monthly payment automatically instead of clicking 'Calculate'
2. update extra fee => make drop down option to add more text fields.
*/


