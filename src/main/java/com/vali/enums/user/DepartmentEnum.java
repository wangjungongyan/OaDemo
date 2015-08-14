package com.vali.enums.user;

/**
 * Created by vali on 15-8-14.
 */

/**
 * 部门
 */
public enum DepartmentEnum {

    HR(3,"HR");

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
