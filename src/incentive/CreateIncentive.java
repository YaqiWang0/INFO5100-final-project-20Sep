package incentive;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JRadioButton $RadioButton;
    private JTextField textField1;
    private JRadioButton radioButton1;
    private JTextField textField2;

    public CreateIncentive() {
        initComponents();
    }

    private void initComponents(){
        publish();
        addItemsToComboBoxes();
    }

    public void addItemsToComboBoxes(){
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

    public void publish(){
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Incentive Created!");
            }
        });
    }

    public static void main(String[] args) {
        CreateIncentive frame = new CreateIncentive();
        frame.setTitle("Create Incentive");
        frame.setContentPane(frame.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
