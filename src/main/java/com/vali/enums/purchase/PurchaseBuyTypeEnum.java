package com.vali.enums.purchase;

/**
 * Created by vali on 15-9-17.
 */
public enum PurchaseBuyTypeEnum {

    ALL(0, "全部"), SELF(1, "自己购买"), IT(2, "IT购买");

    private int type;

    private String desc;

    PurchaseBuyTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static PurchaseBuyTypeEnum matchType(int type) {
        PurchaseBuyTypeEnum[] enums = PurchaseBuyTypeEnum.values();
        for (PurchaseBuyTypeEnum purchaseBuyTypeEnum : enums) {
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
