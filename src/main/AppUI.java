package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AppUI extends App {

    private JPanel centerPanel;
    private boolean isShow = true;

    @Override
    protected JPanel getCenterPanel() {
        // pop-up button
        JButton popBtn = new JButton("special_info");
        popBtn.setSize(20, 5);
        popBtn.addActionListener((ActionEvent e) -> {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Field 1:", JLabel.CENTER));
            panel.add(new JLabel("xxxxxxxxx"));
            panel.add(new JLabel("Field 2:", JLabel.CENTER));
            panel.add(new JLabel("xxxxxxxxx"));
            JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        });

        // add Panel's attribute
        centerPanel = new JPanel(new GridLayout(0, 2));
        // add Component to centerPanel
        centerPanel.add(new JLabel("Field 1:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("Field 2:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("Field 3:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("", JLabel.CENTER));
        centerPanel.add(popBtn); // for button

        return centerPanel;
    }
}
