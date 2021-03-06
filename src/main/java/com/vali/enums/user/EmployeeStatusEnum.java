package com.vali.enums.user;

/**
 * Created by vali on 15-8-14.
 */

/**
 * 员工状态
 */
public enum EmployeeStatusEnum {

    ON(0, "在职"), LEAVE(1, "离职");

    private int status;

    private String desc;

    EmployeeStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
