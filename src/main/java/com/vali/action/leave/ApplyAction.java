package com.vali.action.leave;

import com.vali.bo.LoginBO;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import com.vali.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApplyAction {

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @Resource(name = "leaveApplyService")
    private LeaveApplyService leaveApplyService;

    @Resource(name = "leaveAuditService")
    private LeaveAuditService leaveAuditService;

    @Resource(name = "employeeHolidayService")
    private EmployeeHolidayService employeeHolidayService;

    @RequestMapping(value = "/leave")
    public ModelAndView leaveIndex() {

        EmployeeDTO employee = LoginBO.getLoginUser();
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getEmployeeHoliday(employee.getId());

        Map model = new HashMap();
        model.put("employee", employee);
        model.put("employeeHolidays", employeeHolidays);
        return new ModelAndView("leave/apply", model);
    }

    @RequestMapping(value = "/leaveApply")
    public String apply(LeaveApplyDTO applyDTO) {

        EmployeeDTO employeeDTO = LoginBO.getLoginUser();
        applyDTO.setApplicantID(employeeDTO.getId());
        applyDTO.setApplyTime(new Date());
        applyDTO.setLeaveDays(TimeUtil.caculateLeaveDays(applyDTO.getLeaveStartTime(), applyDTO.getLeaveEndTime()));

        int applyId = leaveApplyService.saveApplyDetail(applyDTO);
        applyDTO.setId(applyId);

        if (applyId > 0) {
            leaveAuditService.saveAudit(getLeaveAuditDTO(applyDTO));
        }

        return "redirect:/myLeaveApply";
    }

    private LeaveAuditDTO getLeaveAuditDTO(LeaveApplyDTO applyDTO) {

        LeaveAuditDTO leaveAuditDTO = new LeaveAuditDTO();

        EmployeeDTO employee = LoginBO.getLoginUser();
        leaveAuditDTO.setApplyId(applyDTO.getId());
        leaveAuditDTO.setHrAuditStatus(AuditStatusEnum.AUDITING.getAuditStatus());
        leaveAuditDTO.setHrId(employeeService.getHr().getId());
        leaveAuditDTO.setManagerId(employee.getManger().getId());
        leaveAuditDTO.setManagerAuditStatus(AuditStatusEnum.AUDITING.getAuditStatus());

        return leaveAuditDTO;
    }

    @RequestMapping(value = "/myLeaveApply")
    public ModelAndView myApply() {

        int applicantID = LoginBO.getLoginUser().getId();
        List<LeaveApplyDTO> myLeaveApplys = leaveApplyService.getApplyRecords(applicantID);
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getEmployeeHoliday(applicantID);

        Map model = new HashMap();
        model.put("myApplys", myLeaveApplys);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/applyList", model);
    }

    @RequestMapping(value = "/leaveApplyDetail")
    public String details() {
        return "leave/details";
    }

}
