package com.vali.dto.leave;

import com.vali.dto.common.QueryBaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveApplyQueryDTO extends QueryBaseDTO {

    private int applicantID;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private int leaveType;

    public LeaveApplyQueryDTO() {
    }

    @Override protected void setCondition() {

        StringBuffer query = new StringBuffer("&");

        if (startTime != null) {
            query.append("startTime=" + startTime + "&");
        }

        if (endTime != null) {
            query.append("endTime=" + endTime + "&");
        }

        query.append("leaveType=" + leaveType);

        this.queryCondition = query.toString();
    }

}
