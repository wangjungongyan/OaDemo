package com.vali.enums.user;

/**
 * Created by vali on 15-8-14.
 */

/**
 * 员工角色
 */
public enum RoleEnum {

    NOMALR(0, "普通员工"), HR(1, "人事");

    private int type;

    private String name;

    RoleEnum(int type, String name) {
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
