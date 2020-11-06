package cn.nci.dao.impl;

import cn.nci.dao.TelemetryParametersDao;
import cn.nci.domain.TelemetryParameters;
import cn.nci.domain.TelemetryParametersList;
import cn.nci.util.AutoCreateTable;
import cn.nci.util.DateUtil;
import cn.nci.util.JDBCUtilsOpt;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

/**
 * @program: centispacesg
 * @description: 遥测参数入库实现类
 * @author: xiejianfeng
 * @create: 2020-07-24 14:21
 */
public class TelemetryParametersDaoImpl implements TelemetryParametersDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsOpt.getDataSource());

    @Override
    public void save(TelemetryParametersList telemetryParametersList) {
        String signalSatTime = telemetryParametersList.getTime();

        String tableName = telemetryParametersList.gettablename();
        if (!AutoCreateTable.isTableExist(tableName)) {
            String createBrandDatabase =
                    "CREATE TABLE `" + tableName + "`  (\n" +
                            "  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',\n" +
                            "  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',\n" +
                            "  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',\n" +
                            "  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',\n" +
                            "  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',\n" +
                            "  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',\n" +
                            "  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',\n" +
                            "  PRIMARY KEY (`ID`) USING BTREE\n" +
                            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;";
            AutoCreateTable.createTable(createBrandDatabase);
        }
        // 2020年10月22日18:21:00修改前
//        LinkedHashMap<String, TelemetryParameters> map = telemetryParametersList.getParam();
//        for (String key : map.keySet()) {
//            String sql = "insert into `" + tableName + "`(CodeID,SignalSatTime,OriginalValue,EngineerValue,StateValue) values (?,?,?,?,?)";
//            int row = jdbcTemplate.update(sql, key, DateUtil.strToDate(signalSatTime), map.get(key).getOriginalValue(), map.get(key).getEngineerValue(), map.get(key).getStateValue());
//            if (row > 0) {
//                System.out.println("更新 " + row + " 行。");
//            }
//        }

        // 按照新接口修改
        ArrayList<TelemetryParameters> paramlist = telemetryParametersList.getParamlist();
        for (int i = 0; i < paramlist.size(); i++) {
            TelemetryParameters parameters = paramlist.get(i);
            String sql = "insert into `" + tableName + "`(CodeID,SignalSatTime,OriginalValue,EngineerValue,StateValue) values (?,?,?,?,?)";
            int row = jdbcTemplate.update(
                    sql,
                    parameters.getCsdh(),
                    DateUtil.strToDate(signalSatTime),
                    parameters.getOriginalValue(),
                    parameters.getEngineerValue(),
                    parameters.getStateValue());
            if (row > 0) {
                System.out.println("更新 " + row + " 行。");
            }
        }
    }
}
