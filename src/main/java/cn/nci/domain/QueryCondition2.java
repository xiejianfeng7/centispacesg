package cn.nci.domain;

import cn.hutool.core.date.DateTime;

import java.util.List;

/**
 * @program: centispacesg
 * @description: 所有的查询条件
 * @author: xiejianfeng
 * @create: 2020-08-05 12:02
 */
public class QueryCondition2 {
    private Short message;              // 自定义4位数字，消息编号，与获取应答消息中的消息编号对应，只填1个
    private Integer dataType;           // 必填，只填1个

    private Integer satelliteID;  // 卫星标识
    private List<Integer> freq;         // 填“S”或者“X”
    private List<Integer> station;
    private List<Integer> source;       // 源地址
    private DateTime start;             // 开始时间
    private DateTime end;               // 结束时间
    private Integer newFile;            // 最新文件/数据
    private List<String> param;         // 只有获取或写入数据库数据时填写

    public Short getMessage() {
        return message;
    }

    public void setMessage(Short message) {
        this.message = message;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getSatelliteID() {
        return satelliteID;
    }

    public void setSatelliteID(Integer satelliteID) {
        this.satelliteID = satelliteID;
    }

    public List<Integer> getFreq() {
        return freq;
    }

    public void setFreq(List<Integer> freq) {
        this.freq = freq;
    }

    public List<Integer> getStation() {
        return station;
    }

    public void setStation(List<Integer> station) {
        this.station = station;
    }

    public List<Integer> getSource() {
        return source;
    }

    public void setSource(List<Integer> source) {
        this.source = source;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public Integer getNewFile() {
        return newFile;
    }

    public void setNewFile(Integer newFile) {
        this.newFile = newFile;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }
}
