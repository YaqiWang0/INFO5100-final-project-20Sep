package ui;

import javax.swing.*;
import java.awt.*;

public abstract class IncentiveApiUIAbstract {

    protected JFrame frame;

    public IncentiveApiUIAbstract() {
        System.out.println("App constructor starting...");
        initGUI();
        showUI();
    }

    private void initGUI() {
        frame = new JFrame();
        frame.setSize(800, 1000);
        frame.setTitle("SaleVehicle_showSpecialPrice");

        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // put the frame on the center of the screen.
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(getCenterPanel(), BorderLayout.CENTER);
    }

    private void showUI() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    protected abstract JPanel getCenterPanel();

}
