package com.vali.dao.leave;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.leya.idal.model.PageModel;
import com.vali.po.leave.LeaveApplyPO;
import com.vali.po.leave.LeaveApplyQueryPO;
import com.vali.po.leave.LeaveAuditQueryPO;
import com.vali.po.leave.LeaveRevokeQueryPO;

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

    @Action(action = ActionType.QUERY_OBJECT)
    public LeaveApplyPO getApplyDetailByApplyId(@ParamName("applyId") int applyId);

    @Action(action = ActionType.PAGE)
    public PageModel pageLeaveApplyRecords(@ParamName("po") LeaveApplyQueryPO po,
                                           @ParamName("pageNo") int pageNo,
                                           @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.PAGE)
    public PageModel pageWait4AduitApplys(@ParamName("po") LeaveAuditQueryPO queryPO,
                                          @ParamName("pageNo") int pageNo,
                                          @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.UPDATE)
    public int updateApplyStatus(@ParamName("applyId") int applyId,
                                 @ParamName("auditStatus") int auditStatus);

    @Action(action = ActionType.PAGE)
    public PageModel pageCanRevokeApplys(@ParamName("po") LeaveRevokeQueryPO queryPO,
                                         @ParamName("pageNo") int pageNo,
                                         @ParamName("pageSize") int pageSize);


}
