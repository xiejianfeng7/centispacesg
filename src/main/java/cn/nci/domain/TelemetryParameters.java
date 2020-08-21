package cn.nci.domain;

/**
 * @program: centispacesg
 * @description: 遥测参数
 * @author: xiejianfeng
 * @create: 2020-08-11 14:18
 */
public class TelemetryParameters {
    private Long originalValue;
    private String engineerValue;
    private String stateValue;

    public TelemetryParameters() {
    }

    public TelemetryParameters(Long originalValue, String engineerValue, String stateValue) {
        this.originalValue = originalValue;
        this.engineerValue = engineerValue;
        this.stateValue = stateValue;
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
                "originalValue=" + originalValue +
                ", engineerValue='" + engineerValue + '\'' +
                ", stateValue='" + stateValue + '\'' +
                '}';
    }
}
