package com.vali.enums.leave;

/**
 * Created by fanshuai on 15/8/13.
 */
public enum LeaveAuditStatusEnum {

    AUDITING(0, "审核中"),

    PASS(1, "通过"),

    REJECT(2, "不通过"),

    REOVKE(3, "销假");

    private int auditStatus;

    private String auditStatusName;

    private LeaveAuditStatusEnum(int auditStatus, String auditStatusName) {
        this.auditStatus = auditStatus;
        this.auditStatusName = auditStatusName;
    }

    public static LeaveAuditStatusEnum getAuditStatus(int auditStatus) {

        LeaveAuditStatusEnum[] statuss = LeaveAuditStatusEnum.values();

        for (LeaveAuditStatusEnum s : statuss) {
            if (s.auditStatus == auditStatus) {
                return s;
            }
        }
        return null;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }
}
