package dao;

import ui.CustomerLogin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UserHome extends JFrame implements ActionListener {
    private Customer customer;
    private JButton subscription;
    private JButton refresh;
    private JButton logOut;
    private Container container;
    private JPanel notificationPanel;
    private NotificationUI notification;
    private JPanel buttonPanel;
    private JPanel helloPanel;
    private BorderLayout layout;


    public UserHome(Customer customer) throws Exception {
        super("Homepage");
        this.customer = customer;
        layout = new BorderLayout();
        container = getContentPane();
        container.setLayout(layout);

        JLabel west = new JLabel("     ");
        container.add("West", west);
        JLabel east = new JLabel("     ");
        container.add("East", east);

        //hello
        helloPanel = new JPanel();
        helloPanel.setLayout(new BorderLayout());
        JLabel hello = new JLabel("  Hello, \n");
        hello.setFont(new java.awt.Font("ZapfDingbats", Font.BOLD, 40));
        helloPanel.setSize(700, 100);
        helloPanel.add("South", hello);
        JLabel northH = new JLabel("      ");
        helloPanel.add("North", northH);
        JLabel southH = new JLabel("      ");
        //helloPanel.add("South", southH);
        container.add("North", hello);

        //notification
        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BorderLayout());
        notification= new NotificationUI(customer);
        notification.setSize(500, 400);
        notificationPanel.setSize(600, 400);
        notificationPanel.add("Center", notification);
        JLabel north = new JLabel("    ");
        notificationPanel.add("North", north);
        JLabel south = new JLabel("    ");
        notificationPanel.add("South", south);

        container.add("Center", notificationPanel);

        //button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setSize(500, 50);
            //subscription
            subscription = new JButton("Subscription");
            subscription.addActionListener(this);
            buttonPanel.add("West", subscription);
            //refresh
            refresh = new JButton("Refresh");
            refresh.addActionListener(this);
            buttonPanel.add("Center", refresh);
            //logout
            logOut = new JButton("Log Out");
            logOut.addActionListener(this);
            buttonPanel.add("East", logOut);

            JLabel northButton = new JLabel("          ");
            buttonPanel.add("North", northButton);
            JLabel southButton = new JLabel("          ");
            buttonPanel.add("South", southButton);

        container.add("South", buttonPanel);


        setBounds(300, 300, 700, 550);
        setVisible(true);
        //display.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public static void main(String[] args) throws Exception {
//        Customer customer = new Customer("6762785062304159908", null, null);
//        UserHome display = new UserHome(customer);
//        display.setBounds(300, 300, 700, 550);
//        display.setVisible(true);
//        //display.pack();
//        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

    /*

    //Build the content of home page panel
    private void contentPane(Customer customer) {

        //hello
        JLabel hello = new JLabel("Hello, there");
        hello.setFont(new java.awt.Font("ZapfDingbats", Font.BOLD+Font.ITALIC, 28));
        hello.setSize(700, 50);
        container.add("North", hello);
        //notification
        JComponent notificationPanel= new NotificationUI(customer);
        notificationPanel.setSize(500, 350);
        container.add("Center", notificationPanel);
        //subscription
        subscription = new JButton("Subscription");
        subscription.addActionListener(this);
        container.add("South", subscription);
        //logout
        JButton logOut = new JButton("Log Out");
        logOut.setBounds(320, 670, 40,10);
        logOut.addActionListener(this);
        container.add("South", logOut);

        /*
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setFont(new java.awt.Font("ZapfDingbats", Font.PLAIN, 18));

        //Notification panel
        JComponent notificationPanel= new NotificationUI(customer);
        tabbedPane.addTab("Notification",null, notificationPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        //Subscription panel
        Subscription2 subscriptionPanel= new Subscription2(customer);
        tabbedPane.addTab("Subscription",null,subscriptionPanel);
       tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);

        add(tabbedPane);*/



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == subscription) {
            new Subscription2(customer);
        }
        if (e.getSource() == refresh) {
            dispose();
            UserHome display = null;
            try {
                display = new UserHome(customer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            display.setBounds(300, 300, 700, 550);
            display.setVisible(true);
            //display.pack();
//            display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        if (e.getSource() == logOut) {
            int a = JOptionPane.showConfirmDialog(logOut, "Are you sure to log out?");
            JOptionPane.setRootFrame(null);
            if (a == JOptionPane.YES_OPTION) {
                dispose();
                // redirect to login
                CustomerLogin login= new CustomerLogin();
                login.setVisible(true);

            }
        }
    }

//    private JButton subscription() {
//        JButton subscription = new JButton("Subscription");
//        subscription.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == subscription) {
//                    new Subscription2(customer);
//                }
//            }
//        });
//        return subscription;
//    }

//    private JButton logOut(){
//        JButton logOut = new JButton("Log Out");
//        /*
//        logOut.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                int a = JOptionPane.showConfirmDialog(logOut, "Are you sure to log out?");
//                JOptionPane.setRootFrame(null);
//                if (a == JOptionPane.YES_OPTION) {
//                    dispose();
//                    // redirect to login
//                    CustomerLogin login= new CustomerLogin();
//                    login.setVisible(true);
//                }
//            }
//        });*/
//        return logOut;
//    }


}
