package com.vali.enums.leave;

/**
 * Created by fanshuai on 15/8/13.
 */
public enum AuditStatusEnum {

    APPLY(0,"提交申请"),

    PASS(1,"通过"),

    REJECT(2,"不通过");

    private int auditStatus;

    private String auditStatusName;

    private AuditStatusEnum(int auditStatus,String auditStatusName){
        this.auditStatus = auditStatus;
        this.auditStatusName = auditStatusName;
    }

}
