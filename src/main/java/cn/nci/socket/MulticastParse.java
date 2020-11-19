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
    private BlockingQueue<DatagramPacket> queue;

    public MulticastParse(BlockingQueue<DatagramPacket> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Main.logger.info("消费者线程启动成功！");
        boolean isRunning = true;
        while (isRunning) {
            try {
                DatagramPacket packet = queue.poll(2, TimeUnit.SECONDS);
                if (null != packet) {
                    DatagramParse.parseDatagram(packet.getData(), packet.getLength());
                }
            } catch (InterruptedException e) {
                Main.logger.error(Thread.currentThread().getName() + " 运行异常");
            }
        }
    }
}
