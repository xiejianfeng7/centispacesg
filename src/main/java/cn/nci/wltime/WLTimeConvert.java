package cn.nci.wltime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: centispacesg
 * @description: 微厘时周计数和周内秒
 * @author: xiejianfeng
 * @create: 2020-07-23 13:55
 */
public class WLTimeConvert {
    /**
     * 功能描述: 获取微厘时周计数
     *
     * @Param: []
     * @Return: java.lang.Long
     * @Author: john
     * @Date: 2020/8/5 10:53
     */
    public static int getWeek() {
        return (int) WLTimeConvert.getWeekAndSow().toArray()[0];
    }

    /**
     * 功能描述: 根据指定时间获取微厘时周计数
     *
     * @Param: yyyy MM dd HH mm ss
     * @Return: java.lang.Long
     * @Author: john
     * @Date: 2020/7/23 14:08
     */
    public static int getWeek(String dateString) {
        return (int) WLTimeConvert.getWeekAndSow(dateString).toArray()[0];
    }

    /**
     * 功能描述: 获取微厘时周内秒
     *
     * @Param: []
     * @Return: java.lang.Long
     * @Author: john
     * @Date: 2020/8/5 10:53
     */
    public static int getSow() {
        return (int) WLTimeConvert.getWeekAndSow().toArray()[1];
    }

    /**
     * 功能描述: 根据指定时间获取微厘时周内秒
     *
     * @Param: yyyy MM dd HH mm ss
     * @Return: java.lang.Long
     * @Author: john
     * @Date: 2020/7/23 14:08
     */
    public static int getSow(String dateString) {
        return (int) WLTimeConvert.getWeekAndSow(dateString).toArray()[1];
    }

    /**
     * 功能描述: 获取当前周计数和周内秒
     *
     * @Param: []
     * @Return: java.util.List<java.lang.Long>
     * @Author: john
     * @Date: 2020/8/5 11:02
     */
    private static List<Integer> getWeekAndSow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
        Date date = new Date();
        String dateString = sdf.format(date);
        List<Integer> weekAndSow = WLTimeConvert.getWeekAndSow(dateString);
        return weekAndSow;
    }

    /**
     * 功能描述:
     *
     * @Param: yyyy MM dd HH mm ss
     * @Return: java.util.List<java.lang.Long>
     * @Author: john
     * @Date: 2020/7/23 14:07
     */
    private static List<Integer> getWeekAndSow(String dateString) {
        List<Integer> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
        try {
            Date wlStart = sdf.parse("2017 01 01 00 00 00");
            Date currentDate = sdf.parse(dateString);
            long wlSecond = wlStart.getTime() / 1000;
            long currentSecond = currentDate.getTime() / 1000;
            int week = (int) (currentSecond - wlSecond) / (7 * 24 * 3600);
            int sow = (int) (currentSecond - wlSecond) % (7 * 24 * 3600);
            list.add(week);
            list.add(sow);
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述:
     *
     * @Param: [week, sow] 周计数、周内秒
     * @Return: java.util.Date
     * @Author: john
     * @Date: 2020/7/23 14:08
     */
    public static Date getBJTime(int week, int sow) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
        Date date = new Date();
        long milliseconds = 0;
        try {
            milliseconds = (week * (7 * 24 * 3600) + sow) * 1000L + sdf.parse("2017 01 01 00 00 00").getTime();
            date.setTime(milliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
