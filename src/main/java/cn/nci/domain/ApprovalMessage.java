package cn.nci.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: Approval
 * @description: 审批消息
 * @author: xiejianfeng
 * @create: 2020-12-09 15:02
 */
public class ApprovalMessage {
    // 为14位数字，01/02+年月日（20201117）+编号（每天从0001开始）；01代表指令，02代表数据导入导出；
    private String MessageID;
    // 操作员
    private String User;
    // 卫星号
    private String SatID;
    // 指令代码
    private String InstrID;
    // 收件人
    private ArrayList<HashMap<String, String>> approver;
    // 发件人
    private String applicant;
    // 内容在页面编辑。
    private String content;

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getSatID() {
        return SatID;
    }

    public void setSatID(String satID) {
        SatID = satID;
    }

    public String getInstrID() {
        return InstrID;
    }

    public void setInstrID(String instrID) {
        InstrID = instrID;
    }

    public ArrayList<HashMap<String, String>> getApprover() {
        return approver;
    }

    public void setApprover(ArrayList<HashMap<String, String>> approver) {
        this.approver = approver;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
