package cn.nci.dao;

import cn.nci.domain.TelemetryParametersList;

/**
 * @program: centispacesg
 * @description: 遥测参数入库接口
 * @author: xiejianfeng
 * @create: 2020-07-24 14:20
 */
public interface TelemetryParametersDao {
    void save(TelemetryParametersList telemetryParametersList);
}
