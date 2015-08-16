package com.vali.po.leave;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vali on 15-8-16.
 */

@Data
public class EmployeeHolidayPO {

    private int id;

    private int EmployeeId;

    private String year;

    private BigDecimal own;

    private BigDecimal used;

    private BigDecimal surplus;

    private int type;

    private Date addTime;

    private Date updateTime;

}

