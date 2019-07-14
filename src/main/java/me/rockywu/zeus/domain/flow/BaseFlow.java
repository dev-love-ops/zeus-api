package me.rockywu.zeus.domain.flow;

import java.sql.Timestamp;

/**
 * @author RockyWu
 * @date 2019-01-21 21:24
 */
public class BaseFlow {
    /**
     * 工单ID
     */
    private String flowId;
    /**
     * 申请人
     */
    private String applyUser;
    /**
     * 审核人
     */
    private String verifyUser;
    /**
     * 处理人
     */
    private String operateUser;
    /**
     * 申请时间
     */
    private Timestamp applyTime;
    /**
     * 审核时间
     */
    private Timestamp verifyTime;
    /**
     * 结束时间
     */
    private Timestamp closeTime;
    /**
     * 审核失败原因
     */
    private String failedReason;
    /**
     * 工单状态
     * 0 待审核
     * 1 审核通过,待处理
     * 2 审核失败
     * 3 已完成
     * 4 已撤销
     */
    private int status;


    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public Timestamp getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Timestamp verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseFlow{" +
                "flowId='" + flowId + '\'' +
                ", applyUser='" + applyUser + '\'' +
                ", verifyUser='" + verifyUser + '\'' +
                ", operateUser='" + operateUser + '\'' +
                ", applyTime=" + applyTime +
                ", verifyTime=" + verifyTime +
                ", closeTime=" + closeTime +
                ", failedReason='" + failedReason + '\'' +
                ", status=" + status +
                '}';
    }
}
