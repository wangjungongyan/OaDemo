package com.vali.action.purchase;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.service.purchase.PurchaseReimburseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 15/9/13.
 */
@Controller("purchase")
public class PurchaseReimburseAction {
    @Resource(name = "purchaseReimburseService")
    PurchaseReimburseService purchaseReimburseService;
    @RequestMapping(value = "/purchase/reimburseApplyIndex")
    public ModelAndView applyIndex(){
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee",employee);
        model.put("today",new Date());
        return new ModelAndView("/purchase/reimburseApply",model);
    }

    @RequestMapping(value = "/purchase/reimburseApply")
    public ModelAndView apply(PurchaseReimburseDTO purchaseReimburseDTO){
        purchaseReimburseService.savePurchaseReimburseApply(purchaseReimburseDTO);
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("message","提交申请成功");
        return new ModelAndView("/purchase/reimburseApplyResult",model);
    }

    @RequestMapping(value = "/purchase/myReimburseApply")
    public ModelAndView myApply(Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        if(pageNo<1){
            pageNo=1;
        }
        PageModel pageModel = purchaseReimburseService.queryMyPurchaseReimburse(LoginBO.getLoginUser(),pageNo,10);
        Map model = new HashMap();
        model.put("pageModel",pageModel);
        return new ModelAndView("/purchase/myReimburseApply",model);
    }

    @RequestMapping(value = "/purchase/reimburseApproveIndex")
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
