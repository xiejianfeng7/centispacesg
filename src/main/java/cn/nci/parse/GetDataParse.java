package cn.nci.parse;

import cn.hutool.core.date.DateTime;
import cn.nci.domain.QueryCondition;
import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.domain.TelemetryParameters;
import cn.nci.main.Main;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
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
        QueryCondition queryCondition = new QueryCondition();
        Object object = null;
        Main.logger.warn("JSON 串参数内容为：" + jsonObject);
        try {

//            List<Integer> freq = JSONObject.parseArray(jsonObject.getJSONArray("freq").toJSONString(), Integer.class);
//            object = jsonObject.get("station");
//            List<Integer> station = null;
//            if (object instanceof Integer) {
//                List<Integer> list = new ArrayList<>();
//                list.add((Integer) object);
//                satelliteID = list;
//            } else if (object instanceof JSONArray) {
//                try {
//                    satelliteID = JSONObject.parseArray(jsonObject.getJSONArray("station").toJSONString(), Integer.class);
//                } catch (Exception e) {
//                    System.out.println("station 参数类别未找到");
//                }
//            }
//            List<Integer> source = JSONObject.parseArray(jsonObject.getJSONArray("source").toJSONString(), Integer.class);
//            DateTime start = new DateTime(jsonObject.getTimestamp("start"));
//            DateTime end = new DateTime(jsonObject.getTimestamp("end"));
//            Integer newFile = jsonObject.getInteger("new");
//            List<String> param = JSONObject.parseArray(jsonObject.getJSONArray("param").toJSONString(), String.class);
//            if (message >= 0 && message <= 9999 && dataType != null) {
//                queryCondition = new QueryCondition(message, dataType, satelliteID, freq, station, source, start, end, newFile, param);
//            }
            // 文件/数据获取参数
            // 获取Message
            queryCondition.setMessage(jsonObject.getShort("Message"));
            // 获取Type
            queryCondition.setDataType(jsonObject.getInteger("Type"));
            // 获取satellite_id
            object = jsonObject.get("satellite_id");
            List<Integer> satelliteID = null;
            if (object instanceof Integer) {
                List<Integer> list = new ArrayList<>();
                list.add((Integer) object);
                satelliteID = list;
            } else if (object instanceof JSONArray) {
                try {
                    satelliteID = JSONObject.parseArray(jsonObject.getJSONArray("satelliteID").toJSONString(), Integer.class);
                } catch (Exception e) {
                    System.out.println("satellite_ID 参数类别未找到");
                }
            }
            queryCondition.setSatelliteID(satelliteID);

            object = jsonObject.get("station");
            List<Integer> station = null;
            if (object instanceof Integer) {
                List<Integer> list = new ArrayList<>();
                list.add((Integer) object);
                station = list;
            } else if (object instanceof JSONArray) {
                try {
                    station = JSONObject.parseArray(jsonObject.getJSONArray("station").toJSONString(), Integer.class);
                } catch (Exception e) {
                    System.out.println("station 参数类别未找到");
                }
            }
            DateTime start = new DateTime(jsonObject.getTimestamp("start"));
            DateTime end = new DateTime(jsonObject.getTimestamp("end"));
            queryCondition.setStart(start);
            queryCondition.setEnd(end);
        } catch (Exception e) {
            Main.logger.error("收到的 JSON 串参数不符合接口要求。");
        }
        return queryCondition;
    }
}
