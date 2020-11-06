package cn.nci.domain;

import java.util.ArrayList;

/**
 * @program: centispacesg
 * @description: 遥测参数信息
 * @author: xiejianfeng
 * @create: 2020-07-24 14:16
 */
public class TelemetryALiList {
    private String tablename;
    private String opr;
    private ArrayList<TelemetryALi> paramlist;

    @Override
    public String toString() {
        return "TelemetryALiList{" +
                "tablename='" + tablename + '\'' +
                ", opr='" + opr + '\'' +
                ", paramlist=" + paramlist +
                '}';
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getOpr() {
        return opr;
    }

    public void setOpr(String opr) {
        this.opr = opr;
    }

    public ArrayList<TelemetryALi> getParamlist() {
        return paramlist;
    }

    public void setParamlist(ArrayList<TelemetryALi> paramlist) {
        this.paramlist = paramlist;
    }
}
