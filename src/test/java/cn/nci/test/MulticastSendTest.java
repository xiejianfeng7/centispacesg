package cn.nci.test;

import cn.nci.socket.MulticastSend;
import cn.nci.util.ByteUtil;
import cn.nci.wltime.WLTimeConvert;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @program: centispacesg
 * @description: 组播发送数据包
 * @author: xiejianfeng
 * @create: 2020-08-04 15:53
 */
public class MulticastSendTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        byte[] sendPackage = new byte[65535];
        InetAddress address = InetAddress.getByName("232.100.1.3");
        int port = 8024;
        String string = "ftp://192.168.2.56/0022FF02_000001F7_514_202008050001_20200805010000_20200805120000.xml";
        ByteUtil.writeIntL(sendPackage, 0x000001F7, 0);
        ByteUtil.writeIntL(sendPackage, 0x00120102, 4);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getWeek(), 12);
        ByteUtil.writeIntL(sendPackage, WLTimeConvert.getSow(), 16);
        ByteUtil.writeIntL(sendPackage, string.getBytes().length + 11, 24);
        ByteUtil.writeIntL(sendPackage, 0x0022FF02, 28);
        ByteUtil.writeIntL(sendPackage, 0x000001F7, 32);
        sendPackage[36] = 0xF;
        ByteUtil.writeShortL(sendPackage, (short) 0x9, 37);
        ByteUtil.writeString(sendPackage, 39, string);
        for (int i = 0; i < 1; i++) {
            MulticastSend.UDPSend(sendPackage, string.getBytes().length + 39, address, port);
        }
    }
}
