package cn.nci.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-05-26
 * @time 11:46
 * 原生的数据库连接操作方法
 * <p>
 * 操作数据库的工具类
 */
public class JDBCUtils {
    public static Connection connection;
    public static Statement statement;

    static {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        try {
            // 使用properties对象加载输入流
            properties.load(in);
            // 读取配置文件中的四个基本信息，获取key对应的value值
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            // 加载数据库驱动
            Class.forName(driver);

            // 获取数据库连接
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * 获取Statement
     *
     * @return
     */
    public static Statement getStatement() {
        return statement;
    }

    /**
     * 关闭数据库连接
     * 参数：Statement是PrepareStatement的父类，可以接收传入的参数
     *
     * @param conn
     * @param ps
     */
    public static void closeConnection(Connection conn, Statement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     * 参数：Statement是PrepareStatement的父类，可以接收传入的参数
     *
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeConnection(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
