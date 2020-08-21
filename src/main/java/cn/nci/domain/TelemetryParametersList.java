package cn.nci.domain;

import java.util.LinkedHashMap;

/**
 * @program: centispacesg
 * @description: 遥测参数信息
 * @author: xiejianfeng
 * @create: 2020-07-24 14:16
 */
public class TelemetryParametersList {

    private Integer satid;
    private String time;
    private Integer packid;
    private String tableName;
    private LinkedHashMap<String,TelemetryParameters> param;

    public TelemetryParametersList() {
    }

    public TelemetryParametersList(Integer satid, String time, Integer packid, String tableName, LinkedHashMap<String, TelemetryParameters> param) {
        this.satid = satid;
        this.time = time;
        this.packid = packid;
        this.tableName = tableName;
        this.param = param;
    }

    public Integer getSatid() {
        return satid;
    }

    public void setSatid(Integer satid) {
        this.satid = satid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPackid() {
        return packid;
    }

    public void setPackid(Integer packid) {
        this.packid = packid;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LinkedHashMap<String, TelemetryParameters> getParam() {
        return param;
    }

    public void setParam(LinkedHashMap<String, TelemetryParameters> param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "TelemetryParametersList{" +
                "satid=" + satid +
                ", time='" + time + '\'' +
                ", packid=" + packid +
                ", tableName='" + tableName + '\'' +
                ", param=" + param +
                '}';
    }
}
