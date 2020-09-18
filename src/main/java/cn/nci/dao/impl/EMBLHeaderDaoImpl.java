package cn.nci.dao.impl;

import cn.nci.dao.EMBLHeaderDao;
import cn.nci.domain.EMBLHeader;
import cn.nci.util.AutoCreateTable;
import cn.nci.util.DateUtil;
import cn.nci.util.JDBCUtilsOpt;
import cn.nci.wltime.WLTimeConvert;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: centispacesg
 * @description: EMBL入库实现类
 * @author: xiejianfeng
 * @create: 2020-07-23 15:22
 */
public class EMBLHeaderDaoImpl implements EMBLHeaderDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsOpt.getDataSource());

    @Override
    public void save(EMBLHeader emblHeader) {
        String tableName = "table_" + String.format("%08x", emblHeader.getDataTypeID());
        if (!AutoCreateTable.isTableExist(tableName)) {
            String createBrandDatabase = "CREATE TABLE `" + tableName + "`  (\n" +
                    "  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',\n" +
                    "  `task_id` bigint(20) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',\n" +
                    "  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',\n" +
                    "  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',\n" +
                    "  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',\n" +
                    "  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',\n" +
                    "  `res1` int(10) UNSIGNED NULL DEFAULT NULL,\n" +
                    "  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',\n" +
                    "  `content` blob NULL COMMENT '实际数据段内容',\n" +
                    "  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',\n" +
                    "  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',\n" +
                    "  PRIMARY KEY (`id`) USING BTREE,\n" +
                    "  INDEX `task_id`(`task_id`) USING BTREE,\n" +
                    "  INDEX `wl_date`(`wl_date`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;";
            AutoCreateTable.createTable(createBrandDatabase);
        }
        String sql = "insert into " + tableName + "(task_id,data_type_id,device_id,wl_date,wl_time,res1,data_length,content,recv_time) values (?,?,?,?,?,?,?,?,?)";
        Date date = WLTimeConvert.getBJTime((int) emblHeader.getDate(), (int) emblHeader.getTime());
        Timestamp timestamp = new Timestamp(date.getTime());
        int update = jdbcTemplate.update(sql, emblHeader.getTaskID(), emblHeader.getDataTypeID(), emblHeader.getDeviceID(), emblHeader.getDate(), emblHeader.getTime(), emblHeader.getRes1(), emblHeader.getDataLength(), emblHeader.getContent(), timestamp);
        System.out.println(DateUtil.getCurrentTime() + " 更新 " + update + " 条数据，数据类型为："+Integer.toHexString(emblHeader.getDataTypeID()));
    }

    @Override
    public List<EMBLHeader> findAll() {
        String sql = "select * from message";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<EMBLHeader>(EMBLHeader.class));
    }
}
