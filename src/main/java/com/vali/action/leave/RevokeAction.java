package com.vali.action.leave;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.bo.PageBO;
import com.vali.dto.settings.EmployeeHolidayDTO;
import com.vali.dto.leave.LeaveRevokeQueryDTO;
import com.vali.service.leave.remote.LeaveRevokeService;
import com.vali.service.user.remote.EmployeeHolidayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RevokeAction {

    @Resource(name = "employeeHolidayService")
    private EmployeeHolidayService employeeHolidayService;

    @Resource(name = "leaveRevokeService")
    private LeaveRevokeService leaveRevokeService;

    @RequestMapping(value = "/leave/revoke")
    public ModelAndView listCanRevokeApply(LeaveRevokeQueryDTO leaveRevokeQueryDTO, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        leaveRevokeQueryDTO.setAuditUserId(applicantID);

        PageModel pageModel = leaveRevokeService.pageCanRevokeApplys(leaveRevokeQueryDTO, PageBO.getPageNo(pageNo),
                                                                     PageBO.getPageSize(pageSize));

        List<EmployeeHolidayDTO> employeeHolidays = employeeHolidayService.getHolidayTypes();

        Map model = new HashMap();
        model.put("queryDTO", leaveRevokeQueryDTO);
        model.put("pageModel", pageModel);
        model.put("employeeHolidays", employeeHolidays);

        return new ModelAndView("leave/revoke", model);
    }

    @RequestMapping(value = "/leave/ajaxRevoke", produces = { "text/javascript;charset=UTF-8" })
    @ResponseBody
    public String ajaxRevoke(Integer applyId, String revokeSuggest) {
        boolean process = leaveRevokeService.revokeApply(applyId, revokeSuggest);
        return process ? "操作成功." : "操作失败.";
    }
}
