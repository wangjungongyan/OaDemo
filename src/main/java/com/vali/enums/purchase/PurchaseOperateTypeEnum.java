package com.vali.enums.purchase;

/**
 * Created by vali on 15-9-17.
 */
public enum PurchaseOperateTypeEnum {

    COMMIT_MANAGER(1, "提交主管"), MANAGER_PASS(2, "主管审批通过"), MANAGER_REJECT(3, "主管审批退回"), CANCEL(4, "作废");

    private int type;

    private String desc;

    PurchaseOperateTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static PurchaseOperateTypeEnum matchType(int type) {
        PurchaseOperateTypeEnum[] enums = PurchaseOperateTypeEnum.values();
        for (PurchaseOperateTypeEnum purchaseBuyTypeEnum : enums) {
            if (purchaseBuyTypeEnum.getType() == type) {
                return purchaseBuyTypeEnum;
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
