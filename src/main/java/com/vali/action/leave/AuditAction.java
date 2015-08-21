package com.vali.action.leave;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.bo.PageBO;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveAuditQueryDTO;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.user.remote.EmployeeHolidayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

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
    public boolean ajaxAudit(Integer applyId, Integer auditStatus, String auditSuggest) {
        //修改LL_LeaveAudit表状态和时间
        //修改LL_LeaveApply表状态，如果是经理审批并且审核不通过，直接修改状态为2，如果是HR审批，则根据审核通过、不通过，修改状态
        //扣除假期
        return true;
    }

    @RequestMapping(value = "/leave/myAudits")
    public String getMyAudit() {
        return "leave/getMyAudit";
    }

}
