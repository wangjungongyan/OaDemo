package com.vali.dao.leave;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.leave.LeaveApplyPO;

import java.util.List;

/**
 * Created by vali on 15-8-15.
 */

@NameSpace("LeaveApply")
public interface LeaveApplyDao {

    @Action(action = ActionType.QUERY_OBJECT)
    public Integer getLeaveApply();

    @Action(action = ActionType.INSERT)
    public int saveLeaveApplyDetail(@ParamName("apply") LeaveApplyPO apply);

    @Action(action = ActionType.QUERY_LIST)
    public List<LeaveApplyPO> getLeaveApplyRecords(@ParamName("applicantID") int applicantID);
}
