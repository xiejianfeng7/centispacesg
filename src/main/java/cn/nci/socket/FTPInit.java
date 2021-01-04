package cn.nci.socket;

import cn.nci.util.ReadJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @program: centispacesg
 * @description: ftp配置文件初始化
 * @author: xiejianfeng
 * @create: 2020-08-06 14:34
 */
public class FTPInit {
    public static Map<String, String> init(String jsonPath, String moduleName) {
        System.out.println(moduleName);
        HashMap<String, String> map = new HashMap<>();
        if (ReadJson.getJson(jsonPath) != null) {
            JSONObject json = JSONObject.parseObject(ReadJson.getJson(jsonPath));
            Set<String> keySet = json.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                if ("module" != key) {
                    map.put(key, json.getString(key));
                } else {
                    JSONObject moudle = JSONObject.parseObject(json.getString(key));
                    Set<String> moudleSet = moudle.keySet();
                    Iterator<String> moudleIt = moudleSet.iterator();
                    while (moudleIt.hasNext()) {
                        String moudleKey = moudleIt.next();
                        if (moduleName.equals(moudleKey)) {
                            map.putAll(JSON.parseObject(String.valueOf(moudle.getString(moudleKey)), HashMap.class));
                        }
                    }
                }
            }
            return map;
        }
        return null;
    }


}
