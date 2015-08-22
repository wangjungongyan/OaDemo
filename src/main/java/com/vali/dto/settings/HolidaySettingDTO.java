package com.vali.dto.settings;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanshuai on 15/8/22.
 */
@Data
public class HolidaySettingDTO implements Serializable {
    private Integer id;
    private Integer year;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private String        holiday;
    private String mark;
    private Integer isWorkDay;
    public String getIsWorkDayStr(){
        if(isWorkDay==null){
            return "否";
        }
        if(isWorkDay==1){
            return "是";
        }
        return "否";
    }
    public boolean isWorkDay(){
        return "是".equals(getIsWorkDayStr());
    }
}
