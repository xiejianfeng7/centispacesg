package cn.nci.parse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.nci.domain.EMBLHeader;
import cn.nci.domain.FileHeader;
import cn.nci.domain.SendAddress;
import cn.nci.socket.GetSendAddress;
import cn.nci.socket.MulticastSend;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.ByteUtil;
import cn.nci.util.FtpClientUtil;
import cn.nci.wltime.WLTimeConvert;

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
        FileHeader fileHeader = new FileHeader();
        byte[] data = emblHeader.getContent();
        fileHeader.setDataType(ByteUtil.readIntL(data, 0));
        fileHeader.setSatID(ByteUtil.readIntL(data, 4));
        fileHeader.setStationFlag(data[8]);
        fileHeader.setFileFlag(ByteUtil.readShortL(data, 9));
        fileHeader.setFilePath(ByteUtil.readString(data, 11, emblHeader.getDataLength() - 11));
        String fileName = fileHeader.getFilePath().split("/")[3];
        File file = new File(fileName.trim());
        // 获取文件基准时间
        DateTime dateTime = getFileBaseTime(fileName);

        // 根据数据类型划分模块 key:数据类型 value:所属模块，可以根据模块找到该模块的FTP账号密码
        // 接下来就是归档文件，将文件放在指定的位置。

        /********************************************************/
        /*
            确认归档策略
            ./类型/型号（503）/站/以文件名基准时间为准，拆分年/月/
            00220011/503/514/2020/08/<数据类型>_<业务类型>_<源地址>_<开始时间串>_<结束时间串>.txt
         */
        /********************************************************/
        FtpClientUtil clientUtil = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File downLoadFile = null;
        try {
            // 根据用户在EMBL包头打的设备标识锁定用户FTP账户
            clientUtil = FtpClientUtil.getInstance("src/main/resources/ftpconfig.json", ByteStringUtil.decToHex(emblHeader.getDeviceID(), 4));
            String localPath = ByteStringUtil.decToHex(fileHeader.getDataType(), 8) + "/" + emblHeader.getTaskID() + "/" + fileHeader.getStationFlag() + dateTime.year() + "/" + String.format("%02d", dateTime.month() + 1);
            downLoadFile = clientUtil.downLoadFile(file.getParent(), file.getName(), localPath);

            // 将最新文件存放在一个单独为目录中
            String newestPath = clientUtil.localWorkPath + "/" + "newFile" + ByteStringUtil.decToHex(fileHeader.getDataType(), 8) + "/" + emblHeader.getTaskID() + "/" + fileHeader.getStationFlag();
            File newest = new File(newestPath);
            if (deleteDir(newest)) {
                System.out.println("文件夹清空成功！");
            }
            if (!newest.exists()) {
                newest.mkdirs();
            }
            fis = new FileInputStream(downLoadFile);
            fos = new FileOutputStream(new File(newest + "/" + file.getName()));
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
        // 给归档用户发送文件归档响应。
        SendAddress sendAddress = GetSendAddress.init("src/main/resources/udpsendconfig.json", ByteStringUtil.decToHex(emblHeader.getDeviceID(), 4));
        if (sendAddress != null){
            String ip = sendAddress.getGroupHost();
            int port = sendAddress.getPort();
            try {
                fileReply(emblHeader, fileHeader, (byte) 0, InetAddress.getByName(ip), port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("归档回执发送失败");
        }

        // 给其他用户发送文件更新消息。
        sendAddress = GetSendAddress.init("src/main/resources/udpsendconfig.json", "GBDZ");
        if (sendAddress != null){
            String ip = sendAddress.getGroupHost();
            int port = sendAddress.getPort();
            try {
                fileUpdate(emblHeader, fileHeader, file.getName(), InetAddress.getByName(ip), port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("归档回执发送失败");
        }
    }

    /**
     * 功能描述: 发送文件归档响应
     *
     * @Param: [emblHeader, fileHeader, response, address, port]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/20 14:31
     */
    private void fileReply(EMBLHeader emblHeader, FileHeader fileHeader, byte response, InetAddress address, int port) {
        byte[] sendPackage = new byte[65535];
        ByteUtil.writeIntL(sendPackage, emblHeader.getTaskID(), 0);
        ByteUtil.writeIntL(sendPackage, 0x00120202, 4);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getWeek(), 12);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getSow(), 16);
        ByteUtil.writeIntL(sendPackage, 7, 24);
        ByteUtil.writeIntL(sendPackage, fileHeader.getDataType(), 28);
        sendPackage[32] = response;
        ByteUtil.writeShortL(sendPackage, fileHeader.getFileFlag(), 33);
        MulticastSend.UDPSend(sendPackage, 7 + 28, address, port);
    }

    private void fileUpdate(EMBLHeader emblHeader, FileHeader fileHeader, String fileName, InetAddress address, int port) {
        byte[] sendPackage = new byte[65535];
        ByteUtil.writeIntL(sendPackage, emblHeader.getTaskID(), 0);
        ByteUtil.writeIntL(sendPackage, 0x00120203, 4);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getWeek(), 12);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getSow(), 16);
        ByteUtil.writeIntL(sendPackage, 4 + fileName.length(), 24);
        ByteUtil.writeIntL(sendPackage, fileHeader.getDataType(), 28);
        ByteUtil.writeString(sendPackage, 32, fileName);
        MulticastSend.UDPSend(sendPackage, 4 + 28 + fileName.length(), address, port);
    }

    public DateTime getFileBaseTime(String fileName) {
        Date date = new Date();
        DateTime dateTime = new DateTime();
        File file = new File(fileName.trim());
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
            default:
                date = Convert.toDate(split[split.length - 1]);
                break;
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
