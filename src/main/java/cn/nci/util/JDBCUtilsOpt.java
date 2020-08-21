package cn.nci.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:56
 */

/**
 * Druid连接池工具类
 */
public class JDBCUtilsOpt {
    /**
     * 定义成员变量   DataSource
     */
    private static DataSource ds;

    static {
        // 1、加载配置文件
        Properties ps = new Properties();
        try {
            // 使用ClassLoader加载配置文件，获取字节输入流
            InputStream is = JDBCUtilsOpt.class.getClassLoader().getResourceAsStream("druid.properties");
            ps.load(is);

            // 2、初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(ps);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 3、获得连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 4、释放资源
     *
     * @param stat
     * @param conn
     */
    public static void close(Statement stat, Connection conn) {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 5、释放资源
     *
     * @param rs
     * @param stat
     * @param conn
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        close(stat, conn);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 6、获得数据库连接池对象
     *
     * @return
     */
    public static DataSource getDataSource() {
        return ds;
    }
}
