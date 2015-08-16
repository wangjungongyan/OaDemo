package com.vali.action.leave;

import com.vali.bo.LoginBO;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.service.leave.remote.LeaveApplyService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApplyAction {

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @Resource(name = "leaveApplyService")
    private LeaveApplyService leaveService;

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
        applyDTO.setLeaveDays(caculateLeaveDays(applyDTO.getLeaveStartTime(), applyDTO.getLeaveEndTime()));

        leaveService.saveApplyDetail(applyDTO);
        return "redirect:/myLeaveApply";
    }

    //TODO
    private BigDecimal caculateLeaveDays(Date leaveStartTime, Date leaveEndTime) {
        //计算规则：请假时长以4个小时为单位，小于等于4小时则算作请假4小时，大于4小时按照8小时算
        //碰到双休/国定假等非工作日，不计算在请假天数内
        return new BigDecimal(11);
    }

    @RequestMapping(value = "/myLeaveApply")
    public ModelAndView myApply() {

        int applicantID = LoginBO.getLoginUser().getId();
        List<LeaveApplyDTO> myLeaveApplys = leaveService.getApplyRecords(applicantID);
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
