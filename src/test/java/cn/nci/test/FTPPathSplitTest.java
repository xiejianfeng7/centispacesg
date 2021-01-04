package cn.nci.test;

import cn.nci.util.FtpClientUtil;

/**
 * @program: centispacesg
 * @description: FTP路径获取文件目录结构
 * @author: xiejianfeng
 * @create: 2021-01-04 13:34
 */
public class FTPPathSplitTest {
    public static void main(String[] args) throws Exception {
        String ftp = "ftp://192.168.2.53/a/b/c/d/abc.txt";
        System.out.println(ftp.split("ftp://" + "192.168.2.53/")[1]);
        FtpClientUtil instance = null;

        instance = FtpClientUtil.getInstance("ftpconfig.json", "0203");
        System.out.println(FtpClientUtil.ftpHost);
        instance.close();
    }
}
