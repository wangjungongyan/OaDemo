package com.vali.dao.purchase;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.leya.idal.model.PageModel;
import com.vali.po.purchase.PurchasePO;

/**
 * Created by vali on 15-9-12.
 */
@NameSpace("PurchaseApply")
public interface PurchaseApplyDao {

    @Action(action = ActionType.INSERT)
    public int savePurchaseApply(@ParamName("apply") PurchasePO apply);

    @Action(action = ActionType.UPDATE)
    public int updateMngApproveStatus(@ParamName("purchaseId") int purchaseId,
                                      @ParamName("mngApproveStatus") int mngApproveStatus);

    @Action(action = ActionType.QUERY_OBJECT)
    public PurchasePO getPurchaseApplyRecord(@ParamName("applyId") int applyId);

    @Action(action = ActionType.PAGE)
    public PageModel pagePurchaseApplyRecords(@ParamName("applicant") int applicant,
                                              @ParamName("pageNo") int pageNo,
                                              @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.PAGE)
    public PageModel pagePurchaseApproveRecords(@ParamName("manager") int manager,
                                                @ParamName("pageNo") int pageNo,
                                                @ParamName("pageSize") int pageSize);

}
