package com.vali.po.leave;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveApplyPO implements Serializable {
    Integer id;
    Integer applicantID;
    Integer leaveType;
    Date leaveStartTime;
    Date leaveEndTime;
    BigDecimal leaveDays;
    String leaveReason;
    Date applyTime;
    Date addTime;
    Date updateTime;
}
