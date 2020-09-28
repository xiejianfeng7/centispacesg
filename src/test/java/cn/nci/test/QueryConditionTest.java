package cn.nci.test;

import cn.nci.domain.EMBLHeader;
import cn.nci.domain.QueryCondition2;
import cn.nci.wltime.WLTimeConvert;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: centispacesg
 * @description: 查询条件测试
 * @author: xiejianfeng
 * @create: 2020-09-28 16:30
 */
public class QueryConditionTest {
    public static void main(String[] args) {
        EMBLHeader emblHeader = new EMBLHeader();
        emblHeader.setTaskID(4294967295L);
        emblHeader.setDataTypeID(1179905);
        emblHeader.setDeviceID(65537);
        emblHeader.setDate(WLTimeConvert.getWeek());
        emblHeader.setTime(WLTimeConvert.getSow());
        emblHeader.setRes1(0);


//        QueryCondition queryCondition = new QueryCondition();
//        Short message = 90;
//        queryCondition.setMessage(message);
//        queryCondition.setDataType(5439490);
//        List<Integer> satelliteID = new ArrayList<>();
//        satelliteID.add(503);
//        satelliteID.add(504);
//        satelliteID.add(505);
//        queryCondition.setSatelliteID(satelliteID);
        QueryCondition2 queryCondition = new QueryCondition2();
        queryCondition.setSatelliteID(511);

        JSONObject jsonObject = JSONObject.parseObject(JSONArray.toJSON(queryCondition).toString());

        Object object = jsonObject.get("satelliteID");
        if(object instanceof Integer){
            List<Integer> list = new ArrayList<>();
            list.add((Integer)object);
        }
        else if (object instanceof JSONObject) {
            Integer integer = (Integer) object;
            System.out.println(integer);
            System.out.println("===========");

        } else if (object instanceof JSONArray) {
            try {
                List<Integer> list = JSONObject.parseArray(jsonObject.getJSONArray("satelliteID").toJSONString(), Integer.class);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }
            } catch (Exception e) {
                System.out.println("satellite_ID 参数类别未找到");
            }
        }
    }
}
