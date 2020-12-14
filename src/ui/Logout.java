package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Logout extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;



//    public Logout() {
//
//    }

//    public Logout(String userSes) {

public Logout() {
	        Locale.setDefault(new Locale("en","US"));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(450, 190, 1014, 597);
            setResizable(false);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            JButton btnNewButton = new JButton("Logout");
            btnNewButton.setForeground(new Color(0, 0, 0));
            btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
            btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 39));
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure to log out?");
                     JOptionPane.setRootFrame(null);
                    if (a == JOptionPane.YES_OPTION) {
                        dispose();
                        // redirect to login
                        
                    }

                }
            });
            btnNewButton.setBounds(247, 118, 491, 114);
            contentPane.add(btnNewButton);
            setVisible(true);
        }

    // Launch the application.
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