package com.vali.service.user.remote;

import com.vali.dto.leave.EmployeeHolidayDTO;

import java.util.List;

/**
 * Created by vali on 15-8-16.
 */
public interface EmployeeHolidayService {

    public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId);

}
