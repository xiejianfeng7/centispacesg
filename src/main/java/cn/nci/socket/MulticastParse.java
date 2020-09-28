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
                // 有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
                DatagramPacket packet = queue.poll(2, TimeUnit.SECONDS);
                if (null != packet) {
//                    Main.logger.info("拿到数据：" + packet);
                    DatagramParse.parseDatagram(packet.getData(), packet.getLength());
                }
//                else {
//                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
//                    isRunning = false;
//                }
            } catch (InterruptedException e) {
                Main.logger.error(Thread.currentThread().getName() + " 运行异常");
            }
        }
    }
}
