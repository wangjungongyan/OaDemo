package com.vali.service.leave.impl;

import com.vali.enums.leave.AuditStatusEnum;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.service.leave.remote.LeaveService;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/13.
 */
public class LeaveServiceImpl implements LeaveService {

    @Override
    public int saveApplyDetail(LeaveApplyDTO applyDetail) {
        return 0;
    }

    @Override
    public boolean updateApplyDetail(LeaveApplyDTO applyDetail) {
        return false;
    }

    @Override
    public boolean manageAudit(int manageId, String managerAuditSuggest, AuditStatusEnum auditStatus) {
        return false;
    }

    @Override
    public boolean hrAudit(int hrId, String hrAuditSuggest, AuditStatusEnum auditStatus) {
        return false;
    }

    @Override
    public boolean revoke(int hrId, int applyId) {
        return false;
    }

    @Override
    public List<LeaveApplyDTO> myApply(Integer applicantID, Integer leaveType, String leaveReason, Date applyTime_begin, Date applyTime_end) {
        return null;
    }

    @Override
    public LeaveApplyDTO applyDetail(Integer applyId) {
        return null;
    }
}
