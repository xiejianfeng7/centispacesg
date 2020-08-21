package cn.nci.parse;

import cn.nci.domain.TelemetryParameters;
import cn.nci.domain.TelemetryParametersList;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @program: centispacesg
 * @description: 解析JSON字符串
 * @author: xiejianfeng
 * @create: 2020-07-24 14:14
 */
public class TelemetryParse {
    /**
     * {
     * "satid"：value, //星号（整数）
     * "time": "value", //星上时间（字符串）
     * "packid": value, //遥测包号（整数）
     * "tablename": "value", //数据库表名（字符串）
     * "paramlist": {
     *       name:{
     *           "originalValue":value,
     *           "engineerValue":value,
     *           "stateValue",value
     *       },
     *       name:{
     *            "originalValue":value,
     *            "engineerValue":value,
     *            "stateValue",value
     *       }
     *    }
     * }
     */
    public static TelemetryParametersList parseName(JSONObject jsonObject) {
        TelemetryParametersList parameters = new TelemetryParametersList();
        LinkedHashMap<String, TelemetryParameters> map = new LinkedHashMap<>();

        parameters.setSatid(jsonObject.getInteger("satid"));
        parameters.setTime(jsonObject.getString("time"));
        parameters.setPackid(jsonObject.getInteger("packid"));
        parameters.setTableName(jsonObject.getString("tablename"));

        JSONObject paramlist = jsonObject.getJSONObject("paramlist");
        Set<String> set = paramlist.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            JSONObject paramValue = paramlist.getJSONObject(key);
            System.out.println(paramValue);
            TelemetryParameters param = new TelemetryParameters();
            param.setOriginalValue(paramValue.getLong("originalValue"));
            param.setEngineerValue(paramValue.getString("engineerValue"));
            param.setStateValue(paramValue.getString("stateValue"));
            map.put(key, param);
        }
        parameters.setParam(map);
        return parameters;
    }
}
