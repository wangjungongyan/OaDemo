package com.vali.service.leave.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.leave.LeaveAuditDao;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.LeaveAuditStatusEnum;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.po.leave.LeaveAuditPO;
import com.vali.po.leave.LeaveAuditQueryPO;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.user.remote.EmployeeService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    private BeanCopier DTO2ENTITY4LeaveAuditQuery = BeanCopier.create(LeaveAuditQueryDTO.class, LeaveAuditQueryPO.class,
                                                                      false);

    private BeanCopier PAGECopier = BeanCopier.create(PageModel.class, PageModel.class, false);

    private BeanCopier ENTITY2DTO4LeaveApply = BeanCopier.create(LeaveApplyPO.class, LeaveApplyDTO.class, false);

    @Override public boolean manageAudit(int applyId, String managerAuditSuggest, int auditStatus) {
        int updated = leaveAuditDao.updateStatusAndSuggest4Manager(applyId, managerAuditSuggest, auditStatus);
        return (updated >= 1);
    }

    @Override public boolean hrAudit(int applyId, String hrAuditSuggest, int auditStatus) {
        int updated = leaveAuditDao.updateStatusAndSuggest4Hr(applyId, hrAuditSuggest, auditStatus);
        return (updated >= 1);
    }

    @Override public LeaveAuditDTO getAuditChain(int applyId) {

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

    @Override public PageModel pageAduitedApplys(LeaveAuditQueryDTO leaveAuditQueryDTO, int pageNo, int pageSize) {

        LeaveAuditQueryPO queryPO = new LeaveAuditQueryPO();
        DTO2ENTITY4LeaveAuditQuery.copy(leaveAuditQueryDTO, queryPO, null);

        queryPO.setRole(employeeService.getRoleByApplicantID(leaveAuditQueryDTO.getAuditUserId()).getType());

        PageModel pageModel = leaveAuditDao.pageAduitedApplys(queryPO, pageNo, pageSize);

        PageModel result = new PageModel();
        PAGECopier.copy(pageModel, result, null);

        List<LeaveApplyPO> pos = (List<LeaveApplyPO>) pageModel.getRecords();

        if (pos == null || pos.size() == 0) {
            return result;
        }

        List<LeaveApplyDTO> dtos = new ArrayList<LeaveApplyDTO>(pos.size());

        for (LeaveApplyPO po : pos) {
            LeaveApplyDTO dto = new LeaveApplyDTO();
            ENTITY2DTO4LeaveApply.copy(po, dto, null);
            dto.setLeaveName(LeaveTypeEnum.getLeaveType(dto.getLeaveType()).getName());
            dto.setApplicant(employeeService.loadEmployee(dto.getApplicantID()));
            dto.setStatusName(LeaveAuditStatusEnum.getAuditStatus(dto.getStatus()).getAuditStatusName());
            dtos.add(dto);
        }

        result.setRecords(dtos);

        return result;

    }
}
