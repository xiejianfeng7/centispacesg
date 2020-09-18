package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: EMBL包头
 * @author: xiejianfeng
 * @create: 2020-07-23 13:37
 */
public class EMBLHeader {
    private Long taskID;
    private Integer dataTypeID;
    private Integer deviceID;
    private Integer date;
    private Integer time;
    private Integer res1;
    private Integer dataLength;
    private byte[] content;

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public Integer getDataTypeID() {
        return dataTypeID;
    }

    public void setDataTypeID(Integer dataTypeID) {
        this.dataTypeID = dataTypeID;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getRes1() {
        return res1;
    }

    public void setRes1(Integer res1) {
        this.res1 = res1;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
