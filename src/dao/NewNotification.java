package dao;

import dao.Notification;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class NewNotification extends JFrame {
    private JPanel contentPane;
    private Notification newMessage;
//    public static void main(String[] args) {
//
//        Notification message = new Notification("1111", "Your car is sold");
//        NewNotification frame = new NewNotification(message);
//    }

    public NewNotification(Notification message) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(404, 100);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        this.newMessage = message;
        String content = message.getContent();
        String customerID = message.getCustomerID();
        String time = message.getTime();
        String showMessage = String.format("Dear Customer %s, \n%s", customerID, content);


        JLabel lblNewLabel = new JLabel(showMessage);
        contentPane.add(lblNewLabel, BorderLayout.CENTER);

//        JButton confButton = new JButton("OK");
//        confButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();// click OK to close
//            }
//        });
//        contentPane.add(confButton, BorderLayout.SOUTH);

        setVisible(true);
    }

}