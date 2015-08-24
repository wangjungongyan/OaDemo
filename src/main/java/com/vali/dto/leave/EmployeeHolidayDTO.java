package com.vali.dto.leave;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vali on 15-8-16.
 */

@Data
public class EmployeeHolidayDTO {

    private int id;

    private int employeeId;

    private String year;

    private BigDecimal own;

    private BigDecimal used;

    private BigDecimal surplus;

    private int type;

    private String name;

    private String desc;

    private Date addTime;

    private Date updateTime;

}

