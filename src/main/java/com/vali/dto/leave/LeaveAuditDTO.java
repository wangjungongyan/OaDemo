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

    /**
     * 申请编号
     */
    private Integer applyId;

    /**
     * 上级id
     */
    private Integer managerId;

    /**
     * 上级审批状态
     */
    private Integer managerAuditStatus;

    /**
     * 上级审批时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date managerAuditTime;

    /**
     * 上级审批意见
     */
    private String managerAuditSuggest;

    /**
     * hr id
     */
    private Integer hrId;

    /**
     * hr审批状态
     */
    private Integer hrAuditStatus;

    /**
     * hr审批时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hrAuditTime;

    /**
     * hr审批意见
     */
    private String hrAuditSuggest;

}
