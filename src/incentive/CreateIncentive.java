package incentive;

import dao.Special;
import dao.Vehicle;
import dto.DataPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateIncentive extends JFrame {
    private JButton button1;
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JPanel Value;
    private JPanel Details;
    private JPanel Description;
    private JComboBox startYear;
    private JComboBox endYear;
    private JComboBox startDay;
    private JComboBox startMonth;
    private JComboBox endDay;
    private JComboBox endMonth;
    private JCheckBox cashPaymentCheckBox;
    private JCheckBox checkPaymentCheckBox;
    private JCheckBox loanCheckBox;
    private JCheckBox checkBox4;
    private JRadioButton flatValue;
    private JTextField inputValue;
    private JRadioButton percentValue;
    private JTextField inputPercent;

    Special spl;
    Vehicle veh;

    public CreateIncentive() {
        spl = new Special();
        initComponents();
    }

    private void initComponents(){
        publish();
        addItemsToComboBoxes();
    }

    public void addItemsToComboBoxes(){
        //startMonth.setPreferredSize(80px);
        //add items for start month combo box
        for(int i=1;i<=12;i++){
            startMonth.addItem(i);
            endMonth.addItem(i);
        }

        //add items for start day combo box
        for(int i = 1;i<=31;i++){
            startDay.addItem(i);
            endDay.addItem(i);
            //filter as/month
        }

        for(int i = 2020;i<2050;i++){
            startYear.addItem(i);
            endYear.addItem(i);
        }
    }

    //setting information
    public void setDates() throws ParseException {
        int sMonth = (int) startMonth.getSelectedItem();
        int sDay = (int) startDay.getSelectedItem();
        int sYear = (int) startYear.getSelectedItem();
        String startDate = sDay + "/" + sMonth + "/" + sYear;
        Date sDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);

        int eMonth = (int) endMonth.getSelectedItem();
        int eDay = (int) endDay.getSelectedItem();
        int eYear = (int) endYear.getSelectedItem();
        String endDate = eDay + "/" + eMonth + "/" + eYear;
        Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

        //System.out.println(date.toString());
        spl.setStartDate(sDate);
        spl.setEndDate(eDate);
    }

    public void setDiscountValue(){
        //TODO one of the two values have to be selected
        if(flatValue.isSelected()){
            //TODO if the value entered is not int, throw exception, give pop up
            int value = Integer.parseInt(inputValue.getText());
            spl.setDiscountValue(value);
        }
        else if(percentValue.isSelected()){
            int percent = Integer.parseInt(inputPercent.getText());
            spl.setDiscountPercent(percent);
        }
    }

    public Special publish(){
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Incentive Created!");
                //call the setters here
                try {
                    setDates();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                setDiscountValue();
            }
        });
        DataPersistence dp = new DataPersistence();
        dp.writeSpecials(spl);
        return spl;
        //add this special to database
    }

    public static void main(String[] args) throws ParseException {
        CreateIncentive frame = new CreateIncentive();
        frame.setTitle("Create Incentive");
        frame.setContentPane(frame.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
