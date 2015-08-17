package com.vali.service.leave.impl;

import com.vali.dao.leave.LeaveAuditDao;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.po.leave.LeaveAuditPO;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.user.remote.EmployeeService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by vali on 15-8-16.
 */
@Service("leaveAuditService")
public class LeaveAuditServiceImpl implements LeaveAuditService {

    @Setter
    @Resource(name = "leaveAuditDao")
    private LeaveAuditDao leaveAuditDao;

    @Setter
    @Resource(name = "employeeService")
    protected EmployeeService employeeService;

    private BeanCopier ENTITY2DTO4LeaveAudit = BeanCopier.create(LeaveAuditPO.class, LeaveAuditDTO.class, false);

    private BeanCopier DTO2ENTITY4LeaveAudit = BeanCopier.create(LeaveAuditDTO.class, LeaveAuditPO.class, false);

    @Override public boolean manageAudit(int manageId, String managerAuditSuggest, AuditStatusEnum auditStatus) {
        return false;
    }

    @Override public boolean hrAudit(int hrId, String hrAuditSuggest, AuditStatusEnum auditStatus) {
        return false;
    }

    @Override public LeaveAuditDTO getAuidtChain(int applyId) {

        LeaveAuditPO po = leaveAuditDao.getAuidtByApplyId(applyId);

        if (po == null) {
            return null;
        }

        int mangerId = po.getManagerId();
        EmployeeDTO manger = employeeService.loadEmployee(mangerId);

        int hrId = po.getHrId();
        EmployeeDTO hr = employeeService.loadEmployee(hrId);

        LeaveAuditDTO dto = new LeaveAuditDTO();
        ENTITY2DTO4LeaveAudit.copy(po, dto, null);
        dto.setManager(manger);
        dto.setHr(hr);

        return dto;
    }

    @Override public int saveAudit(LeaveAuditDTO dto) {

        if (dto == null) {
            return 0;
        }

        LeaveAuditPO po = new LeaveAuditPO();
        DTO2ENTITY4LeaveAudit.copy(dto, po, null);

        return leaveAuditDao.saveAudit(po);

    }

    @Override public boolean revoke(int hrId, int applyId) {
        return false;
    }
}
