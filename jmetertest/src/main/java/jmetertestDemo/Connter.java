package jmetertestDemo;


/**
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator 定义获取和关闭数据源的方法
 */
public class Connter {

    /**
     * 注册数据库驱动
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     * 
     * @throws SQLException
     */
    public static Connection getConnection(String url, String user,
            String password) throws SQLException {
    	Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭数据源
     * 
     * @throws SQLException
     */
    public static void closeConnection(Connection conn) throws SQLException {
        if (null != conn) {
            conn.close();
        }
    }
}