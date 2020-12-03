package incentive;

import javax.swing.*;

public class Inventory extends JFrame {
    private JPanel panel1;
    private JLabel CategoryJLabel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField textField1;
    private JButton clearAllButton;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox8;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JComboBox comboBox11;
    private JButton clearCriteriaButton;
    private JPanel mainPanel;
    private JRadioButton applyThisSpecialToRadioButton;
    private JRadioButton manuallySelectVehiclesFromRadioButton;
    private JCheckBox splitOffersIndividuallyCheckBox;
    private JTable table1;

    public Inventory(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

}
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args){
        JFrame frame = new Inventory("Inventory");
        frame.setVisible(true);
    }
}
