package com.vali.po.leave;

import lombok.Data;

import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveAuditPO {

    private Integer id;

    private Integer applyId;

    private Integer managerId;

    private Integer managerAuditStatus;

    private Date managerAuditTime;

    private String managerAuditSuggest;

    private Integer hrId;

    private Integer hrAuditStatus;

    private Date hrAuditTime;

    private String hrAuditSuggest;

    private Date addTime;

    private Date updateTime;
    
}
