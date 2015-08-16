package com.vali.po.leave;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveApplyPO {

    private Integer id;

    private Integer applicantID;

    private Integer leaveType;

    private Date leaveStartTime;

    private Date leaveEndTime;

    private BigDecimal leaveDays;

    private String leaveReason;

    private Date applyTime;

    private int status;

    private Date addTime;

    private Date updateTime;

}
