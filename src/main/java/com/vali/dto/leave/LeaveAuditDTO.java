package com.vali.dto.leave;

import lombok.Data;

import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveAuditDTO {

    private int id;

    private int applyId;

    private int managerId;

    private int managerAuditStatus;

    private Date managerAuditTime;

    private String managerAuditSuggest;

    private Integer hrId;

    private Integer hrAuditStatus;

    private Date hrAuditTime;

    private String hrAuditSuggest;

    private Date addTime;

    private Date updateTime;

}
