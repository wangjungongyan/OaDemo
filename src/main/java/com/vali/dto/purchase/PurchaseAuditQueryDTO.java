package com.vali.dto.purchase;

import com.vali.dto.common.QueryBaseDTO;
import lombok.Data;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class PurchaseAuditQueryDTO extends QueryBaseDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String applyUserName;

    private int applicantID;

    private int buyType;

    public PurchaseAuditQueryDTO() {
    }

    @Override protected void setCondition() {

        StringBuffer query = new StringBuffer("&");

        if (startTime != null) {
            query.append("startTime=" + DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm:ss") + "&");
        }

        if (endTime != null) {
            query.append("endTime=" + DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss") + "&");
        }

        query.append("applyUserName=" + applyUserName);
        query.append("buyType=" + buyType);

        this.queryCondition = query.toString();
    }

}
