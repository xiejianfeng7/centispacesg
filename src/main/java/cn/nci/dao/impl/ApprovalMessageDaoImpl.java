package cn.nci.dao.impl;

import cn.nci.dao.ApprovalMessageDao;
import cn.nci.domain.ApprovalMessage;
import cn.nci.util.AutoCreateTable;
import cn.nci.util.JDBCUtilsOpt;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: centispacesg
 * @description: 遥测参数入库实现类
 * @author: xiejianfeng
 * @create: 2020-07-24 14:21
 */
public class ApprovalMessageDaoImpl implements ApprovalMessageDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsOpt.getDataSource());

    @Override
    public void save(ApprovalMessage message) {
        String tableName = "approval_message";
        if (!AutoCreateTable.isTableExist(tableName)) {
            String createBrandDatabase =
                    "CREATE TABLE `approval_message`  (\n" +
                            "  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',\n" +
                            "  `message_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息编号',\n" +
                            "  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户信息',\n" +
                            "  `sat_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卫星号',\n" +
                            "  `instr_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指令编号',\n" +
                            "  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人邮箱',\n" +
                            "  `applicant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件人邮箱',\n" +
                            "  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',\n" +
                            "  `appraval_id` int(11) NULL DEFAULT NULL COMMENT '审批人代号',\n" +
                            "  `approval_result` int(11) NULL DEFAULT NULL COMMENT '审批结果',\n" +
                            "  `is_send` int(11) NULL DEFAULT NULL COMMENT '是否发送给卫星',\n" +
                            "  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',\n" +
                            "  PRIMARY KEY (`id`) USING BTREE\n" +
                            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;";
            AutoCreateTable.createTable(createBrandDatabase);
        }


        // 按照新接口修改
        String sql = "insert into `" + tableName + "`(message_id,user_name,sat_id,instr_id,approver,applicant,content) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                message.getMessageID(),
                message.getUser(),
                message.getSatID(),
                message.getInstrID(),
                message.getApprover().toString(),
                message.getApplicant(),
                message.getContent());
    }
}
