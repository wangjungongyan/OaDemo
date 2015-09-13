package com.vali.action.purchase;

import com.vali.dto.purchase.PurchaseReimburseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fanshuai on 15/9/13.
 */
@Controller("/purchase")
public class PurchaseReimburseAction {

    @RequestMapping(value = "/applyIndex")
    public ModelAndView applyIndex(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/apply")
    public ModelAndView apply(PurchaseReimburseDTO purchaseReimburseDTO){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/myApply")
    public ModelAndView myApply(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/approveIndex")
    public ModelAndView approveIndex(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/approve")
    public ModelAndView approve(){

        return new ModelAndView("apply");
    }

    @RequestMapping(value = "/myApprove")
    public ModelAndView myApprove(){

        return new ModelAndView("apply");
    }



}
