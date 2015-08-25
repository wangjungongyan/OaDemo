package com.vali.dao.setting;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.leave.EmployeeHolidayPO;

import java.util.List;

/**
 * Created by vali on 15-8-15.
 */

@NameSpace("EmployeeHoliday")
public interface EmployeeHolidayDao {

    @Action(action = ActionType.QUERY_LIST)
    public List<EmployeeHolidayPO> getEmployeeHoliday(@ParamName("employeeId") int employeeId,
                                                      @ParamName("year") String year);

    @Action(action = ActionType.UPDATE)
    public int decreaseHolidayDay(@ParamName("po") EmployeeHolidayPO po);

    @Action(action = ActionType.UPDATE)
    public int increaseHolidayDay(@ParamName("po") EmployeeHolidayPO po);

    @Action(action = ActionType.QUERY_LIST)
    public List<EmployeeHolidayPO> getEmployeeYearHoliday(@ParamName("employeeId") int employeeId,@ParamName("year")String year,@ParamName("type") int type);

    @Action(action = ActionType.INSERT)
    void saveHolidaySetting(@ParamName("po")EmployeeHolidayPO po);
    @Action(action = ActionType.UPDATE)
    public int updateHolidayOwn(@ParamName("po") EmployeeHolidayPO po);

}
