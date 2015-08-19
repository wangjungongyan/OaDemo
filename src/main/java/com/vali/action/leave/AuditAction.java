package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuditAction {

    @RequestMapping(value = "/leave/auditIndex")
    public String auditIndex() {
        return "leave/audit";
    }

    @RequestMapping(value = "/leave/ajaxAudit")
    public boolean ajaxAudit() {
        return true;
    }

    @RequestMapping(value = "/leave/myAudits")
    public String getMyAudit() {
        return "leave/getMyAudit";
    }

}
