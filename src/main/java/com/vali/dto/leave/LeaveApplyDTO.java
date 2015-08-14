package com.vali.dto.leave;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveApplyDTO{

    private int id;

    private int applicantID;

    private int leaveType;

    private Date leaveStartTime;

    private Date leaveEndTime;

    private BigDecimal leaveDays;

    private String leaveReason;

    private Date applyTime;

    private Date addTime;

    private Date updateTime;

    private LeaveAuditDTO audit;

}