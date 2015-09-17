package com.vali.enums.purchase;

/**
 * Created by vali on 15-9-17.
 */
public enum PurchaseBuyTypeEnum {

    SELF(0, "自己购买"), IT(1, "IT购买");

    private int type;

    private String desc;

    PurchaseBuyTypeEnum(int type, String desc) {
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
