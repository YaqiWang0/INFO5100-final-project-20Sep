package ui;

import dao.Special;
import service.IncentiveApiImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppUI extends AppUIAbstract {

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

// ??? comment for clearer view.
//            JPanel panel = new JPanel(new GridLayout(0, 2));
//            panel.add(new JLabel("Field 1:", JLabel.CENTER));
//            panel.add(new JLabel("xxxxxxxxx"));
//            panel.add(new JLabel("Field 2:", JLabel.CENTER));
//            panel.add(new JLabel("xxxxxxxxx"));
//            JOptionPane.showConfirmDialog(null, panel, "Test",
//                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

            // use Singleton, this will create only one IncentiveUI frame.
//            incentiveUI = IncentiveUI.getInstance(new IncentiveApiImpl().showIncentive(""));
//            incentiveUI.initialize();

            incentiveUI = new IncentiveUI(new IncentiveApiImpl().showIncentive(""));
//            if (!incentiveUI.isIncentiveUIIsOpen()) {
//                incentiveUI.showUI();
//                incentiveUI.setIncentiveUIIsOpen(true);
//            }
            incentiveUI.showUI();
//            popBtn.setEnabled(false);
//            if (incentiveUI.salesIsEnded()) {
//                popBtn.setVisible(false);
//            }

//            // if the discount period is ended, the special_info should not be displayed.
//            new Thread() {
//                public void run() {
//                    while (true) {
//                        if (incentiveUI.salesIsEnded()) {
//                            popBtn.setVisible(false);
//                        }
//
//                        try {
//                            // update per second.
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//
        });
        return popBtn;
    }

}

