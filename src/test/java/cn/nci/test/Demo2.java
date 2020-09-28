package cn.nci.test;

import cn.nci.main.Main;

import java.util.ArrayList;
import java.util.Map;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-28 20:34
 */
public class Demo2 {

    public static void main(String[] args) {
        Map<String, ArrayList> map = Main.map;
        System.out.println(map.get("station").contains("514"));
        System.out.println(map.get("station"));
    }
}
