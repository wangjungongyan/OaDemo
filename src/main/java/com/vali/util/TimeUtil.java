package com.vali.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */
public class TimeUtil {

    //TODO
    public static BigDecimal caculateLeaveDays(Date startTime, Date endTime) {
        //计算规则：请假时长以4个小时为单位，小于等于4小时则算作请假4小时，大于4小时按照8小时算
        //碰到双休/国定假等非工作日，不计算在请假天数内
        return new BigDecimal(11);
    }

}
