package dao;
//import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface DealerDao {
    public List<Dealer> findAllInventory(Customer customer) throws Exception;
    public void insertDealer(String dealerID,String dealerName,Customer customer) throws Exception;
    public void deleteDealer(String dealerID,Customer customer) throws Exception;
}

public class DealerDaoImpl extends BaseDao implements DealerDao{
    public List<Dealer> findAllInventory(Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        String sql="select dealerId,dealerName from dealer where customerID ="+customer.getID();
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=  stmt.executeQuery();
        List<Dealer> dealerList=new ArrayList<Dealer>();
        while(rs.next()) {
            Dealer dealer=new Dealer(
                    rs.getString("dealerId"),
                    rs.getString("dealerName")
                    );
            dealerList.add(dealer);
        }
        BaseDao.closeAll(conn, stmt, rs);
        return dealerList;
    }
    public void insertDealer(String dealerID,String dealerName,Customer customer) throws Exception{
        Connection conn=BaseDao.getConnection();
        // the mysql insert statement
        String insertDealerItems = " insert into dealer (customerID, dealerID, dealerName)"
                + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement stmt = conn.prepareStatement(insertDealerItems);
        stmt.setString (1, customer.getID());
        stmt.setString (2, dealerID);
        stmt.setString   (3, dealerName);
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }
    public void deleteDealer(String dealerID,Customer customer) throws Exception{
        Connection conn=BaseDao.getConnection();
        // create the mysql delete statement.
        String deleteDealerItems = "delete from dealer where customerID = ? and dealerID = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteDealerItems);
        stmt.setString(1, customer.getID());
        stmt.setString(2, dealerID);
        // execute the preparedstatement
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }
}

