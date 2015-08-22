package com.vali.action.leave;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.bo.PageBO;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;
import com.vali.dto.leave.LeaveAuditResultDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuditAction {

    @Resource(name = "leaveApplyService")
    private LeaveApplyService leaveApplyService;

    @Resource(name = "employeeHolidayService")
    private EmployeeHolidayService employeeHolidayService;

    @Resource(name = "leaveAuditService")
    private LeaveAuditService leaveAuditService;

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @RequestMapping(value = "/leave/wait2Audit")
    public ModelAndView wait2Audit(LeaveAuditQueryDTO leaveAuditQueryDTO, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        leaveAuditQueryDTO.setAuditUserId(applicantID);

        PageModel pageModel = leaveApplyService.pageWait4AduitApplys(leaveAuditQueryDTO, PageBO.getPageNo(pageNo),
                                                                     PageBO.getPageSize(pageSize));

        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getEmployeeHoliday(applicantID);

        Map model = new HashMap();
        model.put("queryDTO", leaveAuditQueryDTO);
        model.put("pageModel", pageModel);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/audit", model);
    }

    @RequestMapping(value = "/leave/ajaxAudit")
    @ResponseBody
    public LeaveAuditResultDTO ajaxAudit(Integer applyId, Integer auditStatus, String auditSuggest) {

        boolean isHr = employeeService.isHr(LoginBO.getLoginUser().getId());

        AuditStatusEnum auditStatusEnum = this.prepareAuditStatus(isHr, applyId, auditStatus);

        if (isHr) {
            leaveAuditService.hrAudit(applyId, auditSuggest, auditStatusEnum.getAuditStatus());
        } else {
            leaveAuditService.manageAudit(applyId, auditSuggest, auditStatusEnum.getAuditStatus());
        }

        leaveApplyService.updateApplyStatus(applyId, auditStatusEnum.getAuditStatus());

        return prepareLeaveAuditResultDTO();
    }

    private AuditStatusEnum prepareAuditStatus(boolean isHr, Integer applyId, Integer auditStatus) {

        if (auditStatus == AuditStatusEnum.REJECT.getAuditStatus()) {
            return AuditStatusEnum.REJECT;
        }

        if (!isHr) {
            return AuditStatusEnum.PASS;
        }

        EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
        dto.setEmployeeId(LoginBO.getLoginUser().getId());

        LeaveApplyDTO leaveApplyDTO =leaveApplyService.getApplyDetailByApplyId(applyId);
        dto.setType(leaveApplyDTO.getLeaveType());
        dto.setUsed(leaveApplyDTO.getLeaveDays());
        dto.setYear("1");
        //TODO
        boolean sucessDecreaseHoliday = employeeHolidayService.decreaseHolidayDay(dto);

        if (sucessDecreaseHoliday) {
            return AuditStatusEnum.PASS;
        }

        return AuditStatusEnum.REJECT;
    }

    //TODO
    private LeaveAuditResultDTO prepareLeaveAuditResultDTO() {
        return null;
    }

    @RequestMapping(value = "/leave/myAudits")
    public String getMyAudit() {
        return "leave/getMyAudit";
    }

}
