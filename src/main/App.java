package main;

import javax.swing.*;
import java.awt.*;

public abstract class App {

    protected JFrame frame;

    public App() {
        System.out.println("App constructor starting...");
        initGUI();
        showUI();
    }

    public void initGUI() {
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setTitle("SaleVehicle_showSpecialPrice");

        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.add(getCenterPanel(), BorderLayout.CENTER);
    }

    public void showUI() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    protected abstract JPanel getCenterPanel();

    public static void main(String[] args) {
        new AppUI();
        System.out.println("AppUI main starting...");
    }

}
