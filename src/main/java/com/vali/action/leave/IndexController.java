package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")

    public ModelAndView listProjects() {
        Map model = new HashMap();
        model.put("name","vali");
        return new ModelAndView("index", model);
    }

}
