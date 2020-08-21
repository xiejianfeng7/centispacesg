package cn.nci.dao;

import cn.nci.domain.EMBLHeader;

import java.util.List;

/**
 * @program: centispacesg
 * @description: EMBL入库接口
 * @author: xiejianfeng
 * @create: 2020-07-23 15:21
 */
public interface EMBLHeaderDao {
    void save(EMBLHeader emblHeader);

    List<EMBLHeader> findAll();
}
