package ui;

import service.InventiveTimeJob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppUI extends AppUIAbstract {

    private JPanel centerPanel;

    // sub panel group
    private IncentiveUI incentiveUI;
    private InventiveTimeJob timejob;

    public AppUI() {
        incentiveUI = new IncentiveUI();
        timejob = new InventiveTimeJob();
        timejob.addObserver(incentiveUI);
    }

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
        centerPanel.add(getPopupBtn("xxxxxx")); // TODO specialID

        return centerPanel;
    }

    private JButton getPopupBtn(String specialId) {
        JButton popBtn = new JButton("Learn About Discount!!!");

        popBtn.addActionListener((ActionEvent e) -> {
            timejob.start(specialId);
            JOptionPane.showConfirmDialog(centerPanel, incentiveUI, "Incentive details",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            timejob.stop();
        });

        return popBtn;
    }

}

