package cn.nci.parse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.nci.domain.EMBLHeader;
import cn.nci.domain.FileArchiveMessage;
import cn.nci.domain.MulticastAddress;
import cn.nci.main.Main;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.ByteUtil;
import cn.nci.util.FtpClientUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @program: centispacesg
 * @description: 处理归档申请
 * @author: xiejianfeng
 * @create: 2020-08-05 11:51
 */
public class ProFileRequest {

    /**
     * 功能描述: 处理文件归档请求
     *
     * @Param: [emblHeader]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/20 14:31
     */
    public void file(EMBLHeader emblHeader) {
        FtpClientUtil clientUtil = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File downLoadFile = null;
        File file = null;
        String fileName = new String();
        String broadAddress;
        MulticastAddress multicastAddress = null;

        FileArchiveMessage fileArchiveMessage = new FileArchiveMessage();
        byte[] data = emblHeader.getContent();

        fileArchiveMessage.setDataType(ByteUtil.readIntL(data, 0));
        fileArchiveMessage.setSatID(ByteUtil.readIntL(data, 4));
        fileArchiveMessage.setStationFlag(data[8]);
        fileArchiveMessage.setFileFlag(ByteUtil.readShortL(data, 9));
        fileArchiveMessage.setFilePath(ByteUtil.readString(data, 11, emblHeader.getDataLength() - 11));
        try {
            // 获取FTP账号信息
            // 根据用户在EMBL包头打的设备标识锁定用户FTP账户
            clientUtil = FtpClientUtil.getInstance("ftpconfig.json", ByteStringUtil.decToHex(emblHeader.getDeviceID(), 4));
            fileName = fileArchiveMessage.getFilePath().split("ftp://" + FtpClientUtil.ftpHost + "/")[1];
            file = new File(fileName.trim());
            // 获取文件基准时间
            DateTime dateTime = getFileBaseTime(fileName);
            if (dateTime == null) {
                Main.logger.error("");
                return;
            }

            // 根据数据类型划分模块 key:数据类型 value:所属模块，可以根据模块找到该模块的FTP账号密码
            // 接下来就是归档文件，将文件放在指定的位置。


            // 确认归档策略
            // ./类型/型号（503）/站/以文件名基准时间为准，拆分年/月/
            // 00220011/503/514/2020/08/<数据类型>_<业务类型>_<源地址>_<开始时间串>_<结束时间串>.txt

            String localPath = ByteStringUtil.decToHex(fileArchiveMessage.getDataType(), 8) + File.separator + emblHeader.getTaskID() + File.separator + fileArchiveMessage.getStationFlag() + File.separator + dateTime.year() + File.separator + String.format("%02d", dateTime.month() + 1);
            downLoadFile = clientUtil.downLoadFile(file.getParent(), file.getName(), localPath);
            boolean status = clientUtil.deleteFile("./", file.getName());
            if (status) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

            // 将最新文件存放在一个单独为目录中
            String newestPath = clientUtil.localWorkPath + File.separator + "newFile/" + ByteStringUtil.decToHex(fileArchiveMessage.getDataType(), 8) + File.separator + emblHeader.getTaskID() + File.separator + fileArchiveMessage.getStationFlag();
            File newest = new File(newestPath);
            if (deleteDir(newest)) {
                System.out.println("文件夹清空成功！");
            }
            if (!newest.exists()) {
                newest.mkdirs();
            }
            fis = new FileInputStream(downLoadFile);
            fos = new FileOutputStream(new File(newest + File.separator + file.getName()));
            int length = 0;
            byte[] readData = new byte[65535];
            while ((length = fis.read(readData)) != -1) {
                fos.write(readData, 0, length);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientUtil != null) {
                    clientUtil.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        broadAddress = ByteStringUtil.decToHex(emblHeader.getDeviceID(), 4);
        sendMessage(emblHeader, fileArchiveMessage, broadAddress);
//        if (Main.udpSendAdderss.containsKey(broadAddress)) {
//            multicastAddress = Main.udpSendAdderss.get(broadAddress);
//            if (multicastAddress != null) {
//                String ip = multicastAddress.getGroupHost();
//                int port = multicastAddress.getPort();
//                try {
//                    Message.fileArchiveMessage(emblHeader, fileArchiveMessage, (byte) 0, InetAddress.getByName(ip), port);
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Main.logger.error("归档回执发送失败1");
//            }
//        }

        // 给其他用户发送文件更新消息。
        broadAddress = "GBDZ";
        sendMessage(emblHeader, fileArchiveMessage, broadAddress);
//        if (Main.udpSendAdderss.containsKey(broadAddress)) {
//            multicastAddress = Main.udpSendAdderss.get(broadAddress);
//            if (multicastAddress != null) {
//                String ip = multicastAddress.getGroupHost();
//                int port = multicastAddress.getPort();
//                try {
//                    Message.fileUpdateMessage(emblHeader, fileArchiveMessage, file.getName(), InetAddress.getByName(ip), port);
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Main.logger.error("归档回执发送失败2");
//            }
//        } else {
//            Main.logger.error("配置文件中不包含广播地址，请查验。");
//        }

    }

    private void sendMessage(EMBLHeader emblHeader, FileArchiveMessage fileArchiveMessage, String broadAddress) {
        String ip = null;
        int port = 0;
        if (Main.udpSendAdderss.containsKey(broadAddress)) {
            MulticastAddress multicastAddress = Main.udpSendAdderss.get(broadAddress);
            if (multicastAddress != null) {
                ip = multicastAddress.getGroupHost();
                port = multicastAddress.getPort();
                try {
                    Message.fileArchiveMessage(emblHeader, fileArchiveMessage, (byte) 0, InetAddress.getByName(ip), port);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            } else {
                Main.logger.error("归档回执发送失败，模块：" + broadAddress + "，地址：" + ip + "，端口：" + port);
            }
        } else {
            Main.logger.error("配置文件中不包含 " + broadAddress + " 模块地址，请查验。");
        }
    }

    public DateTime getFileBaseTime(String fileName) {
        Date date = new Date();
        System.out.println(fileName);
        try {
            String[] split = new File(fileName.trim()).getName().split("\\.")[0].split("_");
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
                case 5:
                    date = Convert.toDate(split[3]);
                    break;
                case 6:
                    date = Convert.toDate(split[4]);
                    break;
                default:
                    date = Convert.toDate(split[split.length - 1]);
                    break;
            }
        } catch (Exception e) {
            Main.logger.error(e.toString());
        }
        return DateUtil.date(date);
    }

    /**
     * 功能描述: 清空文件夹
     *
     * @Param: [dir]
     * @Return: boolean
     * @Author: john
     * @Date: 2020/8/20 17:53
     */
    private boolean deleteDir(File dir) {
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
