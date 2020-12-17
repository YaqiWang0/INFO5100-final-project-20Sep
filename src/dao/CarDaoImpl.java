package dao;
//import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface CarDao {
    public List<Vehicle> findAllVehicle(Customer customer) throws Exception;
    public void insertVehicle(String vehicleID,String dealerId,Customer customer) throws Exception;
    public void deleteVehicle(String vehicleID,Customer customer) throws Exception;
}

public class CarDaoImpl extends BaseDao implements CarDao{
    public List<Vehicle> findAllVehicle(Customer customer) throws Exception {    //select无法自行构建vehicle
        Connection conn=BaseDao.getConnection();
        String sql="select vehicleID,dealerId from car where customerID ="+customer.getID();
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=  stmt.executeQuery();
        List<Vehicle> vehicleList=new ArrayList<Vehicle>();
        while(rs.next()) {
            Vehicle cars=new Vehicle(
                    rs.getString("vehicleID"),
                    rs.getString("dealerId")
            );
            vehicleList.add(cars);
        }
        BaseDao.closeAll(conn, stmt, rs);
        return vehicleList;
    }

    @Override
    public void insertVehicle(String vehicleID, String dealerId, Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // the mysql insert statement
        String insertCarsItems = "insert into car (customerID,vehicleID,dealerID)"
                + " values (?, ? ,?)";

        // create the mysql insert preparedstatement
        PreparedStatement stmt = conn.prepareStatement(insertCarsItems);
        stmt.setString (1, customer.getID());
        stmt.setString (2, vehicleID);
        stmt.setString   (3, dealerId);
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }

    @Override
    public void deleteVehicle(String vehicleID, Customer customer) throws Exception {
        Connection conn=BaseDao.getConnection();
        // the mysql insert statement
        String deleteCarsItems = "delete from car where customerID = ? and vehicleID = ?";

        // create the mysql insert preparedstatement
        PreparedStatement stmt = conn.prepareStatement(deleteCarsItems);
        stmt.setString (1, customer.getID());
        stmt.setString (2, vehicleID);
        stmt.execute();
        BaseDao.closeConnStat(conn,stmt);
    }
}
