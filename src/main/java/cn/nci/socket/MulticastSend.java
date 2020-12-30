package cn.nci.socket;

import cn.nci.main.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

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

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        String message = "1234";
        int length = message.getBytes().length;
        for (int i = 0; i < 100; i++) {
            UDPSend(message.getBytes(),length,InetAddress.getByName("231.100.130.2"), 9000);
            Thread.sleep(1000);
        }
    }
}
