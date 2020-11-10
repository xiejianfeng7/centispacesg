package cn.nci.dao.impl;

import cn.nci.dao.TelemetryALiDao;
import cn.nci.domain.TelemetryALi;
import cn.nci.domain.TelemetryALiList;
import cn.nci.util.JDBCUtils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @program: centispacesg
 * @description: 遥测参数入库实现类
 * @author: xiejianfeng
 * @create: 2020-07-24 14:21
 */
public class TelemetryALiDaoImpl implements TelemetryALiDao {
    // 获取数据库连接
    Connection conn = JDBCUtils.getConnection();
    Statement stmt = JDBCUtils.getStatement();

    StringBuilder name = new StringBuilder();
    StringBuilder value = new StringBuilder();
    StringBuilder sql = new StringBuilder();
    static int count = 0;

    @Override
    public void save(TelemetryALiList telemetryALiList) {
        String tableName = telemetryALiList.getTablename();
        String option = telemetryALiList.getOpr();
        ArrayList<TelemetryALi> paramlist = telemetryALiList.getParamlist();
        Iterator<TelemetryALi> it = paramlist.iterator();
        TelemetryALi telemetryALi;

        try {
            // 插入操作
            if ("insert".equals(option)) {
                while (it.hasNext()) {
                    telemetryALi = it.next();
                    name.append(telemetryALi.getName() + ",");
                    if (telemetryALi.getValue().contains(":")) {
                        value.append("\'" + telemetryALi.getValue() + "\',");
                    } else {
                        value.append(telemetryALi.getValue() + ",");
                    }
                }
                name.deleteCharAt(name.length() - 1);
                value.deleteCharAt(value.length() - 1);
                sql.append("insert into " + tableName + "(" + name + ") values " + "(" + value + ");");
            }
            // 更新操作
            else if ("update".equals(option)) {
                sql.append("update " + tableName + " set ");
                while (it.hasNext()) {
                    telemetryALi = it.next();
                    sql.append(telemetryALi.getName() + "=\'" + telemetryALi.getValue() + "\',");
                }
                sql.replace(sql.length() - 1, sql.length(), ";");
            }
            // 其他处理
            else {
                System.out.println(telemetryALiList);
            }
            // 记录日志
//            Main.logger.info(sql);
            // 数据批量入库，攒10条数据入库
            stmt.addBatch(sql.toString());
            if (++count % 10 == 0) {
                long start = System.currentTimeMillis();
                int[] batch = stmt.executeBatch();
                long end = System.currentTimeMillis();
                System.out.println("更新了" + batch.length + "条数据。。count = " + count + "。。耗时：" + (end - start));
                stmt.clearBatch();
                count = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(tableName + "出现异常");
        }
    }
}
