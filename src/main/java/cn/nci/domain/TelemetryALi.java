package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 遥测参数
 * @author: xiejianfeng
 * @create: 2020-08-11 14:18
 */
public class TelemetryALi {
    private String name;
    private String value;

    @Override
    public String toString() {
        return "TelemetryALi{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
