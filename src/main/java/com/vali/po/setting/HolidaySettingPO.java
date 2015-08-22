package com.vali.po.setting;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fanshuai on 15/8/23.
 */
@Data
public class HolidaySettingPO {
    private Integer id;
    private Integer year;
    private Date day;
    private String        holiday;
    private String mark;
    private Integer isWorkDay;
}
