package com.vali.service.leave.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.leave.LeaveApplyDao;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveApplyQueryDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.po.leave.LeaveApplyQueryPO;
import com.vali.po.leave.LeaveAuditQueryPO;
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

    private BeanCopier DTO2ENTITY4LeaveAuditQuery = BeanCopier.create(LeaveAuditQueryDTO.class, LeaveAuditQueryPO.class,
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
            dto.setLeaveName(LeaveTypeEnum.getLeaveType(dto.getLeaveType()).getName());
            dto.setStatusName(AuditStatusEnum.getAuditStatus(dto.getStatus()).getAuditStatusName());
            dto.setAudit(leaveAuditService.getAuditChain(dto.getId()));
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
            dto.setLeaveName(LeaveTypeEnum.getLeaveType(dto.getLeaveType()).getName());
            dto.setStatusName(AuditStatusEnum.getAuditStatus(dto.getStatus()).getAuditStatusName());
            resultDTOS.add(dto);
        }

        result.setRecords(resultDTOS);

        return result;
    }

    @Override
    public LeaveApplyDTO getApplyDetailByApplyId(Integer applyId) {

        LeaveApplyPO po = leaveApplyDao.getApplyDetailByApplyId(applyId);

        if (po == null) {
            return null;
        }

        LeaveApplyDTO dto = new LeaveApplyDTO();
        ENTITY2DTO4LeaveApply.copy(po, dto, null);
        dto.setLeaveName(LeaveTypeEnum.getLeaveType(dto.getLeaveType()).getName());
        dto.setStatusName(AuditStatusEnum.getAuditStatus(dto.getStatus()).getAuditStatusName());
        dto.setAudit(leaveAuditService.getAuditChain(dto.getId()));
        dto.setApplicant(employeeService.loadEmployee(dto.getApplicantID()));

        return dto;
    }

    @Override public PageModel pageWait4AduitApplys(LeaveAuditQueryDTO leaveAuditQueryDTO, int pageNo,
                                                    int pageSize) {

        LeaveAuditQueryPO queryPO = new LeaveAuditQueryPO();
        DTO2ENTITY4LeaveAuditQuery.copy(leaveAuditQueryDTO, queryPO, null);
        queryPO.setRole(employeeService.getRoleByApplicantID(leaveAuditQueryDTO.getAuditUserId()).getType());

        PageModel pageModel = leaveApplyDao.pageWait4AduitApplys(queryPO,
                                                                 pageNo, pageSize);

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
            dtos.add(dto);
        }

        result.setRecords(dtos);

        return result;
    }

}
