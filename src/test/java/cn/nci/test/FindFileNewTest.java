package cn.nci.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.nci.domain.GetReplyMessage;
import cn.nci.domain.QueryCondition;
import cn.nci.main.Main;
import cn.nci.parse.ProFileRequest;
import cn.nci.util.ByteStringUtil;
import cn.nci.util.FtpClientUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @program: centispacesg
 * @description: 查找文件
 * @author: xiejianfeng
 * @create: 2020-08-25 15:03
 */
public class FindFileNewTest {
    public static void main(String[] args) throws ParseException {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setDataType(0x00530002);
        // 1、根据用户查询时间查找文件
        List<File> fileList = findFile(queryCondition);

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

            }
            if (fileList.size() >= 1) {
                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
            }
//                clientUtil.uploadFtpFile(file.getParent(), file.getName(), "/");
            Byte flag = 1;
            getReplyMessage.setReplyFlag(flag);
            System.out.println(stringBuilder.toString());
            System.out.println(stringBuilder.length());
            clientUtil.close();
            System.out.println("共查到：" + fileList.size() + " 个文件，路径集长度为：" + stringBuilder.length() + " 个字节");
            getReplyMessage.setFileCount((short) fileList.size());
            getReplyMessage.setPathCollection(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 00220011/503/514/2020/08/<数据类型>_<业务类型>_<源地址>_<开始时间串>_<结束时间串>.txt
    public static List<File> findFile(QueryCondition queryCondition) {
        Map<String, ArrayList> map = Main.map;

        List<File> fileList = new ArrayList<>();
        DateTime startTime = null;
        DateTime endTime = null;

        String newFilePath = null;

        if (queryCondition == null) {
            return null;
        }
        if (queryCondition.getStart() == null) {
            queryCondition.setStart(DateUtil.date(Convert.toDate("2017-01-01 00:00:00")));
            startTime = queryCondition.getStart();
        }
        if (queryCondition.getEnd() == null) {
            queryCondition.setEnd(DateUtil.date(System.currentTimeMillis()));
            endTime = queryCondition.getEnd();
        }
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
                    String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator;

                    // 1、判断星号是否为空
                    if (queryCondition.getSatelliteID() == null) {
                        queryCondition.setSatelliteID(map.get("taskID"));
                    }
                    // 2、判断站号是否为空
                    if (queryCondition.getStation() == null) {
                        queryCondition.setStation(map.get("station"));
                    }
//                    System.out.println(queryCondition.getSatelliteID());
//                    System.out.println(queryCondition.getStation());
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
//                                System.out.println(newFilePath);
                                File newFile = new File(newFilePath);
                                if (newFile.exists() && newFile.isDirectory()) {
//                                    System.out.println(newFile);
                                    fileList.addAll(folderMethod(newFile, startTime, endTime));
                                }
                            }
                        }

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
                list.add(fileName);
            }
        }
        return list;
    }

    // 比较两个时间是否相等
    public static void sameDate(String d1, String d2) {
        System.out.println("时间是否相同:" + d1.equals(d2) + '\n');
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
