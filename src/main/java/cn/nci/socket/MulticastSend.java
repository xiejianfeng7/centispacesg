package cn.nci.socket;

import cn.nci.main.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @program: centispacesg
 * @description: 组播数据包发送
 * @author: xiejianfeng
 * @create: 2020-08-04 15:53
 */
public class MulticastSend {
    public static void UDPSend(byte buf[], int length, InetAddress address, int port) {
        try {
            MulticastSocket multicastSocket = new MulticastSocket();
            multicastSocket.setTimeToLive(128);
            DatagramPacket datagramPacket = new DatagramPacket(buf, length, address, port);
            multicastSocket.send(datagramPacket);
            multicastSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Main.logger.info("数据发送成功，组播地址：" + address + "，端口：" + port);
        }
    }
}
