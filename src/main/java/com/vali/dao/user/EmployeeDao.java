package com.vali.dao.user;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.user.EmployeePO;

/**
 * Created by vali on 15-8-15.
 */
@NameSpace("Employee")
public interface EmployeeDao {

    @Action(action = ActionType.QUERY_OBJECT)
    public EmployeePO getEmployeeByEmail(@ParamName("email") String email);

    @Action(action = ActionType.QUERY_OBJECT)
    public EmployeePO getEmployeeByID(@ParamName("id") int id);

}
