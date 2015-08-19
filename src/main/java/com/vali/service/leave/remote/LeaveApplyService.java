package com.vali.service.leave.remote;

import com.leya.idal.model.PageModel;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveApplyQueryDTO;
import com.vali.enums.leave.AuditStatusEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/13.
 */
public interface LeaveApplyService {

    /**
     * 保存请假申请信息
     *
     * @param applyDetail 请假申请的明细信息
     * @return 申请记录编号
     */
    int saveApplyDetail(LeaveApplyDTO applyDetail);

    /**
     * 修改请假申请信息
     *
     * @param applyId
     * @param auditStatus
     * @return
     */
    boolean updateApplyStatus(int applyId, int auditStatus);

    List<LeaveApplyDTO> getApplyRecords(Integer applicantID, Integer leaveType, Date applyTime_begin,
                                        Date applyTime_end);

    List<LeaveApplyDTO> getApplyRecords(Integer applicantID);

    public PageModel getApplyRecords(LeaveApplyQueryDTO dto, int pageNo, int pageSize);

    /**
     * 请假详细信息
     *
     * @param applyId
     * @return
     */
    LeaveApplyDTO getApplyDetailByApplyId(Integer applyId);

    PageModel getApplysByRoleAndAuditId(int auditId, AuditStatusEnum auditStatus, AuditStatusEnum applyStatus,
                                        int pageNo,
                                        int pageSize);

}
