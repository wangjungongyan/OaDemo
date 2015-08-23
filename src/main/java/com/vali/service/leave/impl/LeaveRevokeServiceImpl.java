package com.vali.service.leave.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.leave.EmployeeHolidayDao;
import com.vali.dao.leave.LeaveApplyDao;
import com.vali.dao.leave.LeaveAuditDao;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveRevokeQueryDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.po.leave.LeaveRevokeQueryPO;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveRevokeService;
import com.vali.service.user.remote.EmployeeService;
import com.vali.util.TimeUtil;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vali on 15-8-23.
 */

@Service("leaveRevokeService")
public class LeaveRevokeServiceImpl implements LeaveRevokeService {

    @Setter
    @Resource(name = "leaveApplyDao")
    private LeaveApplyDao leaveApplyDao;

    @Setter
    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @Setter
    @Resource(name = "employeeHolidayDao")
    private EmployeeHolidayDao employeeHolidayDao;

    @Setter
    @Resource(name = "leaveAuditDao")
    private LeaveAuditDao leaveAuditDao;

    @Setter
    @Resource(name = "leaveApplyService")
    private LeaveApplyService leaveApplyService;

    private BeanCopier DTO2ENTITY4LeaveApplyQuery = BeanCopier.create(LeaveRevokeQueryDTO.class,
                                                                      LeaveRevokeQueryPO.class,
                                                                      false);

    private BeanCopier ENTITY2DTO4LeaveApply = BeanCopier.create(LeaveApplyPO.class, LeaveApplyDTO.class, false);

    private BeanCopier PAGECopier = BeanCopier.create(PageModel.class, PageModel.class, false);

    @Override public PageModel pageCanRevokeApplys(LeaveRevokeQueryDTO queryDTO, int pageNo, int pageSize) {

        LeaveRevokeQueryPO queryPO = new LeaveRevokeQueryPO();
        DTO2ENTITY4LeaveApplyQuery.copy(queryDTO, queryPO, null);

        PageModel pageModel = leaveApplyDao.pageCanRevokeApplys(queryPO, pageNo, pageSize);

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

    @Override public boolean revokeApply(int applyId, String suggest) {

        LeaveApplyDTO leaveApplyDTO = leaveApplyService.getApplyDetailByApplyId(applyId);

        EmployeeHolidayPO po = new EmployeeHolidayPO();
        po.setEmployeeId(leaveApplyDTO.getApplicantID());
        po.setUsed(leaveApplyDTO.getLeaveDays());
        po.setType(leaveApplyDTO.getLeaveType());
        po.setYear(TimeUtil.getCurrentYear());

        employeeHolidayDao.decreaseHolidayDay(po);
        leaveApplyDao.updateApplyStatus(applyId, AuditStatusEnum.REOVKE.getAuditStatus());
        leaveAuditDao.updateStatusAndSuggest4Hr(applyId, suggest, AuditStatusEnum.REOVKE.getAuditStatus());

        return true;
    }
}
