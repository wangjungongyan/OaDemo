package com.vali.action.purchase;

import com.alibaba.fastjson.JSONObject;
import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.DefaultApproveStatusEnum;
import com.vali.service.purchase.PurchaseReimburseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

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
    public ModelAndView approveIndex(Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        if(pageNo<1){
            pageNo=1;
        }
        PageModel pageModel = purchaseReimburseService.queryPreApprove(LoginBO.getLoginUser(), pageNo, 10);
        Map model = new HashMap();
        model.put("pageModel",pageModel);
        return new ModelAndView("/purchase/approveReimburseApply",model);
    }

    @RequestMapping(value = "/purchase/approve")
    @ResponseBody
    public Object approve(Integer applyId,Integer status,String mark){
        JSONObject json = new JSONObject();
        PurchaseReimburseDTO reimburseDTO = purchaseReimburseService.queryPurchaseReimburse(applyId);
        if(reimburseDTO==null){
            json.put("errorMsg","申请不存在");
        }
        if(reimburseDTO.getManager()==LoginBO.getLoginUser().getId() && reimburseDTO.getMngApproveStatus() == DefaultApproveStatusEnum.DEFAULT.getStatusCode()){
            //主管审批
            purchaseReimburseService.doPurchaseReimburseApprove(LoginBO.getLoginUser(),applyId,status,mark,"mng");
        }
        if(reimburseDTO.getFinnaceUserId()==LoginBO.getLoginUser().getId() &&
                reimburseDTO.getMngApproveStatus()==DefaultApproveStatusEnum.PASSED.getStatusCode() &&
                reimburseDTO.getFinnaceApproveStatus()==DefaultApproveStatusEnum.DEFAULT.getStatusCode()){
            //财务审批
            purchaseReimburseService.doPurchaseReimburseApprove(LoginBO.getLoginUser(),applyId,status,mark,"finnace");
        }
        json.put("errorMsg","此申请你没权限审批");
        return json;
    }

    @RequestMapping(value = "/purchase/myReimburseApprove")
    public ModelAndView myApprove(Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        if(pageNo<1){
            pageNo=1;
        }
        PageModel pageModel = purchaseReimburseService.queryMyApprove(LoginBO.getLoginUser(), pageNo, 10);
        Map model = new HashMap();
        model.put("pageModel",pageModel);
        return new ModelAndView("apply",model);
    }



}
