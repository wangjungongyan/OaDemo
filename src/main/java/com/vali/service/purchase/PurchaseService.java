package com.vali.service.purchase;

import com.leya.idal.model.PageModel;
import com.vali.dto.purchase.PurchaseApplyQueryDTO;
import com.vali.dto.purchase.PurchaseAuditQueryDTO;
import com.vali.dto.purchase.PurchaseDTO;

/**
 * Created by vali on 15-9-17.
 */
public interface PurchaseService {

    public int savePurchaseApply(PurchaseDTO purchaseDTO);

    public PageModel pagePurchaseApplys(PurchaseApplyQueryDTO queryDTO, int pageNo, int pageSize);

    public PageModel pagePurchaseAudits(PurchaseAuditQueryDTO queryDTO, int pageNo, int pageSize);

    public PageModel pagePurchaseHisAudits(PurchaseAuditQueryDTO queryDTO, int pageNo, int pageSize);

    public boolean auditPurchaseApply(int purchaseId, int mngApproveStatus);

    public PurchaseDTO getPurchaseApply(int purchaseId);

}
