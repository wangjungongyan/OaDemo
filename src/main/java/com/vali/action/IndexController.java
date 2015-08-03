package com.vali.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public ModelAndView listProjects() {
        return new ModelAndView("index", new HashMap());
    }

}
