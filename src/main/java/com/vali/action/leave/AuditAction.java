package com.vali.action.leave;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.bo.PageBO;
import com.vali.dto.settings.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;
import com.vali.enums.leave.LeaveAuditStatusEnum;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.leave.remote.LeaveAuditService;
import com.vali.service.mail.MailService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import com.vali.util.TimeUtil;
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

    @Resource(name = "mailService")
    private MailService mailService;

    @RequestMapping(value = "/leave/wait2Audit")
    public ModelAndView wait2Audit(LeaveAuditQueryDTO leaveAuditQueryDTO, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        leaveAuditQueryDTO.setAuditUserId(applicantID);

        PageModel pageModel = leaveApplyService.pageWait4AduitApplys(leaveAuditQueryDTO, PageBO.getPageNo(pageNo),
                                                                     PageBO.getPageSize(pageSize));

        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getHolidayTypes();

        Map model = new HashMap();
        model.put("queryDTO", leaveAuditQueryDTO);
        model.put("pageModel", pageModel);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/audit", model);
    }

    @RequestMapping(value = "/leave/ajaxAudit", produces = { "text/javascript;charset=UTF-8" })
    @ResponseBody
    public String ajaxAudit(Integer applyId, Integer auditStatus, String auditSuggest) {

        boolean isHr = employeeService.isHr(LoginBO.getLoginUser().getId());

        LeaveAuditStatusEnum auditStatusEnum = this.prepareAuditStatus(isHr, applyId, auditStatus);

        if (isHr) {
            leaveAuditService.hrAudit(applyId, auditSuggest, auditStatusEnum.getAuditStatus());
            leaveApplyService.updateApplyStatus(applyId, auditStatusEnum.getAuditStatus());
        } else {
            leaveAuditService.manageAudit(applyId, auditSuggest, auditStatusEnum.getAuditStatus());
            if (auditStatusEnum == LeaveAuditStatusEnum.REJECT) {
                leaveApplyService.updateApplyStatus(applyId, auditStatusEnum.getAuditStatus());
            }
        }

        return prepareAuditResult(auditStatusEnum, auditStatus, isHr);
    }

    private LeaveAuditStatusEnum prepareAuditStatus(boolean isHr, Integer applyId, Integer auditStatus) {

        if (auditStatus == LeaveAuditStatusEnum.REJECT.getAuditStatus()) {
            return LeaveAuditStatusEnum.REJECT;
        }

        if (!isHr) {
            return LeaveAuditStatusEnum.PASS;
        }

        EmployeeHolidayDTO dto = new EmployeeHolidayDTO();

        LeaveApplyDTO leaveApplyDTO = leaveApplyService.getApplyDetailByApplyId(applyId);
        dto.setType(leaveApplyDTO.getLeaveType());
        dto.setUsed(leaveApplyDTO.getLeaveDays());

        dto.setYear(TimeUtil.getCurrentYear());
        dto.setEmployeeId(leaveApplyDTO.getApplicantID());
        boolean sucessDecreaseHoliday = employeeHolidayService.decreaseHolidayDay(dto);

        if (sucessDecreaseHoliday) {
            return LeaveAuditStatusEnum.PASS;
        }

        return LeaveAuditStatusEnum.REJECT;
    }

    private String prepareAuditResult(LeaveAuditStatusEnum caculatedAuditStatusEnum,
                                      Integer originAuditStatus, boolean isHr) {

        if (isHr && originAuditStatus != caculatedAuditStatusEnum.getAuditStatus()) {
            return "由于剩余假期天数不够,审核不通过.";
        }

        mailService.sendEmail(employeeService.getHr().getEmail(), "您有一个待审批请假申请", "http://lloa.com/leave/wait2Audit");

        return "已操作成功.";
    }

    @RequestMapping(value = "/leave/myAudits")
    public ModelAndView getMyAudit(LeaveAuditQueryDTO leaveAuditQueryDTO, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        leaveAuditQueryDTO.setAuditUserId(applicantID);

        PageModel pageModel = leaveAuditService.pageAduitedApplys(leaveAuditQueryDTO, PageBO.getPageNo(pageNo),
                                                                  PageBO.getPageSize(pageSize));
        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getHolidayTypes();

        Map model = new HashMap();
        model.put("queryDTO", leaveAuditQueryDTO);
        model.put("pageModel", pageModel);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/myAudits", model);
    }

}
