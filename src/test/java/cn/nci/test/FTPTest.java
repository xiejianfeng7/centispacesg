package cn.nci.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.FtpClientUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @program: centispacesg
 * @description: ftp服务功能测试
 * @author: xiejianfeng
 * @create: 2020-08-06 10:36
 */
public class FTPTest {
    public static void main(String[] args) throws Exception {

        String fileName = "test1/test2/test3/004F0104_000100C1_20200705079000_20200716085942.dat";
        File file = new File(fileName.trim());
        String[] split = new File(fileName.trim()).getName().split("\\.")[0].split("_");
        Date date = new Date();
        switch (split.length) {
            case 2:
                date = Convert.toDate(split[1]);
                break;
            case 3:
                date = Convert.toDate(split[2]);
                break;
            case 4:
                date = Convert.toDate(split[2]);
                break;
            default:
                date = Convert.toDate(split[split.length - 1]);
                break;
        }
        DateTime dateTime = DateUtil.date(date);
        FtpClientUtil clientUtil = FtpClientUtil.getInstance("src/main/resources/ftpconfig.json", "sjgl");
        String localPath = ByteStringUtil.decToHex(5177603, 8) + "/" + 503 + "/" + dateTime.year() + "/" + String.format("%02d", dateTime.month() + 1);
        File downLoadFile = clientUtil.downLoadFile(file.getParent(), file.getName(), localPath);

        String newestPath = clientUtil.localWorkPath + "/" + "newFile/" + ByteStringUtil.decToHex(5177603, 8) + "/" + 503 + "/" + dateTime.year() + "/" + String.format("%02d", dateTime.month() + 1);
        File newest = new File(newestPath);
        if (deleteDir(newest)) {
            System.out.println("文件夹清空成功！");
        }
        if (!newest.exists()) {
            newest.mkdirs();
        }
        FileInputStream fis = new FileInputStream(downLoadFile);
        FileOutputStream fos = new FileOutputStream(new File(newest + "/" + file.getName()));
        int length = 0;
        byte[] data = new byte[65535];
        while ((length = fis.read(data)) != -1) {
            fos.write(data, 0, length);
            fos.flush();
        }
        fos.close();
        fis.close();
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
