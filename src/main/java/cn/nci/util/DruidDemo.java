package cn.nci.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 15:23
 */
public class DruidDemo {

    public static void main(String[] args) {
        // 获取连接
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = JDBCUtilsOpt.getConnection();
            String sql = "insert into t_salary values(null,?,?)";
            pstm = conn.prepareStatement(sql);

            // 设置？的值
            pstm.setString(1, "王五");
            pstm.setInt(2, 4000);

            int count = pstm.executeUpdate();
            if (count >= 1) {
                System.out.println("记录添加成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsOpt.close(pstm, conn);
        }

    }
}
