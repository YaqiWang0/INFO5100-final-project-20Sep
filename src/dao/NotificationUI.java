package dao;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class NotificationUI extends JPanel {
    static NotiDao dataBase = new NotiDaoImpl();
    static private Customer customer = null;
    static private String customerID = null;
    static private DefaultListModel<String> unreadList;
    static private DefaultListModel<String> readList;
    static private final JPopupMenu popupUnread = new JPopupMenu();
    static private final JPopupMenu popupRead  = new JPopupMenu();
    static private int[] indices;



//    public static void main(String[] args) {
//        customer = new Customer("1111", "Jiang", "Canlin");
//        JFrame frame=new JFrame("Notification");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new NotificationUI(customer),BorderLayout.CENTER);
//        frame.pack();
//        frame.setVisible(true);
//    }

    //Build the content of notification panel
    public NotificationUI(Customer customer) throws Exception {
        super(new BorderLayout());
        Border title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),
                "Notification",TitledBorder.LEFT, TitledBorder.TOP, new java.awt.Font("ZapfDingbats", Font.BOLD+Font.ITALIC, 18));
        setBorder(title);

        NotificationUI.customer = customer;
        customerID = customer.getID();
        fillLists();
        fillPopupmenu();

        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setFont(new java.awt.Font("ZapfDingbats", Font.BOLD, 11));

        //Panel Unread
        JComponent unreadPanel= listPanel(unreadList, popupUnread);
        tabbedPane.addTab("Unread",null, unreadPanel);
        tabbedPane.setMnemonicAt(0,KeyEvent.VK_1);
        //Panel Read
        JComponent readPanel= listPanel(readList, popupRead);
        tabbedPane.addTab("Read",null,readPanel);
        tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);

        add(tabbedPane);
    }


    //Build the ListModel of two tabbed panels' JLists
     void fillLists() throws Exception/*Customer customer throws Exception */{
        //fill unread/read list
        List<Notification> rawUnread = dataBase.findAllUnread(customer);
        List<Notification> rawRead = dataBase.findAllRead(customer);
//        List<Notification> rawUnread = Notification.testUnread();
//        List<Notification> rawRead = Notification.testRead();
        unreadList = toJListModel(rawUnread);
        readList = toJListModel(rawRead);
    }
        //change List into jListModel
        private DefaultListModel<String> toJListModel(List<Notification> rawData) {
        DefaultListModel<String>  model = new DefaultListModel<>();
        for (int i = 0; i < rawData.size(); i++) {
            String currLine = rawData.get(i).getTime() + "             " + rawData.get(i).getContent();
            model.addElement(currLine);
        }
        return model;
    }
    //Build the popup menu
    private void fillPopupmenu() {
        //unread list
        ActionListener unreadListener = new NotificationUI.NotificationActionListener(unreadList);
        //Mark as Read
        JMenuItem read = new JMenuItem("Mark as Read");
        read.addActionListener(unreadListener);
        popupUnread.add(read);
        //Delete
        JMenuItem deleteUnread = new JMenuItem("Delete");
        deleteUnread.addActionListener(unreadListener);
        popupUnread.add(deleteUnread);

        //read list
        ActionListener readListener = new NotificationUI.NotificationActionListener(readList);
        //Mark as Unread
        JMenuItem unread = new JMenuItem("Mark as Unread");
        unread.addActionListener(readListener);
        popupRead.add(unread);
        //Delete
        JMenuItem deleteRead = new JMenuItem("Delete");
        deleteRead.addActionListener(readListener);
        popupRead.add(deleteRead);
    }
    //Build the content of JList panel
    private JComponent listPanel(DefaultListModel<String> notificationsList, JPopupMenu popupMenu) {
        //JPanel
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(3, 3));
        //JList
        JList list=new JList(notificationsList);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.add(popupMenu);
        list.addMouseListener(mouseAdapter(list, popupMenu));
        list.addKeyListener(keyListener(notificationsList, list));

        //JScrollPane
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(list);
        contentPane.add(scrollPane,BorderLayout.CENTER);

        return contentPane;
    }


    //Build mouse adapter of JList panel
    private MouseAdapter mouseAdapter(JList list, JPopupMenu popupMenu) {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
            }
            @Override
            public void mousePressed(MouseEvent event) {
                showPopup(event);
            }
            @Override
            public void mouseReleased(MouseEvent event) {
                showPopup(event);
            }
            private void showPopup(MouseEvent event) {
                if (event.isPopupTrigger() && list.getSelectedIndex() != -1) {
                    popupMenu.show(event.getComponent(), event.getX(), event.getY());
                    indices = list.getSelectedIndices();
                }
            }
        };
        return mouseAdapter;
    }


    //Build key listener
    private KeyListener keyListener(DefaultListModel<String> listModel, JList list) {
        NotificationActionListener actionListener = new NotificationActionListener(listModel);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
            }
            @Override
            public void keyPressed(KeyEvent event) {
                indices = list.getSelectedIndices();
                if ((KeyEvent.getKeyText(event.getKeyCode())).equals("⌫")) {
                    try {
                        actionListener.delete(actionListener.listModel);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                /*
                int code1 = event.getKeyCode();
                if((code1 == KeyEvent.VK_CONTROL) || (code1 == KeyEvent.VK_META)){
                    int code2 = event.getKeyCode();
                    if (code2 == KeyEvent.VK_DELETE) {
                        delete(actionListener);
                    }
                } else if (code1 == KeyEvent.VK_DELETE) {
                    int code2 = event.getKeyCode();
                    if ((code2 == KeyEvent.VK_CONTROL) || (code2 == KeyEvent.VK_META)) {
                        delete(actionListener);
                    }
                }*/
            }
            @Override
            public void keyReleased(KeyEvent event) {
            }
        };

        return keyListener;
    }


    //Build action listener
     static class NotificationActionListener implements ActionListener {
        DefaultListModel<String> listModel;

        NotificationActionListener(DefaultListModel<String> listModel) {
            this.listModel = listModel;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Mark as Read")) {
                try {
                    read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.getActionCommand().equals("Mark as Unread")) {
                try {
                    unread();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.getActionCommand().equals("Delete")) {
                try {
                    delete(listModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        void read() throws Exception {
            if (indices.length == unreadList.size()) {
                readAll();
            } else {
                for (int i : indices) {
                    //mark as read in dataBase
                    String thisLine = unreadList.getElementAt(i);
                    String time = thisLine.substring(0, 19);
                    String content = thisLine.substring(32);
                    dataBase.markRead(customerID, time, content);
                    //change in list panel
                    unreadList.removeElementAt(i);
                    readList.addElement(thisLine);
                }
            }
        }

        void readAll() throws Exception {
            //mark as read in dataBase
            dataBase.markReadAll(customer);
            //change in list panel
            readList = addJListModel(readList, unreadList);
            unreadList.removeAllElements();
        }

        void unread() throws Exception {
            if (indices.length == readList.size()) {
                unreadAll();
            } else {
                for (int i : indices) {
                    //mark as unread in dataBase
                    String thisLine = readList.getElementAt(i);
                    String time = thisLine.substring(0, 19);
                    String content = thisLine.substring(32);
                    dataBase.markUnread(customerID, time, content);
                    //change in list panel
                    readList.removeElementAt(i);
                    unreadList.addElement(thisLine);
                }
            }
        }

        void unreadAll() throws Exception {
            //mark as unread in dataBase
            dataBase.markUnreadAll(customer);
            //change in list panel
            unreadList = addJListModel(unreadList, readList);
            readList.removeAllElements();
        }

        void delete(DefaultListModel<String> listModel) throws Exception {
            if (indices.length == listModel.size()) {
                deleteAll(listModel);
            } else {
                for (int i : indices) {
                    //delete in dataBase
                    String thisLine = listModel.getElementAt(i);
                    String time = thisLine.substring(0, 19);
                    String content = thisLine.substring(32);
                    dataBase.deleteNotification(customerID, time, content);
                    //delete in list panel
                    listModel.removeElementAt(i);
                }
            }
        }

        private void deleteAll(DefaultListModel<String> listModel) throws Exception {
            //delete in dataBase
            if (listModel.equals(unreadList)) {
                dataBase.deleteNotificationUnread(customer);
            } else {
                dataBase.deleteNotificationRead(customer);
            }

            //change in list panel
            listModel.removeAllElements();
        }

        //add two jListModel together
        private DefaultListModel<String> addJListModel(DefaultListModel<String> baseList, DefaultListModel<String> listAdd) {
            for (int i = 0; i < listAdd.getSize(); i++) {
                baseList.addElement(listAdd.getElementAt(i));
            }
            return baseList;
        }
    }






    /*
    private Object[] getSelectedObjects(DefaultListModel<String> listModel, int[] indices) {
        int size = indices.length;
        Object[] selectedObjects = new Object[size]; int index = 0;
        for (int i: indices) {
            selectedObjects[index] = listModel.getElementAt(i);
            index++;
        }
        return selectedObjects;
    }*/
    /*
    protected static ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL= NotificationUI.class.getResource(path);
        if(imgURL!=null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            return null;
        }
    }*/
}

