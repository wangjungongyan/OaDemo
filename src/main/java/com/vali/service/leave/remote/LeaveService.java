package com.vali.service.leave.remote;

import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.enums.leave.AuditStatusEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/13.
 */
public interface LeaveService {

    /**
     * 保存请假申请信息
     * @param applyDetail 请假申请的明细信息
     * @return 申请记录编号
     */
    int saveApplyDetail(LeaveApplyDTO applyDetail);

    /**
     * 修改请假申请信息
     * @param applyDetail 请假申请的明细信息
     * @return 成功true   失败false
     */
    boolean updateApplyDetail(LeaveApplyDTO applyDetail);

    /**
     *  经理审批
     * @param manageId 经理ID
     * @param managerAuditSuggest 审批意见
     * @param auditStatus 审批状态
     * @return
     */
    boolean manageAudit(int manageId,String managerAuditSuggest,AuditStatusEnum auditStatus);

    /**
     *  hr审批
     * @param hrId hr编号
     * @param hrAuditSuggest hr处理意见
     * @param auditStatus hr审批状态
     * @return
     */
    boolean hrAudit(int hrId,String hrAuditSuggest,AuditStatusEnum auditStatus);

    /**
     * 销假
     * @param hrId hr编号
     * @param applyId 申请编号
     * @return
     */
    boolean revoke(int hrId,int applyId);

    List<LeaveApplyDTO> getApplyRecords(Integer applicantID, Integer leaveType, Date applyTime_begin,
                                        Date applyTime_end);

    List<LeaveApplyDTO> getApplyRecords(Integer applicantID);

    /**
     * 请假详细信息
     * @param applyId
     * @return
     */
    LeaveApplyDTO applyDetail(Integer applyId);

    List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId);
}
