package com.vali.action.purchase;

import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 15/9/13.
 */
@Controller("purchase")
public class PurchaseReimburseAction {

    @RequestMapping(value = "/purchase/applyIndex")
    public ModelAndView applyIndex(){
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee",employee);
        model.put("today",new Date());
        return new ModelAndView("/purchase/applyIndex",model);
    }

    @RequestMapping(value = "/purchase/apply")
    public ModelAndView apply(PurchaseReimburseDTO purchaseReimburseDTO){

        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee",employee);
        model.put("today",new Date());
        model.put("message","提交申请成功");
        model.put("dto",purchaseReimburseDTO);
        return new ModelAndView("/purchase/applyIndex",model);
    }

    @RequestMapping(value = "/purchase/myApply")
    public ModelAndView myApply(){

        return new ModelAndView("/purchase/myApply.ftl");
    }

    @RequestMapping(value = "/purchase/approveIndex")
    public ModelAndView approveIndex(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/purchase/approve")
    public ModelAndView approve(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/purchase/myApprove")
    public ModelAndView myApprove(){

        return new ModelAndView("apply");
    }



}
