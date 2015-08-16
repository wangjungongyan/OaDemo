package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RevokeAction {

    @RequestMapping(value = "/leave/revoke")
    public String revoke(){
        return "leave/revoke";
    }

}
