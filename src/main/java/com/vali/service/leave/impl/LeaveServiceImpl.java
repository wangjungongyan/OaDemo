package com.vali.service.leave.impl;

import com.vali.dao.leave.EmployeeHolidayDao;
import com.vali.dao.leave.LeaveApplyDao;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.service.leave.remote.LeaveService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/13.
 */

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

    @Setter
    @Resource(name = "employeeHolidayDao")
    private EmployeeHolidayDao employeeHolidayDao;

    @Setter
    @Resource(name = "leaveApplyDao")
    private LeaveApplyDao leaveApplyDao;

    private BeanCopier DTO2ENTITY4LeaveApply = BeanCopier.create(LeaveApplyDTO.class, LeaveApplyPO.class, false);

    private BeanCopier ENTITY2DTO4LeaveApply = BeanCopier.create(LeaveApplyPO.class, LeaveApplyDTO.class, false);

    private BeanCopier ENTITY2DTO4Holiday = BeanCopier.create(EmployeeHolidayPO.class, EmployeeHolidayDTO.class, false);

    @Override
    public int saveApplyDetail(LeaveApplyDTO applyDetail) {
        LeaveApplyPO po = new LeaveApplyPO();
        DTO2ENTITY4LeaveApply.copy(applyDetail, po, null);
        return leaveApplyDao.saveLeaveApplyDetail(po);
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

    @Override public List<LeaveApplyDTO> getApplyRecords(Integer applicantID, Integer leaveType, Date applyTime_begin,
                                                         Date applyTime_end) {
        return null;
    }

    @Override public List<LeaveApplyDTO> getApplyRecords(Integer applicantID) {

        List<LeaveApplyPO> pos = leaveApplyDao.getLeaveApplyRecords(applicantID);

        if (CollectionUtils.isEmpty(pos)) {
            return new ArrayList<LeaveApplyDTO>(1);
        }

        List<LeaveApplyDTO> dtos = new ArrayList<LeaveApplyDTO>(pos.size());

        for (LeaveApplyPO po : pos) {
            LeaveApplyDTO dto = new LeaveApplyDTO();
            ENTITY2DTO4LeaveApply.copy(po, dto, null);

            LeaveTypeEnum typeEnum =LeaveTypeEnum.getLeaveType(po.getLeaveType());
            if (typeEnum!=null){
                dto.setLeaveName(typeEnum.getName());
            }

            AuditStatusEnum auditStatus=AuditStatusEnum.getAuditStatus(po.getStatus());
            if (auditStatus!=null){
                dto.setStatusName(auditStatus.getAuditStatusName());
            }

            dtos.add(dto);
        }

        return dtos;
    }

    public List<LeaveApplyDTO> myApply(Integer applicantID, Integer leaveType, String leaveReason, Date applyTime_begin,
                                       Date applyTime_end) {
        return null;
    }

    @Override
    public LeaveApplyDTO applyDetail(Integer applyId) {
        return null;
    }

    @Override public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId) {

        List<EmployeeHolidayPO> pos = employeeHolidayDao.getEmployeeHoliday(employeeId);

        if (CollectionUtils.isEmpty(pos)) {
            return new ArrayList<EmployeeHolidayDTO>(1);
        }

        List<EmployeeHolidayDTO> dtos = new ArrayList<EmployeeHolidayDTO>(pos.size());

        for (EmployeeHolidayPO po : pos) {
            EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
            ENTITY2DTO4Holiday.copy(po, dto, null);
            LeaveTypeEnum leaveTypeEnum = LeaveTypeEnum.getLeaveType(po.getType());
            dto.setName(leaveTypeEnum.getName());
            dto.setDesc(leaveTypeEnum.getDesc());
            dtos.add(dto);
        }

        return dtos;
    }
}
