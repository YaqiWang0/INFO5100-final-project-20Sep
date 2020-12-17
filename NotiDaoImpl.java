package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
// database structure ----- customerID,content,time,isRead

interface NotiDao {
    public List<Notification> findAllNotification(Customer customer) throws Exception;
    public List<Notification> findAllUnread(Customer customer) throws Exception;
    public void insertNotification(String content,Customer customer) throws Exception;
    public void deleteNotification(String customer,String tempTime) throws Exception;
    public void deleteNotificationRead(Customer customer) throws Exception;
    public void deleteNotificationAll(Customer customer) throws Exception;
    //public void markRead(Customer customer) throws Exception;
    public void markRead(String customer,String tempTime) throws Exception;
    public void markReadAll(Customer customer) throws Exception;
    //public void markUnread(Customer customer) throws Exception;
    public void markUnread(String customer,String tempTime) throws Exception;
    public boolean isFindCustomer(String customer,String password) throws Exception;
}

public class NotiDaoImpl extends BaseDao implements NotiDao{
    public List<Notification> findAllNotification(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        String sql="select * from notification where customerID ="+customer.getID();
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=  stmt.executeQuery();
        List<Notification> NotificationList =new ArrayList<Notification>();
        while(rs.next()) {
            Notification notiTemp=new Notification(
                    rs.getString("customerID"),
                    rs.getString("content"),
                    rs.getString("time"),
                    rs.getBoolean("isRead")
            );
            NotificationList.add(notiTemp);
        }
        BaseDao.closeAll(conn, stmt, rs);
        return NotificationList;
    }
    public List<Notification> findAllUnread(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        String sql="select * from notification where customerID ="+customer.getID()+"and isRead = false";
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=  stmt.executeQuery();
        List<Notification> NotificationList =new ArrayList<Notification>();
        while(rs.next()) {
            Notification notiTemp=new Notification(
                    rs.getString("customerID"),
                    rs.getString("content"),
                    rs.getString("time"),
                    rs.getBoolean("isRead")
            );
            NotificationList.add(notiTemp);
        }
        BaseDao.closeAll(conn, stmt, rs);
        return NotificationList;
    }
    public void insertNotification(String content,Customer customer) throws Exception{
        Connection conn=BaseDao.getConnection();
        // the mysql insert statement
        String insertNotiItems = "insert into notification (customerID,content,time,isRead)"
                + " values (?, ? ,? ,?)";

        // create the mysql insert preparedstatement
        PreparedStatement stmt = conn.prepareStatement(insertNotiItems);
        Notification tempNoti = new Notification(customer.getID(),content);
        stmt.setString (1, customer.getID());
        stmt.setString (2, content);
        stmt.setString (3,tempNoti.getTime());
        stmt.setBoolean(4,tempNoti.getIsRead());
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    @Override
    public void deleteNotification(String customer,String tempTime) throws Exception{
        Connection conn=BaseDao.getConnection();
        // create the mysql delete statement.
        String deleteNotiItems = "delete from notification where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteNotiItems);
        stmt.setString(1, customer);
        stmt.setString(2, tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }
    /*
    public void deleteNotification(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // create the mysql delete statement.
        String sql = "select time from notification where CustomerID = ? order by time limit 1";
        PreparedStatement prestmt= conn.prepareStatement(sql);
        prestmt.setString (1, customer.getID());
        ResultSet rs=  prestmt.executeQuery();
        String tempTime = "";
        while (rs.next()) {
            tempTime = rs.getString("time");
        }
        String deleteNotiItems = "delete from notification where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteNotiItems);
        stmt.setString(1, customer.getID());
        stmt.setString(2, tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

     */

    @Override
    public void deleteNotificationRead(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // create the mysql delete statement.
        String deleteNotiItems = "delete from notification where customerID = ? and isRead = true";
        PreparedStatement stmt = conn.prepareStatement(deleteNotiItems);
        stmt.setString(1, customer.getID());
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    public void deleteNotificationAll(Customer customer) throws Exception{
        Connection conn=BaseDao.getConnection();
        // create the mysql delete statement.
        String deleteNotiItems = "delete from notification where customerID = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteNotiItems);
        stmt.setString(1, customer.getID());
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    @Override
    public void markRead(String customer,String tempTime) throws Exception {  //mark as Read for the first unread notification base on time
        Connection conn=BaseDao.getConnection();
        // create the mysql update statement.
        String MarkNotiItems = "update notification set isRead = true where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(MarkNotiItems);
        stmt.setString(1, customer);
        stmt.setString(2, tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    /*
    public void markRead(Customer customer) throws Exception {  //mark as Read for the first unread notification base on time
        Connection conn=BaseDao.getConnection();
        // create the mysql update statement.
        String sql = "select time from notification where isRead = false and CustomerID = ? order by time desc limit 1";
        PreparedStatement prestmt= conn.prepareStatement(sql);
        prestmt.setString(1,customer.getID());
        ResultSet rs= prestmt.executeQuery();
        String tempTime = "";
        while (rs.next()) {
            tempTime = rs.getString("time");
        }
        String MarkNotiItems = "update notification set isRead = true where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(MarkNotiItems);
        stmt.setString(1, customer.getID());
        stmt.setString(2, tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }s

     */

    @Override
    public void markReadAll(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // create the mysql update statement.
        String MarkNotiItems = "update notification set isRead = true where customerID = ? ";
        PreparedStatement stmt = conn.prepareStatement(MarkNotiItems);
        stmt.setString(1, customer.getID());
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    @Override
    public void markUnread(String customer,String tempTime) throws Exception {
        Connection conn=BaseDao.getConnection();
        // create the mysql update statement.
        String unMarkNotiItems = "update notification set isRead = false where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(unMarkNotiItems);
        stmt.setString(1, customer);
        stmt.setString(2,tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    /*
    public void markUnread(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // create the mysql update statement.
        String sql = "select time from notification where isRead = true and CustomerID = ? order by time desc limit 1";
        PreparedStatement prestmt= conn.prepareStatement(sql);
        prestmt.setString(1,customer.getID());
        ResultSet rs=  prestmt.executeQuery();
        String tempTime = "";
        while (rs.next()) {
            tempTime = rs.getString("time");
        }
        String unMarkNotiItems = "update notification set isRead = false where customerID = ? and time = ?";
        PreparedStatement stmt = conn.prepareStatement(unMarkNotiItems);
        stmt.setString(1, customer.getID());
        stmt.setString(2,tempTime);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

     */
    public boolean isFindCustomer(String customer,String customerPassword) throws Exception{
        Connection conn=BaseDao.getConnection();
        String sql="select * from customer where customerID = ? and password = ?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,customer);
        stmt.setString(2,customerPassword);
        ResultSet rs=  stmt.executeQuery();
        try{
            if(rs.next()){
                return true;
            }
            else
                return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            BaseDao.closeAll(conn, stmt, rs);
        }
    }
}
