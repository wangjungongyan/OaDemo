package com.vali.enums.user;

/**
 * Created by vali on 15-8-14.
 */

/**
 * 部门
 */
public enum DepartmentEnum {

    DEV(-1,"技术"),

    AT(1,"Automation Technical Design"),

    CM(2,"Contract Management"),

    FY(3,"Facility"),

    FS(4,"Field Service"),

    FA(5,"Finance"),

    FW(6,"Forwarding"),

    HR(7,"HR"),

    IT(8,"IT"),

    MOP(9,"Main Operations Planner"),

    MM(10,"Management"),

    NM(11,"NEA Management"),

    NOM(12,"NEA Operation Management"),

    OE(13,"Operation Engineering"),

    OM(14,"Operation Management"),

    OP(15,"Operation Purchase"),

    PT(16,"Production"),

    PH(17,"Purchase"),

    QC(18,"QC"),

    SM(19,"Sales-Merchant"),

    SO(20,"Sales-offshore"),

    SC(21,"Service Centre"),

    SSS(22,"Service Sales Support"),

    SW(23,"Service Workshop");

    private int type;

    private String name;

    DepartmentEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
