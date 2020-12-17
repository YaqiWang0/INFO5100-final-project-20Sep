package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Logout extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JButton logoutB;

    public Logout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        logoutB = new JButton("Logout");

        logoutB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int a = JOptionPane.showConfirmDialog(logoutB, "Are you sure to log out?");
                JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    // redirect to login
                    CustomerLogin login= new CustomerLogin();
                    login.setVisible(true);

                }

            }
        });

        contentPane.add(logoutB);
        setVisible(true);
    }

    // Launch Logout for testing
//    public static void main(String[] args) {
//        Locale.setDefault(new Locale("en","US"));
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Logout frame = new Logout();
//
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}