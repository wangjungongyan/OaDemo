package com.vali.dto.leave;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveApplyQueryDTO {

    private int applicantID;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    private int leaveType;

    public LeaveApplyQueryDTO() {}

}
