package cn.nci.parse;

import cn.nci.domain.EMBLHeader;
import cn.nci.domain.FileArchiveMessage;
import cn.nci.domain.GetReplyMessage;
import cn.nci.socket.MulticastSend;
import cn.nci.util.ByteUtil;
import cn.nci.wltime.WLTimeConvert;

import java.net.InetAddress;

/**
 * @program: centispacesg
 * @description: 文件/数据应答更新消息
 * @author: xiejianfeng
 * @create: 2020-08-27 11:15
 */
public class Message {
    /**
     * 功能描述: 文件/数据获取应答消息
     *
     * @Param: [emblHeader, fileHeader, fileName, address, port]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/27 10:28
     */
    public static void getReplyMessage(EMBLHeader emblHeader, GetReplyMessage getReplyMessage, int replyLength, InetAddress address, int port) {
        byte[] sendPackage = new byte[65535];
        byte[] fillEMBLMessageHeader = fillEMBLMessageHeader(emblHeader.getTaskID(), 0x00120201, 4 + replyLength);
        System.arraycopy(fillEMBLMessageHeader, 0, sendPackage, 0, 28);
        ByteUtil.writeIntL(sendPackage, getReplyMessage.getDataType(), 28);
        ByteUtil.writeShortL(sendPackage, getReplyMessage.getMessage(), 32);
        sendPackage[34] = getReplyMessage.getReplyFlag();
        ByteUtil.writeShortL(sendPackage, getReplyMessage.getFileCount(), 35);
        ByteUtil.writeString(sendPackage, 37, getReplyMessage.getPathCollection().toString());
        MulticastSend.UDPSend(sendPackage, 28 + replyLength, address, port);
    }

    /**
     * 功能描述: 发送文件归档响应
     *
     * @Param: [emblHeader, fileArchiveMessage, response, address, port]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/20 14:31
     */
    public static void fileArchiveMessage(EMBLHeader emblHeader, FileArchiveMessage fileArchiveMessage, byte response, InetAddress address, int port) {
        byte[] sendPackage = new byte[65535];
        byte[] fillEMBLMessageHeader = fillEMBLMessageHeader(emblHeader.getTaskID(), 0x00120202, 7);
        System.arraycopy(fillEMBLMessageHeader, 0, sendPackage, 0, 28);
        ByteUtil.writeIntL(sendPackage, fileArchiveMessage.getDataType(), 28);
        sendPackage[32] = response;
        ByteUtil.writeShortL(sendPackage, fileArchiveMessage.getFileFlag(), 33);
        MulticastSend.UDPSend(sendPackage, 7 + 28, address, port);
    }

    /**
     * 功能描述: 文件更新消息
     *
     * @Param: [emblHeader, fileArchiveMessage, fileName, address, port]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/27 10:28
     */
    public static void fileUpdateMessage(EMBLHeader emblHeader, FileArchiveMessage fileArchiveMessage, String fileName, InetAddress address, int port) {
        byte[] sendPackage = new byte[65535];
        byte[] fillEMBLMessageHeader = fillEMBLMessageHeader(emblHeader.getTaskID(), 0x00120203, 4 + fileName.length());
        System.arraycopy(fillEMBLMessageHeader, 0, sendPackage, 0, 28);
        ByteUtil.writeIntL(sendPackage, fileArchiveMessage.getDataType(), 28);
        ByteUtil.writeString(sendPackage, 32, fileName);
        MulticastSend.UDPSend(sendPackage, 4 + 28 + fileName.length(), address, port);
    }

    private static byte[] fillEMBLMessageHeader(int taskID, int dataTypeID, int dateLength) {
        byte[] EMBLMessageHeader = new byte[28];
        ByteUtil.writeIntL(EMBLMessageHeader, taskID, 0);
        ByteUtil.writeIntL(EMBLMessageHeader, dataTypeID, 4);
        ByteUtil.writeIntL(EMBLMessageHeader, 0x00000103, 8);
        ByteUtil.writeIntL(EMBLMessageHeader, WLTimeConvert.getWeek(), 12);
        ByteUtil.writeIntL(EMBLMessageHeader, WLTimeConvert.getSow(), 16);
        /**
         * 由低向高表示：
         * 2bit表示优先级，0-3取值，0级最高，3级最低，默认填3；
         * 1bit表示ACK，0代表无效，1代表有效，默认填0；
         * 4bit，表示该包是否需要目的节点确认，
         * ‘0101’：不需要确认，
         * ‘1010’：需要确认，
         * 其余bit默认填‘0101’。
         */
        ByteUtil.writeIntL(EMBLMessageHeader, 0, 20);
        ByteUtil.writeIntL(EMBLMessageHeader, dateLength, 24);
        return EMBLMessageHeader;
    }
}
