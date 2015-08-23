package com.vali.dto.leave;

import com.vali.dto.common.QueryBaseDTO;
import lombok.Data;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveRevokeQueryDTO extends QueryBaseDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private int leaveType;

    private int auditUserId;

    public LeaveRevokeQueryDTO() {
    }

    @Override protected void setCondition() {

        StringBuffer query = new StringBuffer("&");

        if (startTime != null) {
            query.append("startTime=" + DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm:ss") + "&");
        }

        if (endTime != null) {
            query.append("endTime=" + DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss") + "&");
        }

        query.append("leaveType=" + leaveType);

        this.queryCondition = query.toString();
    }

}
