package cn.nci.dao;

import cn.nci.domain.QueryTelemetryParameters;

import java.util.List;

/**
 * @program: centispacesg
 * @description: 遥测参数入库接口
 * @author: xiejianfeng
 * @create: 2020-07-24 14:20
 */
public interface QueryTelemetryParametersDao {
    List<QueryTelemetryParameters> findAll(QueryTelemetryParameters q);
}
