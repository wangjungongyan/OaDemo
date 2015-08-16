package com.vali.dao.leave;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.dto.leave.LeaveApplyDTO;
import com.vali.po.leave.EmployeeHolidayPO;

import java.util.List;

/**
 * Created by vali on 15-8-15.
 */

@NameSpace("EmployeeHoliday")
public interface EmployeeHolidayDao {

    @Action(action = ActionType.QUERY_LIST)
    public List<EmployeeHolidayPO> getEmployeeHoliday(@ParamName("employeeId")int employeeId);

}
