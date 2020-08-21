package cn.nci.util;

import java.io.*;

/**
 * @program: centispacesg
 * @description: 读取多层JSON文件
 * @author: xiejianfeng
 * @create: 2020-07-24 15:37
 */
public class ReadJson {
    public static String getJson(String jsonPath) {
        String jsonStr = "";
        try {
            File file = new File(jsonPath);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }
}
