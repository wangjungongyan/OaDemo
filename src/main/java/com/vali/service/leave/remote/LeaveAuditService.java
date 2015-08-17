package com.vali.service.leave.remote;

import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.enums.leave.AuditStatusEnum;

/**
 * Created by fanshuai on 15/8/13.
 */
public interface LeaveAuditService {

    /**
     * 经理审批
     *
     * @param manageId            经理ID
     * @param managerAuditSuggest 审批意见
     * @param auditStatus         审批状态
     * @return
     */
    boolean manageAudit(int manageId, String managerAuditSuggest, AuditStatusEnum auditStatus);

    /**
     * hr审批
     *
     * @param hrId           hr编号
     * @param hrAuditSuggest hr处理意见
     * @param auditStatus    hr审批状态
     * @return
     */
    boolean hrAudit(int hrId, String hrAuditSuggest, AuditStatusEnum auditStatus);

    /**
     * 根据申请ID查询审批链
     *
     * @param applyId
     * @return
     */
    LeaveAuditDTO getAuidtChain(int applyId);

    int saveAudit(LeaveAuditDTO dto);

    /**
     * 销假
     *
     * @param hrId    hr编号
     * @param applyId 申请编号
     * @return
     */
    boolean revoke(int hrId, int applyId);

}
