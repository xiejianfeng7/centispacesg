package cn.nci.service;

import cn.nci.domain.EMBLHeader;

import java.util.List;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:52
 */
public interface EMBLHeaderService {

    void save(List<EMBLHeader> productList);

    List<EMBLHeader> findAll();
}
