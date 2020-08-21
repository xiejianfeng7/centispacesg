package cn.nci.util;

import cn.nci.domain.QueryTelemetryParameters;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mysqltocsv
 * @description: 历经测试
 * @author: xiejianfeng
 * @create: 2020-08-21 11:57
 */
public class CSVUtils {
    /**
     * 功能描述:
     * @Param: [exportData, outPutPath, fileName]
     * 源数据List
     * 文件路径
     * 文件名称
     * @Return: java.io.File
     * @Author: john
     * @Date: 2020/8/21 12:04
     */
    public File createCSVFile(List<List<String>> exportData, String outPutPath, String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("创建成功");
                } else {
                    System.out.println("创建失败");
                }
            }
            //定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), StandardCharsets.UTF_8), 1024);
            for (List<String> exportDatum : exportData) {
                writeRow(exportDatum, csvFileOutputStream);
                csvFileOutputStream.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvFileOutputStream != null) {
                    csvFileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据
     *
     * @param row       数据列表
     * @param csvWriter
     * @throws IOException
     */
    private void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException {
        int i = 0;
        for (String data : row) {
            csvWriter.write(DelQuota(data));
            if (i != row.size() - 1) {
                csvWriter.write(",");
            }
            i++;
        }
    }

    /**
     * 剔除特殊字符
     *
     * @param str 数据
     */
    public String DelQuota(String str) {
        String result = str;
        String[] strQuota = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", ".", "/", ":", "/,", "<", ">", "?"};
        for (int i = 0; i < strQuota.length; i++) {
            if (result.indexOf(strQuota[i]) > -1)
                result = result.replace(strQuota[i], "");
        }
        return result;
    }


    /**
     * 测试
     */
    public static void main(String[] args) throws IOException {
        CSVUtils csvUtils = new CSVUtils();
        QueryTelemetryParameters q1 = new QueryTelemetryParameters("Wcount", Timestamp.valueOf("2020-08-20 15:21:50"), Timestamp.valueOf("2017-01-08 00:00:00"),0L,"0","0");

        QueryTelemetryParameters q2 = new QueryTelemetryParameters("Wcount2", Timestamp.valueOf("2020-08-21 15:21:50"), Timestamp.valueOf("2017-01-09 00:00:00"),0L,"0","0");
        List<QueryTelemetryParameters> list = new ArrayList<>();
        list.add(q1);
        list.add(q2);
        String[] csvHeaders = {"CodeID","SignalGndTime","SignalSatTime","OriginalValue","EngineerValue","StateValue"};
        CsvUtil.writeCSV(list,"D://test111.csv",csvHeaders);
        ExcelUtil.write(list,"D://遥测参数信息.xlsx");
    }
}
