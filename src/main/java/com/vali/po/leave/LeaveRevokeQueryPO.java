package com.vali.po.leave;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveRevokeQueryPO {

    private int auditUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private int leaveType;

    public LeaveRevokeQueryPO() {
    }

}
