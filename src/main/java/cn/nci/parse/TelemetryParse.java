package cn.nci.parse;

import cn.nci.domain.TelemetryALiList;
import cn.nci.domain.TelemetryParametersList;
import com.alibaba.fastjson.JSON;

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
//    public static TelemetryParametersList parseName(JSONObject jsonObject) {
//        TelemetryParametersList parameters = new TelemetryParametersList();
//        LinkedHashMap<String, TelemetryParameters> map = new LinkedHashMap<>();
//
//        parameters.setSatid(jsonObject.getInteger("satid"));
//        parameters.setTime(jsonObject.getString("time"));
//        parameters.setPackid(jsonObject.getInteger("packid"));
//        parameters.setTableName(jsonObject.getString("tablename"));
//
//        JSONObject paramlist = jsonObject.getJSONObject("paramlist");
//        Set<String> set = paramlist.keySet();
//        Iterator<String> it = set.iterator();
//        while (it.hasNext()) {
//            String key = it.next();
//            JSONObject paramValue = paramlist.getJSONObject(key);
//            System.out.println(paramValue);
//            TelemetryParameters param = new TelemetryParameters();
//            param.setOriginalValue(paramValue.getLong("originalValue"));
//            param.setEngineerValue(paramValue.getString("engineerValue"));
//            param.setStateValue(paramValue.getString("stateValue"));
//            map.put(key, param);
//        }
//        parameters.setParam(map);
//        return parameters;
//    }

    /**
     * 遥测包JSON
     * {
     * “"satid”： value,      //星号（整数）
     * “time”: value,          //星上时间（字符串）
     * “packid”: value,      //包号
     * “tablename”: value,     //数据库表名（字符串）
     * “paramlist”:
     * [
     * {
     * “csdh”:value,     // csdh:参数代号
     * “name”:value,     // name:参数中文名
     * “originalValue”:value,   // 原始值（整数）
     * “engineerValue”:value,   // 工程值（字符串）
     * “stateValue”:value,   // 状态值（字符串）
     * },
     * {
     * “csdh”:value,     // csdh:参数代号
     * “name”:value,     // name:参数中文名
     * “originalValue”:value,   // 原始值（整数）
     * “engineerValue”:value,   // 工程值（字符串）
     * “stateValue”:value,   // 状态值（字符串）
     * },
     * ...
     * ]
     * }
     */
    public static TelemetryParametersList parseName(String content) {
//        TelemetryParametersList parameters = new TelemetryParametersList();
//        ArrayList<TelemetryParameters> list = new ArrayList<>();
//
//        parameters.setSatid(jsonObject.getInteger("satid"));
//        parameters.setTime(jsonObject.getString("time"));
//        parameters.setPackid(jsonObject.getInteger("packid"));
//        parameters.settablename(jsonObject.getString("tablename"));
//        JSONArray paramlist = jsonObject.getJSONArray("paramlist");
//        for (int i = 0; i < paramlist.size(); i++) {
//            TelemetryParameters param = new TelemetryParameters();
//            JSONObject paramValue = paramlist.getJSONObject(i);
//
//            param.setCsdh(paramValue.getString("csdh"));
//            param.setName(paramValue.getString("name"));
//            param.setOriginalValue(paramValue.getLong("originalValue"));
//            param.setEngineerValue(paramValue.getString("engineerValue"));
//            param.setStateValue(paramValue.getString("stateValue"));
//            list.add(param);
//        }
//        parameters.setParamlist(list);
        return JSON.parseObject(content, TelemetryParametersList.class);
    }

    public static TelemetryALiList parseALiName(String content) {
//        TelemetryALiList parameters = new TelemetryALiList();
//        ArrayList<TelemetryALi> list = new ArrayList<>();
//
//        parameters.setTablename(jsonObject.getString("tablename"));
//        parameters.setOpr(jsonObject.getString("opr"));
//        JSONArray paramlist = jsonObject.getJSONArray("paramlist");
//        for (int i = 0; i < paramlist.size(); i++) {
//            TelemetryALi param = new TelemetryALi();
//            JSONObject paramValue = paramlist.getJSONObject(i);
//
//            param.setName(paramValue.getString("name"));
//            param.setValue(paramValue.getString("value"));
//            list.add(param);
//        }
//        parameters.setParamlist(list);
//        return parameters;

        return JSON.parseObject(content, TelemetryALiList.class);
    }
}
