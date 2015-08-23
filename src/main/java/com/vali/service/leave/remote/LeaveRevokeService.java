package com.vali.service.leave.remote;

import com.leya.idal.model.PageModel;
import com.vali.dto.leave.LeaveRevokeQueryDTO;

/**
 * Created by fanshuai on 15/8/13.
 */
public interface LeaveRevokeService {

    public PageModel pageCanRevokeApplys(LeaveRevokeQueryDTO queryDTO,
                                         int pageNo,
                                         int pageSize);

    public boolean revokeApply(int applyId, String suggest);

}
