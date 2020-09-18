package cn.nci.parse;

import cn.nci.domain.QueryCondition;
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

    public static QueryCondition parseFileRequest(JSONObject jsonObject) {
        QueryCondition queryCondition = new QueryCondition();

        // 参见附录5 文件获取数据类型的JSON串最大集合，共12个参数
        /*
         以下是5.3.6文件/数据获取申请消息定义的内容
         {
            "Message":"值",
            "Type":"值",
            "param1":"值",
            "Sat":"值",
            "Freq":"",
            "start":"2020-05-20 09:00:00",
            "end":"2020-05-25 09:00:00"
         }
         */

        return queryCondition;
    }
}
