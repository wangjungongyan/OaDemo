package com.vali.action.leave;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.bo.PageBO;
import com.vali.dto.settings.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveApplyQueryDTO;
import com.vali.dto.leave.LeaveAuditDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.AuditStatusEnum;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.mail.MailService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Resource(name = "mailService")
    private MailService mailService;

    @RequestMapping(value = "/leave")
    public ModelAndView leaveIndex() {

        EmployeeDTO employee = LoginBO.getLoginUser();
        employee.setHr(employeeService.getHr());
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getEmployeeHoliday(employee.getId());

        Map model = new HashMap();
        model.put("employee", employee);
        model.put("employeeHolidays", employeeHolidays);
        return new ModelAndView("leave/apply", model);
    }

    @RequestMapping(value = "/leave/apply")
    public String apply(LeaveApplyDTO applyDTO) {

        EmployeeDTO employeeDTO = LoginBO.getLoginUser();
        applyDTO.setApplicantID(employeeDTO.getId());
        applyDTO.setApplyTime(new Date());
        applyDTO.setLeaveDays(
                employeeHolidayService.caculateLeaveDays(applyDTO.getLeaveStartTime(), applyDTO.getLeaveEndTime()));

        int applyId = leaveApplyService.saveApplyDetail(applyDTO);
        applyDTO.setId(applyId);

        if (applyId > 0) {
            leaveAuditService.saveAudit(getLeaveAuditDTO(applyDTO));
        }

        mailService.sendEmail(employeeDTO.getManager().getEmail(), "您有一个待审批请假申请", "http://lloa.com/leave/wait2Audit");

        return "redirect:/leave/myLeaveApply";
    }

    @RequestMapping(value = "/leave/myLeaveApply")
    public ModelAndView myApplyWithConditions(LeaveApplyQueryDTO dto, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        dto.setApplicantID(applicantID);

        PageModel pageModel = leaveApplyService.getApplyRecords(dto, PageBO.getPageNo(pageNo), PageBO.getPageSize(
                pageSize));
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getHolidayTypes();

        Map model = new HashMap();
        model.put("queryDTO", dto);
        model.put("pageModel", pageModel);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/applyList", model);
    }

    @RequestMapping(value = "/leave/ajaxGetApplyDetail")
    @ResponseBody
    public LeaveApplyDTO ajaxGetApplyDetail(Integer applyId) {
        LeaveApplyDTO leaveApplyDTO = leaveApplyService.getApplyDetailByApplyId(applyId);
        return leaveApplyDTO;
    }

    private LeaveAuditDTO getLeaveAuditDTO(LeaveApplyDTO applyDTO) {

        LeaveAuditDTO leaveAuditDTO = new LeaveAuditDTO();

        EmployeeDTO employee = LoginBO.getLoginUser();
        leaveAuditDTO.setApplyId(applyDTO.getId());
        leaveAuditDTO.setHrAuditStatus(AuditStatusEnum.AUDITING.getAuditStatus());
        leaveAuditDTO.setHrId(employeeService.getHr().getId());
        leaveAuditDTO.setManagerId(employee.getManager().getId());
        leaveAuditDTO.setManagerAuditStatus(AuditStatusEnum.AUDITING.getAuditStatus());

        return leaveAuditDTO;
    }

    @RequestMapping(value = "/leave/myHolidays")
    public ModelAndView showMyHolidays() {

        EmployeeDTO employee = LoginBO.getLoginUser();
        employee.setHr(employeeService.getHr());
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getEmployeeHoliday(employee.getId());

        Map model = new HashMap();
        model.put("employeeHolidays", employeeHolidays);
        return new ModelAndView("leave/myHolidays", model);
    }

}
