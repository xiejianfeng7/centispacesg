package cn.nci.service;

import cn.nci.domain.TelemetryParametersList;

/**
 * @program: centispacesg
 * @description: 遥测数据服务接口
 * @author: xiejianfeng
 * @create: 2020-07-24 15:13
 */
public interface TelemetryParametersService {
    void save(TelemetryParametersList telemetryParametersList);
}
