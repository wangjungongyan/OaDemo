package com.vali.enums.user;

/**
 * Created by vali on 15-8-14.
 */

/**
 * 员工角色
 */
public enum RoleEnum {

    NOMALR(0, "普通员工"), HR(1, "人事"),MANAGE(2,"经理"),BOSS(3,"总经理");

    private int type;

    private String roleName;

    RoleEnum(int type, String name) {
        this.type = type;
        this.roleName = name;
    }

    public int getType() {
        return type;
    }

    public String getRoleName() {
        return roleName;
    }
}
