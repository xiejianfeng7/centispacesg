package cn.nci.test;

import cn.nci.domain.EMBLHeader;
import cn.nci.domain.FileArchiveMessage;
import cn.nci.parse.Message;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: centispacesg
 * @description: 文件更新消息测试
 * @author: xiejianfeng
 * @create: 2020-09-18 11:24
 */
public class FileUpdateMessageTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        while (true){
            EMBLHeader emblHeader = new EMBLHeader();
            emblHeader.setTaskID(503L);
            FileArchiveMessage fileArchiveMessage = new FileArchiveMessage();
            fileArchiveMessage.setDataType(0x0022FF02);
            // <数据类型标识>_<卫星标识>_<地面站编号>_ <计划编号>_<开始时间串>_<结束时间串>.xml
            File file = new File("0022FF02_XAJR_202009180002_20200918111643_20200927112542.xml");
            Message.fileUpdateMessage(emblHeader, fileArchiveMessage, file.getName(), InetAddress.getByName("232.100.1.3"), 9008);
            Thread.sleep(5000);
        }
    }
}
