package incentive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateIncentive {
    private JButton button1;
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JPanel Value;
    private JPanel Details;
    private JPanel Description;

    public CreateIncentive() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Incentive Created!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create Incentive");
        frame.setContentPane(new CreateIncentive().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);
    }
}
