package com.vali.po.leave;

import lombok.Data;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveAuditQueryPO {

    private int auditUserId;

    private Date startTime;

    private Date endTime;

    private int leaveType;

    private int role;
}
