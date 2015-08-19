package com.vali.service.leave.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.leave.LeaveApplyDao;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveApplyQueryDTO;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.enums.user.RoleEnum;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.po.leave.LeaveApplyQueryPO;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.user.remote.EmployeeService;
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

@Service("leaveApplyService")
public class LeaveApplyServiceImpl implements LeaveApplyService {

    @Setter
    @Resource(name = "leaveApplyDao")
    private LeaveApplyDao leaveApplyDao;

    @Setter
    @Resource(name = "leaveAuditService")
    private LeaveAuditService leaveAuditService;

    @Setter
    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    private BeanCopier DTO2ENTITY4LeaveApply = BeanCopier.create(LeaveApplyDTO.class, LeaveApplyPO.class, false);

    private BeanCopier PAGECopier = BeanCopier.create(PageModel.class, PageModel.class, false);

    private BeanCopier DTO2ENTITY4LeaveApplyQuery = BeanCopier.create(LeaveApplyQueryDTO.class, LeaveApplyQueryPO.class,
                                                                      false);

    private BeanCopier ENTITY2DTO4LeaveApply = BeanCopier.create(LeaveApplyPO.class, LeaveApplyDTO.class, false);

    @Override
    public int saveApplyDetail(LeaveApplyDTO applyDetail) {
        LeaveApplyPO po = new LeaveApplyPO();
        DTO2ENTITY4LeaveApply.copy(applyDetail, po, null);
        return leaveApplyDao.saveLeaveApplyDetail(po);
    }

    @Override
    public boolean updateApplyStatus(int applyId, int auditStatus) {
        int updated = leaveApplyDao.updateApplyStatus(applyId, auditStatus);
        return (updated >= 1);
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

            LeaveTypeEnum typeEnum = LeaveTypeEnum.getLeaveType(po.getLeaveType());
            if (typeEnum != null) {
                dto.setLeaveName(typeEnum.getName());
            }

            AuditStatusEnum auditStatus = AuditStatusEnum.getAuditStatus(po.getStatus());
            if (auditStatus != null) {
                dto.setStatusName(auditStatus.getAuditStatusName());
            }

            LeaveAuditDTO auditDTO = leaveAuditService.getAuditChain(po.getId());
            dto.setAudit(auditDTO);

            dtos.add(dto);
        }

        return dtos;
    }

    @Override public PageModel getApplyRecords(LeaveApplyQueryDTO queryDTO, int pageNo, int pageSize) {

        LeaveApplyQueryPO queryPO = new LeaveApplyQueryPO();
        DTO2ENTITY4LeaveApplyQuery.copy(queryDTO, queryPO, null);
        PageModel queryModel = leaveApplyDao.pageLeaveApplyRecords(queryPO, pageNo, pageSize);

        PageModel result = new PageModel();
        PAGECopier.copy(queryModel, result, null);

        if (queryModel == null || CollectionUtils.isEmpty(queryModel.getRecords())) {
            return result;
        }

        List<LeaveApplyDTO> resultDTOS = new ArrayList<LeaveApplyDTO>(10);

        for (LeaveApplyPO record : (List<LeaveApplyPO>) queryModel.getRecords()) {
            LeaveApplyDTO dto = new LeaveApplyDTO();
            ENTITY2DTO4LeaveApply.copy(record, dto, null);

            int leaveType = record.getLeaveType();
            LeaveTypeEnum leaveTypeEnum = LeaveTypeEnum.getLeaveType(leaveType);
            dto.setLeaveName(leaveTypeEnum.getName());

            int status = record.getStatus();
            AuditStatusEnum auditStatusEnum = AuditStatusEnum.getAuditStatus(status);
            dto.setStatusName(auditStatusEnum.getAuditStatusName());

            resultDTOS.add(dto);
        }

        result.setRecords(resultDTOS);

        return result;
    }

    public List<LeaveApplyDTO> myApply(Integer applicantID, Integer leaveType, String leaveReason, Date applyTime_begin,
                                       Date applyTime_end) {
        return null;
    }

    @Override
    public LeaveApplyDTO getApplyDetailByApplyId(Integer applyId) {

        LeaveApplyPO po = leaveApplyDao.getApplyDetailByApplyId(applyId);

        if (po == null) {
            return null;
        }

        LeaveApplyDTO dto = new LeaveApplyDTO();
        ENTITY2DTO4LeaveApply.copy(po, dto, null);

        LeaveTypeEnum leaveTypeEnum = LeaveTypeEnum.getLeaveType(dto.getLeaveType());
        dto.setLeaveName(leaveTypeEnum.getName());

        AuditStatusEnum auditStatusEnum = AuditStatusEnum.getAuditStatus(dto.getStatus());
        dto.setStatusName(auditStatusEnum.getAuditStatusName());

        LeaveAuditDTO auditDTO = leaveAuditService.getAuditChain(po.getId());
        dto.setAudit(auditDTO);

        EmployeeDTO applicant = employeeService.loadEmployee(dto.getApplicantID());
        dto.setApplicant(applicant);

        return dto;
    }

    @Override public PageModel getApplysByRoleAndAuditId(int auditId, AuditStatusEnum auditStatus,
                                                         AuditStatusEnum applyStatus, int pageNo,
                                                         int pageSize) {
        RoleEnum role = RoleEnum.MANAGE;
        if (employeeService.isHr(auditId)) {
            role = RoleEnum.HR;
        }

        PageModel pageModel = leaveApplyDao.pageAuditsByRoleAndAuditId(auditId, role.getType(),
                                                                       auditStatus.getAuditStatus(),
                                                                       applyStatus.getAuditStatus(), pageNo, pageSize);

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
            dtos.add(dto);
        }

        result.setRecords(dtos);

        return pageModel;
    }

}
