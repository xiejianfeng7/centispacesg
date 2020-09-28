package cn.nci.test;

import cn.hutool.core.date.DateUtil;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-28 18:20
 */
public class Demo {
    public static void main(String[] args) {

        long time = DateUtil.parse("2020-01-01 00:00:00").getTime();
        System.out.println(time);
        System.out.println(System.currentTimeMillis());
    }
}
