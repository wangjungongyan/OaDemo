package com.vali.service.purchase.impl;

import com.vali.dao.purchase.PurchaseOperateDao;
import com.vali.dto.purchase.PurchaseOperateDetailDTO;
import com.vali.enums.purchase.PurchaseOperateTypeEnum;
import com.vali.po.purchase.PurchaseOperateDetailPO;
import com.vali.service.purchase.PurchaseOperateService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vali on 15-9-20.
 */
@Service("purchaseOperateService")
public class PurchaseOperateServiceImpl implements PurchaseOperateService {

    @Setter
    @Resource(name = "purchaseOperateDao")
    private PurchaseOperateDao purchaseOperateDao;

    private BeanCopier DTO2ENTITY = BeanCopier.create(PurchaseOperateDetailDTO.class, PurchaseOperateDetailPO.class,
                                                      false);

    private BeanCopier ENTITY2DTO = BeanCopier.create(PurchaseOperateDetailPO.class, PurchaseOperateDetailDTO.class,
                                                      false);

    @Override public boolean savePurchaseOperate(PurchaseOperateDetailDTO detailDTO) {
        PurchaseOperateDetailPO po = new PurchaseOperateDetailPO();
        DTO2ENTITY.copy(detailDTO, po, null);

        return purchaseOperateDao.savePurchaseOperate(po) > 0;
    }

    @Override public List<PurchaseOperateDetailDTO> getPurchaseOperates(int purchaseId) {
        List<PurchaseOperateDetailPO> pos = purchaseOperateDao.getPurchaseOperatRecords(purchaseId);

        if (CollectionUtils.isEmpty(pos)) {
            return new ArrayList<PurchaseOperateDetailDTO>(1);
        }

        List<PurchaseOperateDetailDTO> result = new ArrayList<PurchaseOperateDetailDTO>(pos.size());
        for (PurchaseOperateDetailPO po : pos) {
            PurchaseOperateDetailDTO dto = new PurchaseOperateDetailDTO();
            ENTITY2DTO.copy(po, dto, null);
            dto.setOperateTypeDesc(PurchaseOperateTypeEnum.matchType(dto.getOperateType()).getDesc());
            result.add(dto);
        }

        return result;
    }
}
