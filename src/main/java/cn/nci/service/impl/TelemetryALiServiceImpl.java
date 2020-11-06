package cn.nci.service.impl;

import cn.nci.dao.TelemetryALiDao;
import cn.nci.dao.impl.TelemetryALiDaoImpl;
import cn.nci.domain.TelemetryALiList;
import cn.nci.service.TelemetryALiService;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:52
 */
public class TelemetryALiServiceImpl implements TelemetryALiService {
    private TelemetryALiDao parametersDao = new TelemetryALiDaoImpl();

    @Override
    public void save(TelemetryALiList telemetryParametersList) {
        parametersDao.save(telemetryParametersList);
    }
}
