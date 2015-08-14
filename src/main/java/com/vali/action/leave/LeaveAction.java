package com.vali.action.leave;

import com.vali.service.user.remote.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 15/8/13.
 */
@Controller
public class LeaveAction {


    @RequestMapping(value = "/leave/applyIndex")
    public String leaveIndex(){
        return "leave/applyIndex";
    }


    @RequestMapping(value = "/leave/apply")
    public String apply(){
        return "leave/apply";
    }

    @RequestMapping(value = "/leave/myApply")
    public String myApply(){
        return "leave/myApply";
    }

    @RequestMapping(value = "/leave/details")
    public String details(){
        return "leave/details";
    }

    @RequestMapping(value = "/leave/approval")
    public String approval(){
        return "leave/approval";
    }

    @RequestMapping(value = "/leave/myApproval")
    public String myApproval(){
        return "leave/myApproval";
    }


    @RequestMapping(value = "/leave/revoke")
    public String revoke(){
        return "leave/revoke";
    }

}
