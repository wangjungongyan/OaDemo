package com.vali.action.setting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vali.dto.settings.HolidaySettingDTO;
import com.vali.service.setting.HolidaySettingService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 15/8/22.
 */
@Controller
public class LegalHolidaySettingAction {
    @Resource(name = "holidaySettingService")
    private HolidaySettingService holidaySettingService;

    @RequestMapping(value = "/sys/holidaySettingIndex")
    public ModelAndView HolidaySettingIndex(Integer year){
        if(year==null){
            year = new DateTime(new Date()).year().get();
        }
        List<HolidaySettingDTO> holidaySettingDTOList = holidaySettingService.queryHolidaySettings(year);
        Map model = new HashMap();
        model.put("year",year);
        model.put("holidaySettingDTOList",holidaySettingDTOList);
        return new ModelAndView("setting/holidaySettingIndex",model);
    }

    @RequestMapping(value = "/sys/saveHolidaySetting")
    @ResponseBody
    public Object saveHolidaySetting(HolidaySettingDTO holidaySettingDTO){
        try {
            if(holidaySettingService.dayHasSettinged(holidaySettingDTO.getDay())){
                JSONObject json = new JSONObject();
                json.put("success",false);
                json.put("errorMsg","当前日期已经设置过了");
                return json;
            }
            int year = new DateTime(holidaySettingDTO.getDay()).year().get();
            holidaySettingDTO.setYear(year);
            int id = holidaySettingService.saveHolidaySetting(holidaySettingDTO);
            if(id>0){
                JSONObject json = new JSONObject();
                json.put("success",true);
                return json;
            }else {
                JSONObject json = new JSONObject();
                json.put("success",false);
                json.put("errorMsg","保存失败请重试");
                return json;
            }
        }catch (Exception e){
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("errorMsg","保存失败:"+e.getMessage());
            return json;
        }
    }
    @RequestMapping(value = "/sys/deleteHolidaySetting")
    @ResponseBody
    public Object deleteHolidaySetting(Integer id){
        JSONObject json = new JSONObject();
        try {
            holidaySettingService.deleteHolidaySetting(id);
            json.put("success",true);
        }catch (Exception e){
            json.put("success",false);
            json.put("errorMsg","删除失败:"+e.getMessage());
        }
        return json;
    }

}
