package cn.nci.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: centispacesg
 * @description: 字节数组输入流测试
 * @author: xiejianfeng
 * @create: 2020-07-24 11:51
 */
public class ByteArrayInputStreamTest {
    public static void main(String[] args) {
        //1.创建源
        byte[] src = "hello".getBytes();
        InputStream is = null;
        try {
            //2.选择流
            is = new ByteArrayInputStream(src);
            //3.操作
            byte[] flush = new byte[22];//缓冲容器
            int len = -1;//接收长度
            while ((len = is.read(flush)) != -1) {
                String str = new String(flush, 0, len);
                System.out.print(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    //4.释放资源
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
