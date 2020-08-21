package cn.nci.socket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MulticastReceive
 * @Author: xiejianfeng
 * @Version: 1.0
 * @Description: TODO
 * @Date: Create in 2020-07-16 18:30
 * @Modified By:
 */
public class MulticastReceive implements Runnable {
    // 是否在运行标志
    private volatile boolean isRunning = true;
    private String groupHost = null;            // 组播地址
    private int port = 0;                       // 端口号
    private BlockingQueue<DatagramPacket> queue;

    public MulticastReceive(String groupHost, int port, BlockingQueue<DatagramPacket> queue) {
        this.groupHost = groupHost;
        this.port = port;
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动成功！组播地址：" + groupHost + "，端口：" + port);
        try {
            // 创建multicastSocket实例
            MulticastSocket multicastSocket = new MulticastSocket(port);
            // 组播地址
            InetAddress inetAddress = InetAddress.getByName(groupHost);
            // 加入组播组
            multicastSocket.joinGroup(inetAddress);

            while (true) {
                byte[] bytes = new byte[65535];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                multicastSocket.receive(datagramPacket);
                if (!queue.offer(datagramPacket, 60, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + datagramPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
