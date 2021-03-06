package cn.nci.parse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.nci.domain.EMBLHeader;
import cn.nci.domain.GetReplyMessage;
import cn.nci.domain.QueryCondition;
import cn.nci.domain.SendAddress;
import cn.nci.main.Main;
import cn.nci.socket.GetSendAddress;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.FtpClientUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: centispacesg
 * @description: 处理获取申请
 * @author: xiejianfeng
 * @create: 2020-08-05 11:53
 */
public class ProGetRequest {
//    private QueryTelemetryParametersService parametersService = new QueryTelemetryParametersServiceImpl();
//    private String outPutPath = "D://";

    /**
     * 功能描述: 处理文件/数据获取请求
     *
     * @Param: [emblHeader]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/21 14:18
     */
    public void getFile(EMBLHeader emblHeader) {
        JSONObject jsonObject = JSONObject.parseObject(new String(emblHeader.getContent()));
        QueryCondition queryCondition = GetDataParse.parseFileRequest(jsonObject);
        // 如果用户给的查询条件解析之后无效，直接返回，不做后续处理。
        if (queryCondition == null) {
            Main.logger.error("文件获取请求无效，不符合接口规范。");
            return;
        }
        // 1、根据用户查询时间查找文件
        List<File> fileList = findFile(emblHeader, queryCondition);

        // 2、将查找的文件上传到指定的FTP地址
        FtpClientUtil clientUtil = null;
        StringBuilder stringBuilder = new StringBuilder();
        GetReplyMessage getReplyMessage = new GetReplyMessage();
        if (null == queryCondition || null == queryCondition.getDataType() || null == queryCondition.getMessage()) {
            return;
        } else {
            getReplyMessage.setDataType(queryCondition.getDataType());
            getReplyMessage.setMessage(queryCondition.getMessage());
        }
        try {
            clientUtil = FtpClientUtil.getInstance("ftpconfig.json", "sjgl");
            String ftpHost = "ftp://" + clientUtil.ftpHost + "/";
            for (File file : fileList) {
                // 此处连接FTP次数与数据文件个数成正比，要优化 2020年8月25日17:54:36
//                System.out.println(file.getParent());
//                System.out.println(file.getName());
//                System.out.println(file.getAbsolutePath());
                stringBuilder.append(ftpHost + file.getName() + ";");
                clientUtil.uploadFtpFile(file.getParent(), file.getName(), "/");
            }
            clientUtil.close();
            // 删除最后一个;号
            if (fileList.size() >= 1) {
                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
            }
            Byte flag = 0;
            getReplyMessage.setReplyFlag(flag);

            getReplyMessage.setFileCount((short) fileList.size());
            getReplyMessage.setPathCollection(stringBuilder);
            Main.logger.info("获取文件列表名：" + stringBuilder.toString());
            Main.logger.info("获取文件列表长度：" + stringBuilder.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3、给用户发送获取应答消息
        ProFileRequest proFileRequest = new ProFileRequest();
        // 给其他用户发送文件更新消息。
        SendAddress sendAddress = GetSendAddress.init("udpsendconfig.json", "GXYD");
        if (sendAddress != null) {
            String ip = sendAddress.getGroupHost();
            int port = sendAddress.getPort();
            try {
                Message.getReplyMessage(emblHeader, getReplyMessage, stringBuilder.length() + 9, InetAddress.getByName(ip), port);
            } catch (UnknownHostException e) {
                Main.logger.error("归档回执发送失败3");
            }
        } else {
            Main.logger.error("归档回执发送失败4");
        }
    }

//    private void getYC(EMBLHeader emblHeader){
//        JSONObject jsonObject = JSONObject.parseObject(new String(emblHeader.getContent()));
//        QueryTelemetryParameters queryTelemetryParameters = GetDataParse.parseName(jsonObject);
//        List<QueryTelemetryParameters> parameters = parametersService.findAll(queryTelemetryParameters);
//
//        // 查询返回结果写入到CSV文件中
//        String[] csvHeaders = {"CodeID", "SignalGndTime", "SignalSatTime", "OriginalValue", "EngineerValue", "StateValue"};
//
//        String fileName = "pack_503_100b_s" + "_";
//        File tempFile = null;
//        try {
//            tempFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
//            if (!tempFile.exists()) {
//                tempFile.mkdirs();
//            }
//            System.out.println(tempFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 根据用户查询的是哪张表，确定文件名 2020年8月21日15:37:23
//        CsvUtil.writeCSV(parameters, tempFile.toString(), csvHeaders);
//
//        // 将查询结果写入Excel表格
//        try {
//            ExcelUtil.write(parameters, outPutPath + "遥测参数信息.xlsx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 将文件上传到FTP服务器
//
//        // 发送文件更新消息
//    }

    // 00220011/503/514/2020/08/<数据类型>_<业务类型>_<源地址>_<开始时间串>_<结束时间串>.txt
//    public static List<File> findFile(EMBLHeader emblHeader, QueryCondition queryCondition) {
//        List<File> fileList = new ArrayList<>();
//        DateTime startTime = null;
//        DateTime endTime = null;
//
//        if (queryCondition == null) {
//            return null;
//        }
//        if (queryCondition.getStart() == null) {
//            queryCondition.setStart(DateUtil.date(Convert.toDate("2010-01-01 00:00:00")));
//            startTime = queryCondition.getStart();
//        }
//        if (queryCondition.getEnd() == null) {
//            queryCondition.setEnd(DateUtil.date(System.currentTimeMillis()));
//            endTime = queryCondition.getEnd();
//        }
//        // 1、判断要获取的数据是符合接口规范的
//
//        // 2、判断开始时间是否小于结束时间
//        try {
//            // 如果获取文件的开始时间小于结束时间
//            if (startTime.getTime() < endTime.getTime()) {
//                // 3、考虑跨年和跨月的情况
//                // 根据时间获取要查找那些文件夹
//                List<String> list = getMonthBetween(queryCondition.getStart(), queryCondition.getEnd());
//
//                for (String s : list) {
//                    // 拼接文件夹
//                    String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator;
//                    File file = new File(filePath);
//                    if (file.exists() && file.isDirectory()) {
//                        File[] files = file.listFiles();
//                        for (File file1 : files) {
//                            if (file1.exists() && file.isDirectory()) {
//                                File[] files1 = file1.listFiles();
//                                for (File file2 : files1) {
//                                    String newFilePath = file2.toString() + File.separator + s + File.separator;
//                                    File file3 = new File(newFilePath);
//                                    if (file3.exists() && file3.isDirectory()) {
//                                        System.out.println(file3);
//                                        fileList.addAll(folderMethod(file3, startTime, endTime));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            } else {
//                // 非法获取情形
//                Main.logger.warn("时间非法，请检查!");
//            }
////        if (emblHeader == null || queryCondition == null) {
////            return null;
////        }
////        List<File> fileList = new ArrayList<>();
////        DateTime startTime = queryCondition.getStart();
////        DateTime endTime = queryCondition.getEnd();
////
////        // 1、判断要获取的数据是符合接口规范的
////
////        // ？？新需求，如果只告诉我数据类型，我要返回所有的文件。2020年9月28日18:02:24
////
////        // 2、判断开始时间是否小于结束时间
////        try {
////            // 如果获取文件的开始时间小于结束时间
////            if (startTime != null && endTime != null && startTime.getTime() <= endTime.getTime()) {
////                // 3、考虑跨年和跨月的情况
////                // 根据时间获取要查找那些文件夹
////                List<String> list = getMonthBetween(queryCondition.getStart(), queryCondition.getEnd());
////
////                for (String s : list) {
////                    // 拼接文件夹
////                    String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator + emblHeader.getTaskID() + File.separator + queryCondition.getStation() + File.separator + s + File.separator;
////
////                    Main.logger.info("查找文件的路径为：" + filePath);
////                    File file = new File(filePath);
////                    if (file.exists() && file.isDirectory()) {
////                        fileList.addAll(folderMethod(file, startTime, endTime));
////                    }
////                }
////            } else {
////                // 非法获取情形
////                Main.logger.warn("时间非法，请检查!");
////            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return fileList;
//    }

    public static List<File> findFile(EMBLHeader emblHeader, QueryCondition queryCondition) {
        Map<String, ArrayList> map = Main.EMBL;

        List<File> fileList = new ArrayList<>();
        DateTime startTime = new DateTime();
        DateTime endTime = new DateTime();

        String newFilePath = null;

        if (queryCondition == null) {
            return null;
        }
        if (queryCondition.getStart() == null) {
            queryCondition.setStart(DateUtil.date(Convert.toDate("2017-01-01 00:00:00")));
        }
        startTime = queryCondition.getStart();
        if (queryCondition.getEnd() == null) {
            queryCondition.setEnd(DateUtil.date(System.currentTimeMillis()));
        }
        endTime = queryCondition.getEnd();
        // 1、判断要获取的数据是符合接口规范的

        // 2、判断开始时间是否小于结束时间
        try {
            // 如果获取文件的开始时间小于结束时间
            if (startTime.getTime() <= endTime.getTime()) {
                // 3、考虑跨年和跨月的情况
                // 根据时间获取要查找那些文件夹
                List<String> list = getMonthBetween(queryCondition.getStart(), queryCondition.getEnd());

                for (String s : list) {
                    // 拼接文件夹
                    String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator;

                    // 1、判断星号是否为空
                    if (queryCondition.getSatelliteID() == null) {
                        queryCondition.setSatelliteID(map.get("taskID"));
                    }
                    // 2、判断站号是否为空
                    if (queryCondition.getStation() == null) {
                        queryCondition.setStation(map.get("station"));
                    }
                    File file = new File(filePath);
                    if (file.exists() && file.isDirectory()) {
                        List<Integer> satelliteID = queryCondition.getSatelliteID();
                        List<Integer> station = queryCondition.getStation();
                        for (int satelliteIDNum = 0; satelliteIDNum < satelliteID.size(); satelliteIDNum++) {
                            for (int stationNum = 0; stationNum < station.size(); stationNum++) {
                                if ((Integer) satelliteID.get(satelliteIDNum) != -1) {
                                    newFilePath = file.toString() + File.separator + satelliteID.get(satelliteIDNum) + File.separator + station.get(stationNum) + File.separator + s + File.separator;
                                } else {
                                    newFilePath = file.toString() + File.separator + "4294967295" + File.separator + station.get(stationNum) + File.separator + s + File.separator;
                                }
                                File newFile = new File(newFilePath);
                                if (newFile.exists() && newFile.isDirectory()) {
                                    System.out.println(newFile);
                                    fileList.addAll(folderMethod(newFile, startTime, endTime));
                                }
                            }
                        }
                    }
                }
            } else {
                // 非法获取情形
                Main.logger.warn("时间非法，请检查!");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public static List<File> folderMethod(File file, DateTime startTime, DateTime endTime) {
        List<File> list = new ArrayList<>();
        File[] files = file.listFiles();
        ProFileRequest proFileRequest = new ProFileRequest();
        for (File fileName : files) {
            // 获取文件基准时间
            DateTime dateTime = proFileRequest.getFileBaseTime(fileName.getName());
            if (dateTime.getTime() >= startTime.getTime() && dateTime.getTime() <= endTime.getTime()) {
                boolean flag = list.add(fileName);
                Main.logger.info("文件添加：" + flag);
            }
        }
        System.out.println("===================" + list);
        return list;
    }

    public static void showFile() {
        // 设置路径
        String path = "D:\\IdeaProjects\\centispacesg\\归档回执原始数据\\4d0001\\2020年\\08月\\20日\\21时";
        File file = new File(path);
        File[] files = file.listFiles();
        System.out.println("该目录下文件个数:" + files.length);
        // 当前时间
        System.out.println("当前时间:" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + '\n');
        // 输出结果
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                // 最后修改时间
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date(files[i].lastModified())));
                // 文件名
                System.out.println("文件:" + files[i]);
                sameDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("yyyy-MM-dd").format(new Date(files[i].lastModified())));
            }
        }
    }

    // 比较两个时间是否相等
    public static void sameDate(String d1, String d2) {
        System.out.println("时间是否相同:" + d1.equals(d2) + '\n');
    }

    public static void fileLastModified() {
        File file = new File("D:\\IdeaProjects\\centispacesg\\归档回执原始数据\\4d0001\\2020年\\08月\\20日\\21时\\2020-08-20 21-36.dat");
        if (file.exists()) {
            System.out.println("是否为文件：" + file.isFile());
            System.out.println("是否为目录：" + file.isDirectory());
            System.out.println("文件大小:" + new BigDecimal((double) file.length() / 1024 / 1024).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP) + "M");
            System.out.println("上次修改时间：" + new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
            System.out.println("上次修改时间:" + file.lastModified());
        }
    }

    public static List<String> getMonthBetween(DateTime start, DateTime end) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + File.separator + "MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(start);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(end);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }
}
