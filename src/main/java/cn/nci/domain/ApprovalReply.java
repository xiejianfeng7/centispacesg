package cn.nci.domain;

/**
 * @program: Approval
 * @description: 审批消息
 * @author: xiejianfeng
 * @create: 2020-12-09 15:02
 */
public class ApprovalReply {
    // 为14位数字，01/02+年月日（20201117）+编号（每天从0001开始）；01代表指令，02代表数据导入导出；
    private String messageID;
    // 审批结果
    private Integer approvalResult;
    // 拒绝人ID
    private Integer approvalRejectID;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Integer getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    public Integer getApprovalRejectID() {
        return approvalRejectID;
    }

    public void setApprovalRejectID(Integer approvalRejectID) {
        this.approvalRejectID = approvalRejectID;
    }
}
