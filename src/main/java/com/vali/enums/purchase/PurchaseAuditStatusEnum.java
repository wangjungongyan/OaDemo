package com.vali.enums.purchase;

/**
 * Created by vali on 15-9-17.
 */
public enum PurchaseAuditStatusEnum {

    AUDITING(1, "审核中"), PASS(2, "通过"), REJECT(3, "不通过");

    private int type;

    private String desc;

    PurchaseAuditStatusEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static PurchaseAuditStatusEnum match(int type) {
        PurchaseAuditStatusEnum[] enums = PurchaseAuditStatusEnum.values();
        for (PurchaseAuditStatusEnum purchaseAuditStatusEnum : enums) {
            if (purchaseAuditStatusEnum.getType() == type) {
                return purchaseAuditStatusEnum;
            }
        }

        return null;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
