package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppUI extends App {

    private JPanel centerPanel;

    @Override
    protected JPanel getCenterPanel() {
        // label listener
        JPanel labelPanel = new JPanel();
        JLabel special = new JLabel("special_info", JLabel.CENTER);
        special.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showConfirmDialog(frame
                        ,"This is the incentive's information....."
                        ,"Car Special Price"
                        , JOptionPane.CLOSED_OPTION);
            }
        });
        labelPanel.add(new JLabel("car_title1", JLabel.CENTER));
        labelPanel.add(new JLabel("car_name1", JLabel.CENTER));
        labelPanel.add(special);

        // dialog button
        JButton dialogBtn = new JButton("special_info_btn");
        dialogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frame
                        ,"This is the incentive's information....."
                        ,"TutorialsPoint.com"
                        , JOptionPane.CLOSED_OPTION);
            }
        });


        // add Panel's attribute
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.gray);

        // add Component to centerPanel
        centerPanel.add(labelPanel); // for label
        centerPanel.add(dialogBtn); // for button


        return centerPanel;
    }
}
