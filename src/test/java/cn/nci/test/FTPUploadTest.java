package cn.nci.test;

import cn.nci.util.FtpClientUtil;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-08-25 17:45
 */
public class FTPUploadTest {
    public static void main(String[] args) throws Exception {
        FtpClientUtil clientUtil = FtpClientUtil.getInstance("src/main/resources/ftpconfig.json", "sjgl");
        clientUtil.uploadFtpFile("D:\\FTP\\004f0104\\503\\514\\2020\\07", "004F0104_000100CF_20200731214921_20200810214921.dat", "/");
    }
}
