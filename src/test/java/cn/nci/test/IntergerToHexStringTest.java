package cn.nci.test;

/**
 * @program: centispacesg
 * @description: 十进制转十六进制指定宽度
 * @author: xiejianfeng
 * @create: 2020-08-04 13:36
 */
public class IntergerToHexStringTest {
    public static void main(String[] args) {
        int a = 65281;
        System.out.println(String.format("%08x",a));
    }
}
