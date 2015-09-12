package com.vali.dao.purchase;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.purchase.ApplyBuyBaoxiaoDetailPO;

import java.util.List;

/**
 * Created by vali on 15-9-12.
 */
@NameSpace("PurchaseReimburseOperate")
public interface PurchaseReimburseOperateDao {

    @Action(action = ActionType.INSERT)
    public int savePurchaseReimburseOperate(
            @ParamName("applyBuyBaoxiaoDetailPO") ApplyBuyBaoxiaoDetailPO applyBuyBaoxiaoDetailPO);

    @Action(action = ActionType.QUERY_LIST)
    public List<ApplyBuyBaoxiaoDetailPO> getPurchaseReimburseOperatRecords(
            @ParamName("purchaseReimburseID") int purchaseReimburseID);

}
