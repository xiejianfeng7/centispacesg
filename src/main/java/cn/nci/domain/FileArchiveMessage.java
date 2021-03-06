package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 文件归档申请消息头
 * @author: xiejianfeng
 * @create: 2020-08-05 15:26
 */
public class FileArchiveMessage {
    private Integer dataType;       // 数据类型
    private Integer satID;          // 卫星标识
    private Byte stationFlag;       // 地面站标识
    private Short fileFlag;         // 归档文件标识
    private String filePath;        // 路径名

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getSatID() {
        return satID;
    }

    public void setSatID(Integer satID) {
        this.satID = satID;
    }

    public Byte getStationFlag() {
        return stationFlag;
    }

    public void setStationFlag(Byte stationFlag) {
        this.stationFlag = stationFlag;
    }

    public Short getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(Short fileFlag) {
        this.fileFlag = fileFlag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
