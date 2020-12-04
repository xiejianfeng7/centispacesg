package cn.nci.main;

import cn.nci.domain.MulticastAddress;
import cn.nci.socket.EMBLInit;
import cn.nci.socket.MulticastInit;
import cn.nci.socket.MulticastParse;
import cn.nci.socket.MulticastReceive;
import cn.nci.util.FtpClientUtil;
import org.apache.log4j.Logger;

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
    // 初始化日志记录
    public static Logger logger = Logger.getLogger(Main.class);

    // 初始化embl接口文件
    public static Map<String, ArrayList> map = EMBLInit.init("emblconfig.json");

    // 初始化FTP账号相关信息
    FtpClientUtil clientUtil = null;

    {
        try {
            clientUtil = FtpClientUtil.getInstance("ftpconfig.json", "localWorkPath");
        } catch (Exception e) {
            Main.logger.error("FTP账号配置文件初始化失败。");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        List<MulticastAddress> list = MulticastInit.init("udpconfig.json");
        Iterator<MulticastAddress> it = list.iterator();
        while (it.hasNext()) {
            MulticastAddress ma = it.next();
            if (ma.getIsCreate() == 1) {
                // 声明一个容量为65535的缓存队列
                BlockingQueue<DatagramPacket> queue = new ArrayBlockingQueue<>(65535);
                // new 生产者和消费者
                MulticastReceive multicastReceive = new MulticastReceive(ma.getGroupHost(), ma.getPort(), queue);
                MulticastParse multicastParse = new MulticastParse(ma.getGroupHost(), ma.getPort(), queue);
                // 启动线程
                service.execute(multicastReceive);
                service.execute(multicastParse);
            }
        }
    }
}
