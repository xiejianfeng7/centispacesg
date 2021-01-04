package cn.nci.socket;

import cn.nci.main.Main;
import cn.nci.parse.DatagramParse;

import java.net.DatagramPacket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MulticastParse
 * @Author: xiejianfeng
 * @Version: 1.0
 * @Description: TODO
 * @Date: Create in 2020-07-16 18:41
 * @Modified By:
 */
public class MulticastParse implements Runnable {
    private String groupHost;               // 组播地址
    private int port;                       // 端口号
    private BlockingQueue<DatagramPacket> queue;

    public MulticastParse(String groupHost, int port, BlockingQueue<DatagramPacket> queue) {
        this.groupHost = groupHost;
        this.port = port;
        this.queue = queue;
    }

    @Override
    public void run() {
        Main.logger.info("消费者线程启动成功！组播地址：" + groupHost + "，端口：" + port);
        while (true) {
            try {
                DatagramPacket packet = queue.poll(2, TimeUnit.SECONDS);
//                Main.logger.debug("消费者线程，队列中的元素数：" + queue.size());
                if (null != packet) {
                    DatagramParse.parseDatagram(packet.getData(), packet.getLength());
                }
            } catch (InterruptedException e) {
                Main.logger.error(Thread.currentThread().getName() + " 运行异常");
            }
        }
    }
}
