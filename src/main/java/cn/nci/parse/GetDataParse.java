package cn.nci.parse;

import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.domain.TelemetryParameters;
import com.alibaba.fastjson.JSONObject;

/**
 * @program: centispacesg
 * @description: 文件/数据获取请求信息解析
 * @author: xiejianfeng
 * @create: 2020-08-21 14:04
 */
public class GetDataParse {
    public static QueryTelemetryParameters parseName(JSONObject jsonObject) {
        QueryTelemetryParameters parameters = new QueryTelemetryParameters();
        TelemetryParameters tp = new TelemetryParameters();
        parameters.setCodeID(jsonObject.getString("codeID"));
        parameters.setSignalGndTime(jsonObject.getTimestamp("signalGndTime"));
        parameters.setSignalSatTime(jsonObject.getTimestamp("sinalSatTime"));
        parameters.setOriginalValue(jsonObject.getLong("orginalValue"));
        parameters.setEngineerValue(jsonObject.getString("engineerValue"));
        parameters.setStateValue(jsonObject.getString("stateValue"));
        return parameters;
    }
}
