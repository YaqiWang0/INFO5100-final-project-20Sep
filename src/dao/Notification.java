package dao;

import java.time.*;
import java.time.format.DateTimeFormatter;
public class Notification {
    private String customerID;
    private String content;
    private String time;
    private boolean isRead = false;

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
}
