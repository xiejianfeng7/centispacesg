package cn.nci.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @program: centispacesg
 * @description: 数据库连接测试
 * @author: xiejianfeng
 * @create: 2020-07-23 15:39
 */
public class MySQLConnectionTest {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 加载驱动程序，此次无需更改
            Class.forName("com.mysql.jdbc.Driver");
            //输出registered success表示驱动加载成功
            System.out.println("registered success!");
            //创建对象连接，连接数据库，此例中learning为数据库名，root为用户名，123456位密码，该三项均需根据实际情况进行修改，其它代码无需变动。注：确保数据库名、用户名和密码的准确性。
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.53:3306/centispace?useSSL=false&serverTimezone=UTC", "root", "123456");
            Statement stmt = conn.createStatement();

            //输出connection success表示数据库已成功连接
            System.out.println("connection success!");
            Date birthday1 = new Date();
            java.sql.Date birthday = new java.sql.Date(birthday1.getTime());
            System.out.println(birthday);
            String sql = "insert into test(birthday) values('"+birthday+"');";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("error!");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
