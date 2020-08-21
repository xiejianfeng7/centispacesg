package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 组播地址信息
 * @author: xiejianfeng
 * @create: 2020-07-28 13:35
 */
public class MulticastAddress {
    private String groupHost;
    private Integer port;
    private Integer isCreate;

    public MulticastAddress() {
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

    public Integer getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(Integer isCreate) {
        this.isCreate = isCreate;
    }

    @Override
    public String toString() {
        return "MulticastAddress{" +
                "groupHost='" + groupHost + '\'' +
                ", port=" + port +
                ", isCreate=" + isCreate +
                '}';
    }
}
