package com.vali.service.user.remote;

import com.vali.dto.settings.EmployeeHolidayDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by vali on 15-8-16.
 */
public interface EmployeeHolidayService {

    /**
     * 获取员工假期
     *
     * @param employeeId
     * @return
     */
    public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId);

    /**
     * 获取各种类型假期
     *
     * @return
     */
    public List<EmployeeHolidayDTO> getHolidayTypes();

    /**
     * 根据请假区间计算出请假天数
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public BigDecimal caculateLevaeDays(Date beginTime, Date endTime);

    /**
     * 扣假
     *
     * @param dto
     * @return
     */
    public boolean decreaseHolidayDay(EmployeeHolidayDTO dto);

    /**
     * 加假
     *
     * @param dto
     * @return
     */
    public boolean increaseHolidayDay(EmployeeHolidayDTO dto);

}
