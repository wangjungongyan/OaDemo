package com.vali.service.purchase;

import com.vali.dto.purchase.PurchaseOperateDetailDTO;

import java.util.List;

/**
 * Created by vali on 15-9-17.
 */
public interface PurchaseOperateService {

    public boolean savePurchaseOperate(PurchaseOperateDetailDTO detailDTO);

    public List<PurchaseOperateDetailDTO> getPurchaseOperates(int purchaseId);

}
