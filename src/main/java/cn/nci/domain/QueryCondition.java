package cn.nci.domain;

import cn.hutool.core.date.DateTime;

import java.util.List;

/**
 * @program: centispacesg
 * @description: 所有的查询条件
 * @author: xiejianfeng
 * @create: 2020-08-05 12:02
 */
public class QueryCondition {
    private Integer message;
    private Integer dataType;
    private Integer StationFlag;
    private List<String> param;
    private Integer taskID;
    private Integer freq;
    private DateTime start;
    private DateTime end;
    private String source;
    private String eventType;
    private String eventNum;

    public QueryCondition() {
    }

    public QueryCondition(Integer message, Integer dataType, Integer stationFlag, List<String> param, Integer taskID, Integer freq, DateTime start, DateTime end, String source, String eventType, String eventNum) {
        this.message = message;
        this.dataType = dataType;
        StationFlag = stationFlag;
        this.param = param;
        this.taskID = taskID;
        this.freq = freq;
        this.start = start;
        this.end = end;
        this.source = source;
        this.eventType = eventType;
        this.eventNum = eventNum;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getStationFlag() {
        return StationFlag;
    }

    public void setStationFlag(Integer stationFlag) {
        StationFlag = stationFlag;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventNum() {
        return eventNum;
    }

    public void setEventNum(String eventNum) {
        this.eventNum = eventNum;
    }
}
