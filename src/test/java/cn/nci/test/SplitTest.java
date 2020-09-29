package cn.nci.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.io.File;
import java.util.Date;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-29 10:45
 */
public class SplitTest {
    public static void main(String[] args) {
        Date date = new Date();
        DateTime dateTime = new DateTime();
        String fileName = "0022FF02_00001F7_XAJR_202009180002_20200927112542_20200927112542.xml";
        File file = new File(fileName.trim());
        String[] split = new File(fileName.trim()).getName().split("\\.")[0].split("_");
        System.out.println(split.length);
        switch (split.length) {
            case 2:
                date = Convert.toDate(split[1]);
                break;
            case 3:
                date = Convert.toDate(split[2]);
                break;
            case 4:
                date = Convert.toDate(split[2]);
                break;
            case 5:
                date = Convert.toDate(split[3]);
                break;
            case 6:
                date = Convert.toDate(split[4]);
                break;
            default:
                date = Convert.toDate(split[split.length - 1]);
                break;
        }
        System.out.println(DateUtil.date(date));
    }
}
