package com.vali.dao.leave;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.enums.ActionType;

/**
 * Created by vali on 15-8-15.
 */

@NameSpace("LeaveApply")
public interface LeaveApplyDao {

    @Action(action = ActionType.QUERY_OBJECT)
    public Integer getLeaveApply();
}
