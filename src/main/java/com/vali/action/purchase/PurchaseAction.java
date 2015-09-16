package com.vali.action.purchase;

import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseDTO;
import com.vali.dto.user.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PurchaseAction {

    @RequestMapping(value = "/purchase/applyIndex")
    public ModelAndView index() {
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee", employee);
        return new ModelAndView("purchase/purchaseIndex", model);
    }

    @RequestMapping(value = "/purchase/apply", method = RequestMethod.POST)
    public ModelAndView apply(PurchaseDTO purchaseDTO) {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/purchaseApply", model);
    }

    @RequestMapping(value = "/purchase/myPurchaseApplyList")
    public ModelAndView myApplyList() {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/apply", model);
    }

    @RequestMapping(value = "/purchase/myPurchaseAudits")
    public ModelAndView myAudits() {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/apply", model);
    }

}
