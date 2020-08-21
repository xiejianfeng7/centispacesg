package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 发送地址
 * @author: xiejianfeng
 * @create: 2020-08-21 10:29
 */
public class SendAddress {
    private String groupHost;
    private Integer port;

    public SendAddress() {
    }

    public SendAddress(String groupHost, Integer port) {
        this.groupHost = groupHost;
        this.port = port;
    }

    public String getGroupHost() {
        return groupHost;
    }

    public void setGroupHost(String groupHost) {
        this.groupHost = groupHost;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SendAddress{" +
                "groupHost='" + groupHost + '\'' +
                ", port=" + port +
                '}';
    }
}
