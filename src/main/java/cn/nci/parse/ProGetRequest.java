package cn.nci.parse;

import cn.hutool.core.date.DateTime;
import cn.nci.domain.*;
import cn.nci.service.QueryTelemetryParametersService;
import cn.nci.service.impl.QueryTelemetryParametersServiceImpl;
import cn.nci.socket.GetSendAddress;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.CsvUtil;
import cn.nci.util.ExcelUtil;
import cn.nci.util.FtpClientUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: centispacesg
 * @description: 处理获取申请
 * @author: xiejianfeng
 * @create: 2020-08-05 11:53
 */
public class ProGetRequest {
    private QueryTelemetryParametersService parametersService = new QueryTelemetryParametersServiceImpl();
    private String outPutPath = "D://";

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
        // 1、根据用户查询时间查找文件
        List<File> fileList = findFile(emblHeader, queryCondition);

        // 2、将查找的文件上传到指定的FTP地址
        FtpClientUtil clientUtil = null;
        StringBuilder stringBuilder = new StringBuilder();
        GetReplyMessage getReplyMessage = new GetReplyMessage();
        getReplyMessage.setDataType(queryCondition.getDataType());
        getReplyMessage.setMessage(queryCondition.getMessage());
        try {
            clientUtil = FtpClientUtil.getInstance("src/main/resources/ftpconfig.json", "sjgl");
            String ftpHost = "ftp://" + clientUtil.ftpHost + "/";
            for (File file : fileList) {
                // 此处连接FTP次数与数据文件个数成正比，要优化 2020年8月25日17:54:36
//                System.out.println(file.getParent());
//                System.out.println(file.getName());
//                System.out.println(file.getAbsolutePath());
                stringBuilder.append(ftpHost + file.getName() + ";");
                // 删除最后一个;号
                if (fileList.size()>=1){
                    stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
                }
                clientUtil.uploadFtpFile(file.getParent(), file.getName(), "/");
                Byte flag = 1;
                getReplyMessage.setReplyFlag(flag);
            }
            clientUtil.close();
            System.out.println("共查到：" + fileList.size() + "个文件");
            getReplyMessage.setFileCount((short) fileList.size());
            getReplyMessage.setPathCollection(stringBuilder);
            System.out.println(stringBuilder.toString());
            System.out.println(stringBuilder.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3、给用户发送获取应答消息
        ProFileRequest proFileRequest = new ProFileRequest();
        // 给其他用户发送文件更新消息。
        SendAddress sendAddress = GetSendAddress.init("src/main/resources/udpsendconfig.json", "GBDZ");
        if (sendAddress != null){
            String ip = sendAddress.getGroupHost();
            int port = sendAddress.getPort();
            try {
                Message.getReplyMessage(emblHeader, getReplyMessage, stringBuilder.length()+9, InetAddress.getByName(ip), port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("归档回执发送失败");
        }
    }

    private void getYC(EMBLHeader emblHeader){
        JSONObject jsonObject = JSONObject.parseObject(new String(emblHeader.getContent()));
        QueryTelemetryParameters queryTelemetryParameters = GetDataParse.parseName(jsonObject);
        List<QueryTelemetryParameters> parameters = parametersService.findAll(queryTelemetryParameters);

        // 查询返回结果写入到CSV文件中
        String[] csvHeaders = {"CodeID", "SignalGndTime", "SignalSatTime", "OriginalValue", "EngineerValue", "StateValue"};

        String fileName = "pack_503_100b_s" + "_";
        File tempFile = null;
        try {
            tempFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            System.out.println(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 根据用户查询的是哪张表，确定文件名 2020年8月21日15:37:23
        CsvUtil.writeCSV(parameters, tempFile.toString(), csvHeaders);

        // 将查询结果写入Excel表格
        try {
            ExcelUtil.write(parameters, outPutPath + "遥测参数信息.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将文件上传到FTP服务器

        // 发送文件更新消息
    }

    // 00220011/503/514/2020/08/<数据类型>_<业务类型>_<源地址>_<开始时间串>_<结束时间串>.txt
    public static List<File> findFile(EMBLHeader emblHeader, QueryCondition queryCondition) {
        List<File> fileList = new ArrayList<>();
        DateTime startTime = queryCondition.getStart();
        DateTime endTime = queryCondition.getEnd();

        // 1、判断要获取的数据是符合接口规范的

        // 2、判断开始时间是否小于结束时间
        try {
            // 如果获取文件的开始时间小于结束时间
            if (startTime.getTime() < endTime.getTime()) {
                // 3、考虑跨年和跨月的情况
                // 根据时间获取要查找那些文件夹
                List<String> list = getMonthBetween(queryCondition.getStart(), queryCondition.getEnd());

                for (String s : list) {
                    // 拼接文件夹
                    String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator + emblHeader.getTaskID() + File.separator + queryCondition.getStationFlag() + File.separator + s + File.separator;
                    File file = new File(filePath);
                    if (file.exists() && file.isDirectory()) {
                        fileList.addAll(folderMethod(file, startTime, endTime));
                    }
                }

            } else {
                // 非法获取情形
                System.out.println("时间非法，请检查!");
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
                list.add(fileName);
            }
        }
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
