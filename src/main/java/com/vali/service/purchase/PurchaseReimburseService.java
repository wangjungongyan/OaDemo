package com.vali.service.purchase;

import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;

import java.util.List;

/**
 * Created by fanshuai on 15/9/16.
 */
public interface PurchaseReimburseService {
    void savePurchaseReimburseApply(PurchaseReimburseDTO purchaseReimburseDTO);

    List<PurchaseReimburseDTO> queryMyPurchaseReimburse(EmployeeDTO loginUser, int pageNo, int limit);
}
