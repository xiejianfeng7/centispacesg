package cn.nci.domain;

import java.sql.Timestamp;

/**
 * @program: centispacesg
 * @description: 查询遥测domain
 * @author: xiejianfeng
 * @create: 2020-08-21 12:11
 */
public class QueryTelemetryParameters {
    private String codeID;
    private Timestamp SignalGndTime;
    private Timestamp SignalSatTime;
    private Long originalValue;
    private String engineerValue;
    private String stateValue;

    public QueryTelemetryParameters() {
    }

    public QueryTelemetryParameters(String codeID, Timestamp signalGndTime, Timestamp signalSatTime, Long originalValue, String engineerValue, String stateValue) {
        this.codeID = codeID;
        SignalGndTime = signalGndTime;
        SignalSatTime = signalSatTime;
        this.originalValue = originalValue;
        this.engineerValue = engineerValue;
        this.stateValue = stateValue;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public Timestamp getSignalGndTime() {
        return SignalGndTime;
    }

    public void setSignalGndTime(Timestamp signalGndTime) {
        SignalGndTime = signalGndTime;
    }

    public Timestamp getSignalSatTime() {
        return SignalSatTime;
    }

    public void setSignalSatTime(Timestamp signalSatTime) {
        SignalSatTime = signalSatTime;
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
        return "QueryTelemetryParameters{" +
                "codeID='" + codeID + '\'' +
                ", SignalGndTime=" + SignalGndTime +
                ", SignalSatTime=" + SignalSatTime +
                ", originalValue=" + originalValue +
                ", engineerValue='" + engineerValue + '\'' +
                ", stateValue='" + stateValue + '\'' +
                '}';
    }
}
