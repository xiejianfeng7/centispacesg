package cn.nci.socket;

import cn.nci.domain.SendAddress;
import cn.nci.util.ReadJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: centispacesg
 * @description: 根据分系统标识获取发送组播地址
 * @author: xiejianfeng
 * @create: 2020-08-21 10:27
 */
public class GetSendAddress {
    public static SendAddress init(String jsonPath,String deviceID) {
        SendAddress sendAddress = new SendAddress();
        JSONObject data = null;
        HashMap<String, Object> map = null;
        JSONObject json = JSONObject.parseObject(ReadJson.getJson(jsonPath));
        Set<String> set = json.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String tempDeviceID = it.next();
            if (deviceID.equals(tempDeviceID)){
                data = json.getJSONObject(tempDeviceID);
                map = JSON.parseObject(String.valueOf(data), HashMap.class);
                for (String key : map.keySet()) {
                    if (key == "groupHost") {
                        sendAddress.setGroupHost((String) map.get(key));
                    } else if (key == "port") {
                        sendAddress.setPort((Integer) map.get(key));
                    }
                }
                return sendAddress;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SendAddress sendAddress = GetSendAddress.init("src/main/resources/udpsendconfig.json", "GBDZ");
        System.out.println(sendAddress);
    }
}
