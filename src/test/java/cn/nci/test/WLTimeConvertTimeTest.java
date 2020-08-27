package cn.nci.test;

import cn.nci.wltime.WLTimeConvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: centispacesg
 * @description: 微厘时转换测试
 * @author: xiejianfeng
 * @create: 2020-07-23 14:09
 */
public class WLTimeConvertTimeTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
        Date date = WLTimeConvert.getBJTime(190, 387648);
        System.out.println(sdf.format(date));

        System.out.println(WLTimeConvert.getWeek());
        System.out.println(WLTimeConvert.getSow());
    }


}
