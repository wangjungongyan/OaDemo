package com.vali.dao.user;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.leya.idal.model.PageModel;
import com.vali.dto.user.EmployeeDTO;
import com.vali.po.user.EmployeePO;

import java.util.Date;
import java.util.List;

/**
 * Created by vali on 15-8-15.
 */
@NameSpace("Employee")
public interface EmployeeDao {

    @Action(action = ActionType.QUERY_OBJECT)
    public EmployeePO getEmployeeByEmail(@ParamName("email") String email);

    @Action(action = ActionType.QUERY_OBJECT)
    public EmployeePO getEmployeeByID(@ParamName("id") int id);

    @Action(action = ActionType.QUERY_LIST)
    public List<EmployeePO> getEmployeeByRole(@ParamName("role") int role);

    @Action(action = ActionType.INSERT)
    public int saveEmployee(@ParamName("employee") EmployeePO employeePO);

    @Action(action = ActionType.UPDATE)
    public int updateEmployee(@ParamName("employee") EmployeePO employeePO);

    @Action(action = ActionType.PAGE)
    public PageModel pageQueryEmployee(@ParamName("employee")EmployeeDTO employeeDTO,
                                       @ParamName("startDate") Date startDate,
                                       @ParamName("endDate") Date endDate,
                                       @ParamName("pageNo") int pageNo,
                                       @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.QUERY_LIST)
    List<EmployeePO> queryAllManager(@ParamName("roleList")List<Integer> roleList);

    @Action(action = ActionType.QUERY_LIST)
    List<EmployeePO> queryAllEmployee();
}
