package com.vali.action.setting;

import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
public class EmployeeHolidayAction {

    @Resource(name = "employeeHolidayService")
    private EmployeeHolidayService employeeHolidayService;

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @RequestMapping(value = "/sys/employeeHolidayList")
    public ModelAndView listEmployeeHoliday(Integer employeeId, String year) {
        Map model = new HashMap();
        model.put("employee", employeeService.loadEmployee(employeeId));
        model.put("year", year);
        model.put("employeeHolidays", employeeHolidayService.getEmployeeHolidayWhenExists(employeeId, year));
        return new ModelAndView("setting/employeeHolidayList", model);
    }

    @RequestMapping(value = "/sys/employeeHolidayUpdate", produces = { "text/javascript;charset=UTF-8" })
    @ResponseBody
    public String ajaxUpdateEmployeeHoliday(EmployeeHolidayDTO employeeHoliday) {
        boolean updated = employeeHolidayService.updateHolidayOwn(employeeHoliday);
        return updated ? "更新成功." : "更新失败.";
    }

}
