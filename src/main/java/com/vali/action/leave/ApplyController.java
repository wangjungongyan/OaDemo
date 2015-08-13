package com.vali.action.leave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplyController {

    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    public ModelAndView apply() {
        Map model = new HashMap();
        model.put("name", "vali");
        return new ModelAndView("apply", model);
    }

}
