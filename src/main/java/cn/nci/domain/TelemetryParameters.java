package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 遥测参数
 * @author: xiejianfeng
 * @create: 2020-08-11 14:18
 */
public class TelemetryParameters {
    private String csdh;
    private String name;
    private Long originalValue;
    private String engineerValue;
    private String stateValue;

    public TelemetryParameters() {
    }

    public TelemetryParameters(String csdn, String name, Long originalValue, String engineerValue, String stateValue) {
        this.csdh = csdh;
        this.name = name;
        this.originalValue = originalValue;
        this.engineerValue = engineerValue;
        this.stateValue = stateValue;
    }

    public String getCsdh() {
        return csdh;
    }

    public void setCsdh(String csdh) {
        this.csdh = csdh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Long originalValue) {
        this.originalValue = originalValue;
    }

    public String getEngineerValue() {
        return engineerValue;
    }

    public void setEngineerValue(String engineerValue) {
        this.engineerValue = engineerValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    @Override
    public String toString() {
        return "TelemetryParameters{" +
                "csdn='" + csdh + '\'' +
                ", name='" + name + '\'' +
                ", originalValue=" + originalValue +
                ", engineerValue='" + engineerValue + '\'' +
                ", stateValue='" + stateValue + '\'' +
                '}';
    }
}
