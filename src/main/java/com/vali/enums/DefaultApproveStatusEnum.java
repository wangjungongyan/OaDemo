package com.vali.enums;

/**
 * Created by fanshuai on 15/9/17.
 */
public enum DefaultApproveStatusEnum {
    DEFAULT(0,"待审批"),
    DOING(1,"审批中"),
    REJECT(2,"退回"),
    PASSED(3,"通过");
    int statusCode;
    String statusName;
    DefaultApproveStatusEnum(int statusCode,String statusName){
        this.statusCode=statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public static DefaultApproveStatusEnum getApproveStatusEnum(int statusCode){
        for(DefaultApproveStatusEnum defaultApproveStatusEnum:DefaultApproveStatusEnum.values()){
            if(defaultApproveStatusEnum.getStatusCode()==statusCode){
                return defaultApproveStatusEnum;
            }
        }
        return null;
    }

}
