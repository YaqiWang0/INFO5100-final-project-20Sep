package ui;

import service.IncentiveApi;
import service.IncentiveApiImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppUI extends AppUIAbstract {

    private IncentiveApi incentiveApi = new IncentiveApiImpl();
    private JPanel centerPanel;
    // ??? demo
//    private Special specialDemo = new IncentiveApiImpl().showIncentive("");
    private IncentiveUI incentiveUI = null;

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
            Object panel = new IncentiveUI(incentiveApi.getSpecial("xxxxxx"));
            JOptionPane.showConfirmDialog(centerPanel, panel, "Incentive details",
                        JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        });

        return popBtn;
    }

}

