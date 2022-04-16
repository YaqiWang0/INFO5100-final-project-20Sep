package dao;

import java.sql.*;

public class BaseDao {
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://db-mysql-sfo2-74616-do-user-8424667-0.b.db.ondigitalocean.com:25060/info5100?serverTimezone=UTC";

    private static String user="doadmin";
    private static String password="zlvikviydhlekqyz";
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if(rs!=null) {
            rs.close();
        }
        if(stmt!=null) {
            stmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }

    public static void closeConnStat(Connection conn, Statement stmt) throws SQLException {
        if(stmt!=null) {
            stmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }


    public int executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        /* 处理SQL,执行SQL */
        try {
            conn = getConnection(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            ResultSet num = pstmt.executeQuery(); // 执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace(); // 处理SQLException异常
        } finally {
            try {
                BaseDao.closeAll(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}