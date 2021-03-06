package cn.nci.socket;

import cn.nci.domain.EMBLHeader;
import cn.nci.main.Main;
import cn.nci.util.ReadJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @program: centispacesg
 * @description: EMBL初始化
 * @author: xiejianfeng
 * @create: 2020-08-04 11:27
 */
public class EMBLInit {
    public static Map<String, ArrayList> init(String jsonPath) {
        String content = null;
        HashMap<String, ArrayList> map = null;
        JSONObject json = null;
        if ((content = ReadJson.getJson(jsonPath)) != null) {
            map = new HashMap<>();
            json = JSONObject.parseObject(content);
            Set<String> set = json.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                String key = it.next();
                JSONArray array = json.getJSONArray(key);
                ArrayList list = JSON.parseObject(String.valueOf(array), ArrayList.class);
                for (int i = 0; i < list.size(); i++) {
//                    修复任务标识为8个F的Bug 2020年9月3日14:21:31
//                    arrayList.add(Integer.parseInt((String) list.get(i), 16));
                    // 正确结果
                    arrayList.add(Integer.parseUnsignedInt((String) list.get(i), 16));
                }
                map.put(key, arrayList);
            }
            Main.logger.warn("EMBL包头信息初始化完成。");
            return map;
        }
        return null;
    }

    public static boolean isEMBLExists(EMBLHeader emblHeader) {
        Map<String, ArrayList> map = Main.EMBL;
        int taskid = emblHeader.getTaskID().intValue();
        if (map.get("taskID").contains(taskid) && map.get("dataTypeID").contains(emblHeader.getDataTypeID())) {
            return true;
        } else {
            System.out.println("不符合接口规范,taskid:" + taskid + ",dataTypeID:" + Integer.toHexString(emblHeader.getDataTypeID()));
            return false;
        }
    }
}
