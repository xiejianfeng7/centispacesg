package cn.nci.socket;

import cn.nci.domain.MulticastAddress;
import cn.nci.util.ReadJson;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: centispacesg
 * @description: 组播地址初始化
 * @author: xiejianfeng
 * @create: 2020-07-28 14:01
 */
public class MulticastInit {
    public static HashMap<String, MulticastAddress> initMulticastAddress(String jsonPath) {
        JSONObject json = JSONObject.parseObject(ReadJson.getJson(jsonPath));
        HashMap<String, MulticastAddress> map = new HashMap<>();
        Set<String> keys = json.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String moudleName = it.next();
            MulticastAddress multicastAddress = JSONObject.parseObject(json.getString(moudleName), MulticastAddress.class);
            map.put(moudleName, multicastAddress);
        }
        return map;
    }

    // 2021年1月4日18:38:27 优化配置文件读取方式

//    public static List<MulticastAddress> initReceiveAddress(String jsonPath) {
//        List<MulticastAddress> list = new ArrayList<>();
//        JSONObject data = null;
//        HashMap<String, Object> map = null;
//        JSONObject json = JSONObject.parseObject(ReadJson.getJson(jsonPath));
//        Set<String> set = json.keySet();
//        Iterator<String> it = set.iterator();
//        while (it.hasNext()) {
//            MulticastAddress ma = new MulticastAddress();
//            data = json.getJSONObject(it.next());
//            map = JSON.parseObject(String.valueOf(data), HashMap.class);
//            for (String key : map.keySet()) {
//                if (key == "groupHost") {
//                    ma.setGroupHost((String) map.get(key));
//                } else if (key == "port") {
//                    ma.setPort((Integer) map.get(key));
//                } else if (key == "isCreate") {
//                    ma.setIsCreate((Integer) map.get(key));
//                }
//            }
//            list.add(ma);
//        }
//        return list;
//    }
}
