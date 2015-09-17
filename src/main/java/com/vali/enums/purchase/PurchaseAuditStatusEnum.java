package com.vali.enums.purchase;

/**
 * Created by vali on 15-9-17.
 */
public enum PurchaseAuditStatusEnum {

    AUDITING(0, "审核中"), PASS(1, "通过"),REJECT(2, "不通过");

    private int type;

    private String desc;

    PurchaseAuditStatusEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
