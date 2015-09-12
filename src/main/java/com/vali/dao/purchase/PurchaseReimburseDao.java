package com.vali.dao.purchase;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.leya.idal.model.PageModel;
import com.vali.po.purchase.PurchasePO;
import com.vali.po.purchase.PurchaseReimbursePO;

import java.util.List;

/**
 * Created by vali on 15-9-12.
 */
@NameSpace("PurchaseReimburse")
public interface PurchaseReimburseDao {

    @Action(action = ActionType.INSERT)
    public int savePurchaseReimburse(@ParamName("purchaseReimburse") PurchaseReimbursePO purchaseReimburse);

    @Action(action = ActionType.QUERY_LIST)
    public List<PurchasePO> getPurchaseReimburse(@ParamName("applicant") int applicant);

    @Action(action = ActionType.PAGE)
    public PageModel pagePurchaseReimburses(@ParamName("applicant") int applicant,
                                            @ParamName("pageNo") int pageNo,
                                            @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.PAGE)
    public PageModel pagePurchaseReimburseManagerApproves(@ParamName("manager") int manager,
                                                          @ParamName("pageNo") int pageNo,
                                                          @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.PAGE)
    public PageModel pagePurchaseReimburseFinnaceApproves(@ParamName("finnaceUserId") int finnaceUserId,
                                                          @ParamName("pageNo") int pageNo,
                                                          @ParamName("pageSize") int pageSize);

    @Action(action = ActionType.UPDATE)
    public int updateMngApproveStatus(@ParamName("reimburseId") int reimburseId,
                                      @ParamName("mngApproveStatus") int mngApproveStatus);

    @Action(action = ActionType.UPDATE)
    public int updateFinnaceApproveStatus(@ParamName("reimburseId") int reimburseId,
                                          @ParamName("finnaceApproveStatus") int finnaceApproveStatus);
}
