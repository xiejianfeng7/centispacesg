package cn.nci.test;

import cn.nci.util.AutoCreateTable;

/**
 * @program: centispacesg
 * @description: 自动创建表测试
 * @author: xiejianfeng
 * @create: 2020-07-24 10:32
 */
public class AutoCreateTableTest {

    public static void main(String[] args) {
        String tableName = "pack_857_0001";
        if (!AutoCreateTable.isTableExist(tableName)) {
            String createBrandDatabase =
//                    "CREATE TABLE `pack_856_0001`  (\n" +
                    "CREATE TABLE `" + tableName + "`  (\n" +
                    "  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',\n" +
                    "  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',\n" +
                    "  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',\n" +
                    "  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',\n" +
                    "  `SignalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '参数值',\n" +
                    "  PRIMARY KEY (`ID`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;";
            AutoCreateTable.createTable(createBrandDatabase);
        }
    }
}
