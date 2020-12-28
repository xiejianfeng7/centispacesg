package cn.nci.dao;

import cn.nci.domain.ApprovalMessage;

/**
 * @program: centispacesg
 * @description: 遥测参数入库接口
 * @author: xiejianfeng
 * @create: 2020-07-24 14:20
 */
public interface ApprovalMessageDao {
    void save(ApprovalMessage message);
}
