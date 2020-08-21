package cn.nci.service.impl;

import cn.nci.dao.TelemetryParametersDao;
import cn.nci.dao.impl.TelemetryParametersDaoImpl;
import cn.nci.domain.TelemetryParametersList;
import cn.nci.service.TelemetryParametersService;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:52
 */
public class TelemetryParametersServiceImpl implements TelemetryParametersService {
    private TelemetryParametersDao parametersDao = new TelemetryParametersDaoImpl();

    @Override
    public void save(TelemetryParametersList telemetryParametersList) {
        parametersDao.save(telemetryParametersList);
    }
}
