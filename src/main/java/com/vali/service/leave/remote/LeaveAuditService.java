package com.vali.service.leave.remote;

import com.leya.idal.model.PageModel;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;

/**
 * Created by fanshuai on 15/8/13.
 */
public interface LeaveAuditService {

    /**
     * 经理审批
     *
     * @param applyId
     * @param managerAuditSuggest
     * @param auditStatus
     * @return
     */
    boolean manageAudit(int applyId, String managerAuditSuggest, int auditStatus);

    /**
     * hr审批
     *
     * @param applyId
     * @param hrAuditSuggest
     * @param auditStatus
     * @return
     */
    boolean hrAudit(int applyId, String hrAuditSuggest, int auditStatus);

    /**
     * 根据申请ID查询审批链
     *
     * @param applyId
     * @return
     */
    LeaveAuditDTO getAuditChain(int applyId);

    int saveAudit(LeaveAuditDTO dto);

    /**
     * 销假
     *
     * @param hrId    hr编号
     * @param applyId 申请编号
     * @return
     */
    boolean revoke(int hrId, int applyId);

    /**
     * 分页获取已审核的请假申请
     *
     * @param leaveAuditQueryDTO
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageModel pageAduitedApplys(LeaveAuditQueryDTO leaveAuditQueryDTO,
                                int pageNo,
                                int pageSize);

}
