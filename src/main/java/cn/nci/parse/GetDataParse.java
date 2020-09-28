package cn.nci.parse;

import cn.hutool.core.date.DateTime;
import cn.nci.domain.QueryCondition;
import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.domain.TelemetryParameters;
import cn.nci.main.Main;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

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
        QueryCondition queryCondition = null;
        Main.logger.warn("JSON 串参数内容为：" + jsonObject);
        /*
            private Short message;              // 自定义4位数字，消息编号，与获取应答消息中的消息编号对应，只填1个
            private Integer dataType;           // 必填，只填1个

            private List<Integer> satelliteID;  // 卫星标识
            private List<Integer> freq;         // 填“S”或者“X”
            private List<Integer> station;
            private List<Integer> source;       // 源地址
            private DateTime start;             // 开始时间
            private DateTime end;               // 结束时间
            private Integer newFile;            // 最新文件/数据
            private List<String> param;         // 只有获取或写入数据库数据时填写
        */
        try {
            Short message = (Short) jsonObject.getShort("Message");
            Integer dataType = jsonObject.getInteger("type");
            List<Integer> satelliteID = JSONObject.parseArray(jsonObject.getJSONArray("satellite_id").toJSONString(), Integer.class);
            List<Integer> freq = JSONObject.parseArray(jsonObject.getJSONArray("freq").toJSONString(), Integer.class);
            List<Integer> station = JSONObject.parseArray(jsonObject.getJSONArray("station").toJSONString(), Integer.class);
            List<Integer> source = JSONObject.parseArray(jsonObject.getJSONArray("source").toJSONString(), Integer.class);
            DateTime start = new DateTime(jsonObject.getTimestamp("start"));
            DateTime end = new DateTime(jsonObject.getTimestamp("end"));
            Integer newFile = jsonObject.getInteger("new");
            List<String> param = JSONObject.parseArray(jsonObject.getJSONArray("param").toJSONString(), String.class);
            if (message >= 0 && message <= 9999 && dataType != null) {
                queryCondition = new QueryCondition(message, dataType, satelliteID, freq, station, source, start, end, newFile, param);
            }
        } catch (Exception e) {
            Main.logger.error("JSON 串参数不符合接口要求。" + jsonObject);
        }
        return queryCondition;
    }
}
