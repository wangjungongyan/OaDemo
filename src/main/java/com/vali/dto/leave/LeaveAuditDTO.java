package com.vali.dto.leave;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveAuditDTO {

    private Integer id;

    private Integer applyId;

    private Integer managerId;

    private Integer managerAuditStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date managerAuditTime;

    private String managerAuditSuggest;

    private Integer hrId;

    private Integer hrAuditStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hrAuditTime;

    private String hrAuditSuggest;

}
