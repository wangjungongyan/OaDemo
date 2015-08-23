package com.vali.enums.leave;

/**
 * Created by fanshuai on 15/8/13.
 */
public enum AuditStatusEnum {

    AUDITING(0, "审核中"),

    PASS(1, "通过"),

    REJECT(2, "不通过"),

    REOVKE(3, "销假");

    private int auditStatus;

    private String auditStatusName;

    private AuditStatusEnum(int auditStatus, String auditStatusName) {
        this.auditStatus = auditStatus;
        this.auditStatusName = auditStatusName;
    }

    public static AuditStatusEnum getAuditStatus(int auditStatus) {

        AuditStatusEnum[] statuss = AuditStatusEnum.values();

        for (AuditStatusEnum s : statuss) {
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
