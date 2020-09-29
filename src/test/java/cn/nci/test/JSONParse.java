package cn.nci.test;

import cn.hutool.core.date.DateTime;
import cn.nci.domain.QueryCondition;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-29 10:22
 */
public class JSONParse {
    public static void main(String[] args) {
        QueryCondition queryCondition = new QueryCondition();
        String json = "{\n" +
                "\"Type\":2293506,\n" +
                "\"Message\":7295,\n" +
                "\"station\":514,\n" +
                "\"start\":\"2020-09-27 11:25:42\",\n" +
                "\"end\":\"2020-09-27 11:25:42\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(json);

        queryCondition.setMessage(jsonObject.getShort("Message"));
        queryCondition.setDataType(jsonObject.getInteger("Type"));
        Object object = jsonObject.get("station");
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

        System.out.println(queryCondition);
    }
}
