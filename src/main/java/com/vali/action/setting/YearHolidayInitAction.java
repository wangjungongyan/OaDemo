package com.vali.action.setting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vali.service.setting.YearHolidayInitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 15/8/24.
 */
@Controller
public class YearHolidayInitAction {

    @Resource(name = "yearHolidayInitService")
    private YearHolidayInitService yearHolidayInitService;

    @RequestMapping(value = "/sys/initYearHoliday")
    public String initYearHoliday() {
        return "/sys/initYearHoliday";
    }

    @RequestMapping(value = "/sys/initYearHoliday/setting")
    @ResponseBody
    public ModelAndView setting(Integer year) {
        JSONObject json = new JSONObject();
        try {
            yearHolidayInitService.initSettings(year);
            json.put("success", true);
        } catch (Exception e) {
            json.put("success", false);
            json.put("errorMsg", "失败:" + e.getMessage());
        }
        return new ModelAndView("/setting/initYearHoliday");
    }
}
