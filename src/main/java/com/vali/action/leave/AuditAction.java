package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuditAction {

    @RequestMapping(value = "/leave/audit")
    public String approval(){
        return "leave/approval";
    }

    @RequestMapping(value = "/leave/myAudit")
    public String myApproval(){
        return "leave/myApproval";
    }

}
