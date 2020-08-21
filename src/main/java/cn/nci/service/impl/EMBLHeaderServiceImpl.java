package cn.nci.service.impl;

import cn.nci.dao.EMBLHeaderDao;
import cn.nci.dao.impl.EMBLHeaderDaoImpl;
import cn.nci.domain.EMBLHeader;
import cn.nci.service.EMBLHeaderService;

import java.util.List;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:52
 */
public class EMBLHeaderServiceImpl implements EMBLHeaderService {
    private EMBLHeaderDao emblHeaderDao = new EMBLHeaderDaoImpl();

    @Override
    public void save(List<EMBLHeader> emblHeaders) {
        for (EMBLHeader emblHeader : emblHeaders) {
            emblHeaderDao.save(emblHeader);
        }
    }

    @Override
    public List<EMBLHeader> findAll() {
        return emblHeaderDao.findAll();
    }
}
