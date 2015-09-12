package com.vali.dao.purchase;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.purchase.PurchaseOperateDetailPO;

import java.util.List;

/**
 * Created by vali on 15-9-12.
 */
@NameSpace("PurchaseOperate")
public interface PurchaseOperateDao {

    @Action(action = ActionType.INSERT)
    public int savePurchaseOperate(@ParamName("operateDetail") PurchaseOperateDetailPO operateDetail);

    @Action(action = ActionType.QUERY_LIST)
    public List<PurchaseOperateDetailPO> getPurchaseOperatRecords(@ParamName("purchaseId") int purchaseId);

}
