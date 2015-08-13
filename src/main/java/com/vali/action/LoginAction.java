package com.vali.action;

import com.vali.service.user.dto.LoginVerifyDTO;
import com.vali.service.user.remote.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 15/8/5.
 */
@Controller
public class LoginAction {

    @Setter
    //@Autowired(required = false)
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public ModelAndView login(String userName, String passWord) {

        LoginVerifyDTO loginVerifyDTO = userService.verifyLoginUser(userName, passWord);

        if (loginVerifyDTO.isVerify()) {
            return new ModelAndView("main", null);
        }

        Map model = new HashMap();
        model.put("msg", loginVerifyDTO.getMsg());
        return new ModelAndView("login", model);

    }

    @RequestMapping(value = "/login")
    public String index() {
        return "login";
    }
}
