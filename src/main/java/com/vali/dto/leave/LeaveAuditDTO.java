package com.vali.dto.leave;

import lombok.Data;

import java.util.Date;

/**
 * Created by fanshuai on 15/8/13.
 */
@Data
public class LeaveAuditDTO {
    private Integer id;//` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    private Integer applyId;//` int(11) NOT NULL COMMENT '申请ID',
    private Integer managerId;//` int(11) NOT NULL DEFAULT '0' COMMENT '审批经理Id',
    private Integer managerAuditStatus;//` int(11) NOT NULL DEFAULT '0' COMMENT '经理审批状态 0-未审批 1-审批通过 2-审批不通过',
    private Date managerAuditTime;//` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '经理审批时间',
    private String managerAuditSuggest;//` varchar(200) NOT NULL DEFAULT '' COMMENT '经理审批意见',
    private Integer hrId;//` int(11) NOT NULL DEFAULT '0' COMMENT '人事Id',
    private Integer hrAuditStatus;//` int(11) NOT NULL DEFAULT '0' COMMENT '人事审批状态 0-未审批 1-审批通过 2-审批不通过',
    private Date hrAuditTime;//` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '人事审批时间',
    private String hrAuditSuggest;//` varchar(200) NOT NULL DEFAULT '' COMMENT '人事审批意见',
    private Date addTime;//` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '添加时间',
    private Date updateTime;//` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '更新时间',
}
