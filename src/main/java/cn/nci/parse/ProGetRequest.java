package cn.nci.parse;

import cn.nci.domain.EMBLHeader;
import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.service.QueryTelemetryParametersService;
import cn.nci.service.impl.QueryTelemetryParametersServiceImpl;
import cn.nci.util.CsvUtil;
import cn.nci.util.ExcelUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
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
    public void get(EMBLHeader emblHeader) {

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
}
