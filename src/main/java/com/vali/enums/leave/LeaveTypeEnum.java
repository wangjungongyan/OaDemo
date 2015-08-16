package com.vali.enums.leave;

/**
 * Created by vali on 15-8-13.
 */
public enum LeaveTypeEnum {

    DAIXINNIANJIA(1, "带薪年假", "每年可以有多天哦，我是带薪年假"),

    DAIXINBINGJIA(2, "带薪病假", "每年可以有多天哦，带薪病假"),

    SHIJIA(3, "事假", "每年可以有多天哦，事假"),

    HUNJIA(4, "婚假", "每年可以有多天哦，婚假"),

    SANGJIA(5, "丧假", "每年可以有多天哦，丧假"),

    PEICHANJIA(6, "陪产假", "每年可以有多天哦，陪产假"),

    OTHER(7, "其它", "每年可以有多天哦，其它");

    private int type;

    private String name;

    private String desc;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    LeaveTypeEnum(int type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public static LeaveTypeEnum getLeaveType(int type) {
        LeaveTypeEnum[] enums = LeaveTypeEnum.values();

        for (LeaveTypeEnum t : enums) {
            if (t.getType() == type) {
                return t;
            }
        }

        return null;
    }

}
