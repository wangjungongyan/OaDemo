package com.vali.action;

import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.service.user.remote.UserService;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public ModelAndView login(String userName, String passWord) {

        LoginVerifyDTO loginVerifyDTO = userService.verifyLoginUser(userName, passWord);

        Map model = new HashMap();

        if (loginVerifyDTO.isVerify()) {
            List<FirstMenuDTO> menus = userService.getUserMenus(1);
            model.put("menus",menus);
            return new ModelAndView("main", model);
        }

        model.put("msg", loginVerifyDTO.getMsg());
        return new ModelAndView("login", model);

    }

    @RequestMapping(value = "/login")
    public String index() {
        return "login";
    }
}
