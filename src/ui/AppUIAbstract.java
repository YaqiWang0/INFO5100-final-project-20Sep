package ui;

import javax.swing.*;
import java.awt.*;

public abstract class AppUIAbstract {

    protected JFrame frame;

    public AppUIAbstract() {
        System.out.println("App constructor starting...");
        initGUI();
        showUI();
    }

    private void initGUI() {
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setTitle("SaleVehicle_showSpecialPrice");

        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
