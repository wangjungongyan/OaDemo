package com.vali.service.purchase.impl;

import com.vali.dao.purchase.PurchaseApplyDao;
import com.vali.dto.purchase.PurchaseAttaDTO;
import com.vali.dto.purchase.PurchaseDTO;
import com.vali.dto.purchase.PurchaseItemDTO;
import com.vali.po.purchase.PurchaseAttaPO;
import com.vali.po.purchase.PurchaseItemPO;
import com.vali.po.purchase.PurchasePO;
import com.vali.service.purchase.PurchaseService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by vali on 15-9-17.
 */

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Setter
    @Resource(name = "purchaseApplyDao")
    private PurchaseApplyDao purchaseApplyDao;

    private BeanCopier DTO2ENTITY4Purchase = BeanCopier.create(PurchaseDTO.class, PurchasePO.class, false);

    private BeanCopier DTO2ENTITY4PurchaseAtta = BeanCopier.create(PurchaseAttaDTO.class, PurchaseAttaPO.class, false);

    private BeanCopier DTO2ENTITY4PurchaseItem = BeanCopier.create(PurchaseItemDTO.class, PurchaseItemPO.class, false);

    @Override public boolean savePurchaseApply(PurchaseDTO purchaseDTO) {

        PurchasePO po = new PurchasePO();
        DTO2ENTITY4Purchase.copy(purchaseDTO, po, null);
        int purchaseId = purchaseApplyDao.savePurchaseApply(po);

        List<PurchaseAttaDTO> attaDTOS = purchaseDTO.getPurchaseAttaDTOs();
        if (!CollectionUtils.isEmpty(attaDTOS)) {
            for (PurchaseAttaDTO attaDTO : attaDTOS) {
                PurchaseAttaPO attaPO = new PurchaseAttaPO();
                DTO2ENTITY4PurchaseAtta.copy(attaDTO, attaPO, null);
                attaPO.setPurchaseId(purchaseId);
                purchaseApplyDao.savePurchaseAtta(attaPO);
            }
        }

        List<PurchaseItemDTO> itemDTOs = purchaseDTO.getPurchaseItemDTOs();
        if (!CollectionUtils.isEmpty(itemDTOs)) {
            for (PurchaseItemDTO itemDTO : itemDTOs) {
                PurchaseItemPO itemPO = new PurchaseItemPO();
                DTO2ENTITY4PurchaseItem.copy(itemDTO, itemPO, null);
                itemPO.setPurchaseId(purchaseId);
                purchaseApplyDao.savePurchaseApplyItem(itemPO);
            }
        }

        return true;
    }
}
