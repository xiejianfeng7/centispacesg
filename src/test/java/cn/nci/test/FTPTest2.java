package cn.nci.test;

import cn.nci.util.FtpClientUtil;

import java.io.File;

/**
 * @program: centispacesg
 * @description: ftp服务功能测试
 * @author: xiejianfeng
 * @create: 2020-08-06 10:36
 */
public class FTPTest2 {
    public static void main(String[] args) throws Exception {

        String fileName = "guihua/0030FF03_CC_202101040002_20200719090429_20200730090650-1.xml";
//        String fileName = "2021-01-04 13-01.dat";
        FtpClientUtil clientUtil = FtpClientUtil.getInstance("ftpconfig.json", "0203");
        // 上传
//        clientUtil.uploadFtpFile(FtpClientUtil.localWorkPath,fileName,"/");
        // 下载
        clientUtil.downLoadFile("/",fileName,"/");
        clientUtil.close();
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
