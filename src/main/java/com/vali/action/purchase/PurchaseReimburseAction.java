package com.vali.action.purchase;

import com.vali.dto.purchase.PurchaseReimburseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fanshuai on 15/9/13.
 */
@Controller("purchase")
public class PurchaseReimburseAction {

    @RequestMapping(value = "/purchase/applyIndex")
    public ModelAndView applyIndex(){

        return new ModelAndView("/purchase/applyIndex");
    }

    @RequestMapping(value = "/purchase/apply")
    public ModelAndView apply(PurchaseReimburseDTO purchaseReimburseDTO){

        return new ModelAndView("/purchase/applyIndex.ftl");
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
