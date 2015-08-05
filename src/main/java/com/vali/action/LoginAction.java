package com.vali.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fanshuai on 15/8/5.
 */
@Controller
public class LoginAction {

    @RequestMapping(value = "/login")
    public String login(){

        return "login";
    }
}
