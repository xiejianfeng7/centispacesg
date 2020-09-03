package cn.nci.test;

/**
 * @program: centispacesg
 * @description: 字符串转为Int，全为F
 * @author: xiejianfeng
 * @create: 2020-09-03 14:12
 */
public class StringtoHexTest {
    public static void main(String[] args) {
        String s = "FFFFFFFF";
        int i = Integer.parseUnsignedInt(s,16);
        System.out.println(i);
    }
}
