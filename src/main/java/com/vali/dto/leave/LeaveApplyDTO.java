package com.vali.dto.leave;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveApplyDTO {

    private Integer id;

    /**
     * 员工id
     */
    private Integer applicantID;

    /**
     * 请假类型
     */
    private Integer leaveType;

    private String leaveName;

    /**
     * 请假开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date leaveStartTime;

    /**
     * 请假结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date leaveEndTime;

    /**
     * 请假天数
     */
    private BigDecimal leaveDays;

    /**
     * 请假理由
     */
    private String leaveReason;

    /**
     * 申请时间
     */
    private Date applyTime;

    private int status;

    private String statusName;

    /**
     * 审批详情
     */
    private LeaveAuditDTO audit;

    private Date addTime;

    private Date updateTime;

}
