package cn.nci.domain;

import java.util.ArrayList;

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
    private String tableame;
    private ArrayList<TelemetryParameters> paramlist;

    public TelemetryParametersList() {
    }

    public TelemetryParametersList(Integer satid, String time, Integer packid, String tableame, ArrayList<TelemetryParameters> paramlist) {
        this.satid = satid;
        this.time = time;
        this.packid = packid;
        this.tableame = tableame;
        this.paramlist = paramlist;
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

    public String getTableame() {
        return tableame;
    }

    public void setTableame(String tableame) {
        this.tableame = tableame;
    }

    public ArrayList<TelemetryParameters> getParamlist() {
        return paramlist;
    }

    public void setParamlist(ArrayList<TelemetryParameters> paramlist) {
        this.paramlist = paramlist;
    }

    @Override
    public String toString() {
        return "TelemetryParametersList{" +
                "satid=" + satid +
                ", time='" + time + '\'' +
                ", packid=" + packid +
                ", tableame='" + tableame + '\'' +
                ", paramlist=" + paramlist +
                '}';
    }
}
