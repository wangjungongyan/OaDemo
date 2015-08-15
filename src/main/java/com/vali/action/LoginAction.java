package com.vali.action;

import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.service.user.remote.UserService;
import com.vali.util.Constant;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 15/8/5.
 */
@Controller
public class LoginAction {

    @Setter
    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public ModelAndView login(String loginName, String passWord) {

        LoginVerifyDTO loginVerifyDTO = userService.verifyLoginUser(loginName, passWord);

        if (loginVerifyDTO.isVerify()) {
            setLoginUser2Session(loginName);
            return new ModelAndView("redirect:/main");
        }

        Map model = new HashMap();
        model.put("msg", loginVerifyDTO.getMsg());
        return new ModelAndView("redirect:/login", model);

    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/main")
    public ModelAndView main() {

        Map model = new HashMap();
        List<FirstMenuDTO> menus = userService.getUserMenus(1);
        model.put("menus", menus);
        return new ModelAndView("main", model);
    }

    private void setLoginUser2Session(String loginName) {
        session.setAttribute(Constant.LOGIN_USER, userService.loadUser(loginName));
    }

}
