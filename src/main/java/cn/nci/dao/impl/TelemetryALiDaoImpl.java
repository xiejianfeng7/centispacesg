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
    Statement stmt;
    StringBuilder name = new StringBuilder();
    StringBuilder value = new StringBuilder();
    StringBuilder update = new StringBuilder();
    String insert;

    @Override
    public void save(TelemetryALiList telemetryALiList) {
        String tableName = telemetryALiList.getTablename();
        String option = telemetryALiList.getOpr();
        update.append("update " + tableName + " set ");

        ArrayList<TelemetryALi> paramlist = telemetryALiList.getParamlist();
        Iterator<TelemetryALi> it = paramlist.iterator();
        TelemetryALi telemetryALi;

        try {
            //
            stmt = conn.createStatement();
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
                insert = "insert into " + tableName + "(" + name + ") values " + "(" + value + ");";
                System.out.println(insert);
                stmt.execute(insert);
            }
            // 更新操作
            else if ("update".equals(option)) {
                while (it.hasNext()) {
                    telemetryALi = it.next();
                    update.append(telemetryALi.getName() + "=\'" + telemetryALi.getValue() + "\',");
                }
                update.replace(update.length() - 1, update.length(), ";");
//                System.out.println(update);
                stmt.execute(update.toString());
            }
            // 其他处理
            else {
                System.out.println(telemetryALiList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(tableName + "出现异常");
        }
    }
}
