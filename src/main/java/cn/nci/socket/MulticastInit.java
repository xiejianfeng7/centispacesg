package cn.nci.socket;

import cn.nci.domain.MulticastAddress;
import cn.nci.util.ReadJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @program: centispacesg
 * @description: 组播地址初始化
 * @author: xiejianfeng
 * @create: 2020-07-28 14:01
 */
public class MulticastInit {
    public static List<MulticastAddress> init(String jsonPath) {
        List<MulticastAddress> list = new ArrayList<>();
        JSONObject data = null;
        HashMap<String, Object> map = null;
        JSONObject json = JSONObject.parseObject(ReadJson.getJson(jsonPath));
        Set<String> set = json.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            MulticastAddress ma = new MulticastAddress();
            data = json.getJSONObject(it.next());
            map = JSON.parseObject(String.valueOf(data), HashMap.class);
            for (String key : map.keySet()) {
                if (key == "groupHost") {
                    ma.setGroupHost((String) map.get(key));
                } else if (key == "port") {
                    ma.setPort((Integer) map.get(key));
                } else if (key == "isCreate") {
                    ma.setIsCreate((Integer) map.get(key));
                }
            }
            list.add(ma);
        }
        return list;
    }
}
