package com.vali.action.leave;

import com.vali.bo.LoginBO;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.service.leave.remote.LeaveService;
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

    @Resource(name = "leaveService")
    private LeaveService leaveService;

    @RequestMapping(value = "/leave")
    public ModelAndView leaveIndex() {

        EmployeeDTO employee = LoginBO.getLoginUser();
        List<EmployeeHolidayDTO> employeeHolidays = leaveService.getEmployeeHoliday(employee.getId());

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
        return "redirct:/myLeaveApply";
    }

    private BigDecimal caculateLeaveDays(Date leaveStartTime, Date leaveEndTime) {
        return new BigDecimal(11);
    }

    @RequestMapping(value = "/myLeaveApply")
    public ModelAndView myApply() {
        //查询出申请记录，按照时间倒序
        return new ModelAndView("leave/myLeaveApply");
    }

    @RequestMapping(value = "/leaveApplyDetail")
    public String details() {
        return "leave/details";
    }

}
