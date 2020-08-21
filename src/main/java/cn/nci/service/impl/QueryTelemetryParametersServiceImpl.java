package cn.nci.service.impl;

import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.service.QueryTelemetryParametersService;

import java.util.List;

/**
 * JDBCProject
 *
 * @author xiejianfeng
 * @date 2020-06-01
 * @time 10:52
 */
public class QueryTelemetryParametersServiceImpl implements QueryTelemetryParametersService {
    private QueryTelemetryParametersService queryTelemetryParametersService = new QueryTelemetryParametersServiceImpl();

    @Override
    public List<QueryTelemetryParameters> findAll(QueryTelemetryParameters q) {
        return queryTelemetryParametersService.findAll(q);
    }
}
