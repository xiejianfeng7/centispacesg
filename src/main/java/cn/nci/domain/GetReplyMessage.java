package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 文件/数据获取应答消息
 * @author: xiejianfeng
 * @create: 2020-08-27 10:30
 */
public class GetReplyMessage {
    private Integer dataType;                   // 数据类型
    private Short message;                      // 消息标识
    private Byte replyFlag;                     // 应答标识
    private Short fileCount;                    // 获取文件数量
    private StringBuilder pathCollection;       // 路径集

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Short getMessage() {
        return message;
    }

    public void setMessage(Short message) {
        this.message = message;
    }

    public Byte getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Byte replyFlag) {
        this.replyFlag = replyFlag;
    }

    public Short getFileCount() {
        return fileCount;
    }

    public void setFileCount(Short fileCount) {
        this.fileCount = fileCount;
    }

    public StringBuilder getPathCollection() {
        return pathCollection;
    }

    public void setPathCollection(StringBuilder pathCollection) {
        this.pathCollection = pathCollection;
    }
}
