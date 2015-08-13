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
    private Integer id;
    private Integer applicantID;
    private Integer leaveType;
    private Date leaveStartTime;
    private Date leaveEndTime;
    private BigDecimal leaveDays;
    private String leaveReason;
    private Date applyTime;
    private Date addTime;
    private Date updateTime;
}
