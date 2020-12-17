package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    private String customerID;
    private String content;
    private String time;
    private boolean isRead = false;

    public static void main(String[] args) {
        List<Notification> rawUnread = Notification.testUnread();
        List<Notification> rawRead = Notification.testRead();

        String thisLine = "2020-12-14 18:42:18             Your car is sold";
        String time = thisLine.substring(0, 19);
        String content = thisLine.substring(32);

        System.out.println("             ".length());

        System.out.println(time);
        System.out.println(content);
    }



    public Notification(String customerID,String content) {
        this.customerID = customerID;
        this.content = content;
        //to get date and time of the notification
        LocalDateTime localDateTime = LocalDateTime.now();
        //格式化时间
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = localDateTime.format(formatDateTime);
    }
    public Notification(String customerID,String content,String time, boolean isRead) {
        this.customerID = customerID;
        this.content = content;
        this.time = time;
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getTime() {
        return time;
    }

    public boolean getIsRead() {
        return isRead;
    }


    //test data
    public static List<Notification> testUnread() {
        List<Notification> notificationList =new ArrayList<Notification>();
        notificationList.add(new Notification("1111", "Your car is sold"));
        notificationList.add(new Notification("1111", "Your car is sold"));
        notificationList.add(new Notification("1111", "Your car's price changed"));
        notificationList.add(new Notification("1111", "Your dealer changed his/her car list"));
        notificationList.add(new Notification("1111", "Your car's price changed"));
        notificationList.add(new Notification("1111", "Your dealer changed his/her car list"));

        return notificationList;
    }
    public static List<Notification> testRead() {
        List<Notification> notificationList =new ArrayList<Notification>();
        notificationList.add(new Notification("1111", "Your car is sold", "2020-12-14 18:42:18", true));
        notificationList.add(new Notification("1111", "Your car's price changed", "2020-12-14 18:42:10", true));
        notificationList.add(new Notification("1111", "Your dealer changed his/her car list", "2020-12-14 18:42:16", true));
        notificationList.add(new Notification("1111", "Your car is sold", "2020-12-14 18:42:12", true));

        return notificationList;
    }
}
