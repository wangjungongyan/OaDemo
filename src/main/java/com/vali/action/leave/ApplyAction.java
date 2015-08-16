package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplyAction {

    @RequestMapping(value = "/leave/index")
    public String leaveIndex() {
        return "leave/applyIndex";
    }

    @RequestMapping(value = "/leave/apply")
    public String apply() {
        return "leave/apply";
    }

    @RequestMapping(value = "/leave/myApply")
    public String myApply() {
        return "leave/myApply";
    }

    @RequestMapping(value = "/leave/applyDetail")
    public String details() {
        return "leave/details";
    }

}
