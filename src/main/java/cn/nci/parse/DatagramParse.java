package cn.nci.parse;

import cn.nci.domain.EMBLHeader;
import cn.nci.domain.TelemetryParametersList;
import cn.nci.file.CreateFile;
import cn.nci.file.LogtoFile;
import cn.nci.service.EMBLHeaderService;
import cn.nci.service.TelemetryParametersService;
import cn.nci.service.impl.EMBLHeaderServiceImpl;
import cn.nci.service.impl.TelemetryParametersServiceImpl;
import cn.nci.socket.EMBLInit;
import cn.nci.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: centispacesg
 * @description: 数据包解析
 * @author: xiejianfeng
 * @create: 2020-07-23 13:21
 */
public class DatagramParse {
    public static void parseDatagram(byte[] data, int length) {
        EMBLHeader emblHeader = new EMBLHeader();
        EMBLHeaderService productService = new EMBLHeaderServiceImpl();
        TelemetryParametersService parametersService = new TelemetryParametersServiceImpl();
        List<EMBLHeader> list = new ArrayList<>(100);

        // 文件路径
        CreateFile createFile = new CreateFile();
        LogtoFile logtoFile = new LogtoFile();

        emblHeader.setTaskID(ByteUtil.readUnsignedIntL(data, 0));
        emblHeader.setDataTypeID(ByteUtil.readIntL(data, 4));
        emblHeader.setDeviceID(ByteUtil.readIntL(data, 8));
        emblHeader.setDate(ByteUtil.readIntL(data, 12));
        emblHeader.setTime(ByteUtil.readIntL(data, 16));
        emblHeader.setRes1(ByteUtil.readIntL(data, 20));
        emblHeader.setDataLength(ByteUtil.readIntL(data, 24));
        if (emblHeader.getDataLength() > 65535) {
            return;
        }
        if (!EMBLInit.isEMBLExists(emblHeader)) {
            return;
        }
        // 如果任务标识为8个F的话，入库失败，原因是8个F解析后超出int的表示范围。
//        if (emblHeader.getTaskID() == -1){
//            emblHeader.setTaskID(0L);
//        }
        byte[] content = new byte[emblHeader.getDataLength()];
        System.arraycopy(data, 28, content, 0, emblHeader.getDataLength());
        emblHeader.setContent(content);

        // CRC校验 2020年9月3日17:14:11
//        int re = CRC16.crc16(content);
//        System.out.println(DateUtil.getCurrentTime() + "CRC 校验结果：" + Integer.toHexString(re).toUpperCase());

        // 单独处理遥测数据入库信息
        if (0x00110501 == emblHeader.getDataTypeID()) {
            TelemetryParametersList parseName = TelemetryParse.parseName(new String(content));
            parametersService.save(parseName);
        }

        // 文件获取申请消息
        else if (0x00120101 == emblHeader.getDataTypeID()) {
            // 用户发送数据获取申请，根据获取请求，解析获取的JSON字符串
            ProGetRequest proGetRequest = new ProGetRequest();
            proGetRequest.getFile(emblHeader);
            // 解析出实际内容，根据解析内容查找
        }

        // 文件归档申请消息
        else if (0x00120102 == emblHeader.getDataTypeID()) {
            ProFileRequest proFileRequest = new ProFileRequest();
            proFileRequest.file(emblHeader);
        }

        // 此处记录一期的原始请求，用于测试回放
        else if (0x004D0001 == emblHeader.getDataTypeID() || 0x004D0002 == emblHeader.getDataTypeID() || 0x004D0103 == emblHeader.getDataTypeID() || 0x004D0104 == emblHeader.getDataTypeID()) {
            String fileName = createFile.createFile("./归档回执原始数据/" + Integer.toHexString(emblHeader.getDataTypeID()) + "/", "dat");
            logtoFile.dataToFile(fileName, data, length, true);
        } else {
            list.add(emblHeader);
            productService.save(list);
        }
    }
}
