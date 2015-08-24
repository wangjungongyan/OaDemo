package com.vali.action.setting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fanshuai on 15/8/24.
 */
@Controller
public class YearHolidayInitAction {

    @RequestMapping(value = "/sys/initYearHoliday")
    public String initYearHoliday(){
        return "/sys/initYearHoliday";
    }

    @RequestMapping(value = "/sys/initYearHoliday/setting")
    @ResponseBody
    public Object setting(){
        JSONObject json = new JSONObject();

        return json;
    }
}
