package cn.nci.main;

import cn.nci.domain.MulticastAddress;
import cn.nci.socket.EMBLInit;
import cn.nci.socket.MulticastInit;
import cn.nci.socket.MulticastParse;
import cn.nci.socket.MulticastReceive;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: Main
 * @Author: xiejianfeng
 * @Version: 1.0
 * @Description: TODO
 * @Date: Create in 2020-07-16 18:52
 * @Modified By:
 */
public class Main {
    public static Map<String, ArrayList> map = EMBLInit.init("src/main/resources/emblconfig.json");

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        List<MulticastAddress> list = MulticastInit.init("src/main/resources/udpconfig.json");
        Iterator<MulticastAddress> it = list.iterator();
        while (it.hasNext()) {
            MulticastAddress ma = it.next();
            if (ma.getIsCreate() == 1) {
                // 声明一个容量为10的缓存队列
                BlockingQueue<DatagramPacket> queue = new ArrayBlockingQueue<>(65535);
                // new 生产者和消费者
                MulticastReceive multicastReceive = new MulticastReceive(ma.getGroupHost(), ma.getPort(), queue);
                MulticastParse multicastParse = new MulticastParse(queue);
                // 启动线程
                service.execute(multicastReceive);
                service.execute(multicastParse);
            }
        }
    }
}
