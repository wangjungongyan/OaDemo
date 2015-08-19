package com.vali.dao.leave;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.leave.LeaveAuditPO;

/**
 * Created by vali on 15-8-15.
 */

@NameSpace("LeaveAudit")
public interface LeaveAuditDao {

    @Action(action = ActionType.QUERY_OBJECT)
    public LeaveAuditPO getAuidtByApplyId(@ParamName("applyId") int applyId);

    @Action(action = ActionType.INSERT)
    public int saveAudit(@ParamName("po") LeaveAuditPO po);

    @Action(action = ActionType.UPDATE)
    public int updateStatusAndSuggest4Hr(@ParamName("applyId") int applyId,
                                         @ParamName("hrAuditSuggest") String hrAuditSuggest,
                                         @ParamName("auditStatus") int auditStatus);

    @Action(action = ActionType.UPDATE)
    public int updateStatusAndSuggest4Manager(@ParamName("applyId") int applyId,
                                         @ParamName("managerAuditSuggest") String managerAuditSuggest,
                                         @ParamName("auditStatus") int auditStatus);

}
