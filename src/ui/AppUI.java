package ui;

import service.IncentiveApiImpl;
import ui.IncentiveUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppUI extends AppUIAbstract {

    private JPanel centerPanel;

    @Override
    protected JPanel getCenterPanel() {
        // init panel
        centerPanel = new JPanel(new GridLayout(0, 2));

        // add Component to centerPanel
        centerPanel.add(new JLabel("Field 1:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("Field 2:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("Field 3:", JLabel.CENTER));
        centerPanel.add(new JLabel("xxxxxxxxx"));
        centerPanel.add(new JLabel("", JLabel.CENTER));
        centerPanel.add(getPopupBtn());

        return centerPanel;
    }

    private JButton getPopupBtn() {
        JButton popBtn = new JButton("special_info");
        popBtn.addActionListener((ActionEvent e) -> {
            // new IncentiveUI();

            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Field 1:", JLabel.CENTER));
            panel.add(new JLabel("xxxxxxxxx"));
            panel.add(new JLabel("Field 2:", JLabel.CENTER));
            panel.add(new JLabel("xxxxxxxxx"));
            JOptionPane.showConfirmDialog(null, panel, "Test",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            // ??? actually, this should pass the Special object from showIncentive().
            new IncentiveUI(new IncentiveApiImpl().showIncentive(""));
        });
        return popBtn;
    }
}

