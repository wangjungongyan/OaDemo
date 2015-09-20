package com.vali.service.purchase.impl;

import com.leya.idal.model.PageModel;
import com.vali.bo.UploadBO;
import com.vali.dao.purchase.PurchaseApplyDao;
import com.vali.dto.purchase.*;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.purchase.PurchaseAuditStatusEnum;
import com.vali.enums.purchase.PurchaseBuyTypeEnum;
import com.vali.po.purchase.*;
import com.vali.service.purchase.PurchaseService;
import com.vali.service.user.remote.EmployeeService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vali on 15-9-17.
 */

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Setter
    @Resource(name = "purchaseApplyDao")
    private PurchaseApplyDao purchaseApplyDao;

    @Setter
    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    private BeanCopier DTO2ENTITY4Purchase = BeanCopier.create(PurchaseDTO.class, PurchasePO.class, false);

    private BeanCopier ENTITY2DTO4Purchase = BeanCopier.create(PurchasePO.class, PurchaseDTO.class, false);

    private BeanCopier DTO2ENTITY4PurchaseAtta = BeanCopier.create(PurchaseAttaDTO.class, PurchaseAttaPO.class, false);

    private BeanCopier ENTITY2DTO4PurchaseAtta = BeanCopier.create(PurchaseAttaPO.class, PurchaseAttaDTO.class, false);

    private BeanCopier DTO2ENTITY4PurchaseItem = BeanCopier.create(PurchaseItemDTO.class, PurchaseItemPO.class, false);

    private BeanCopier ENTITY2DTO4PurchaseItem = BeanCopier.create(PurchaseItemPO.class, PurchaseItemDTO.class, false);

    private BeanCopier DTO2ENTITY4PurchaseApplyQuery = BeanCopier.create(PurchaseApplyQueryDTO.class,
                                                                         PurchaseApplyQueryPO.class,
                                                                         false);

    private BeanCopier DTO2ENTITY4PurchaseAuditQuery = BeanCopier.create(PurchaseAuditQueryDTO.class,
                                                                         PurchaseAuditQueryPO.class,
                                                                         false);

    private BeanCopier PAGECopier = BeanCopier.create(PageModel.class, PageModel.class, false);

    @Override public int savePurchaseApply(PurchaseDTO purchaseDTO) {

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

        return purchaseId;
    }

    @Override public PageModel pagePurchaseApplys(PurchaseApplyQueryDTO queryDTO, int pageNo,
                                                  int pageSize) {
        PurchaseApplyQueryPO queryPO = new PurchaseApplyQueryPO();
        DTO2ENTITY4PurchaseApplyQuery.copy(queryDTO, queryPO, null);

        PageModel queryModel = purchaseApplyDao.pagePurchaseApplyRecords(queryPO, pageNo, pageSize);
        return convertEntityModel2DTOModel(queryModel);
    }

    @Override public PageModel pagePurchaseHisAudits(PurchaseAuditQueryDTO queryDTO, int pageNo, int pageSize) {
        PurchaseAuditQueryPO po = new PurchaseAuditQueryPO();
        DTO2ENTITY4PurchaseAuditQuery.copy(queryDTO, po, null);

        PageModel queryModel = purchaseApplyDao.pagePurchaseHisAuditRecords(po, pageNo, pageSize);
        return convertEntityModel2DTOModel(queryModel);
    }

    @Override public boolean auditPurchaseApply(int purchaseId, int mngApproveStatus) {
        int updated = purchaseApplyDao.updateMngApproveStatus(purchaseId, mngApproveStatus);
        return (updated > 0);
    }

    @Override public PurchaseDTO getPurchaseApply(int purchaseId) {
        PurchasePO purchasePO = purchaseApplyDao.getPurchaseApplyRecord(purchaseId);

        if (purchasePO == null) {
            return new PurchaseDTO();
        }

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        ENTITY2DTO4Purchase.copy(purchasePO, purchaseDTO, null);
        fillApplicantName(purchaseDTO);
        fillBuyTypeName(purchaseDTO);
        fillMngApproveStatusName(purchaseDTO);

        List<PurchaseAttaPO> attaPOs = purchaseApplyDao.getPurchaseAttas(purchaseId);
        purchaseDTO.setPurchaseAttaDTOs(convertAttaPOs2AttaDTOs(attaPOs));

        List<PurchaseItemPO> itemPOs = purchaseApplyDao.getPurchaseApplyItems(purchaseId);
        purchaseDTO.setPurchaseItemDTOs(convertItemPOs2DTOS(itemPOs));

        return purchaseDTO;
    }

    private List<PurchaseItemDTO> convertItemPOs2DTOS(List<PurchaseItemPO> itemPOs) {
        if (CollectionUtils.isEmpty(itemPOs)) {
            return new ArrayList<PurchaseItemDTO>(1);
        }

        List<PurchaseItemDTO> itemDTOs = new ArrayList<PurchaseItemDTO>();
        for (PurchaseItemPO itemPO : itemPOs) {
            PurchaseItemDTO itemDTO = new PurchaseItemDTO();
            ENTITY2DTO4PurchaseItem.copy(itemPO, itemDTO, null);
            itemDTOs.add(itemDTO);
        }

        return itemDTOs;
    }

    private List<PurchaseAttaDTO> convertAttaPOs2AttaDTOs(List<PurchaseAttaPO> attaPOs) {
        if (CollectionUtils.isEmpty(attaPOs)) {
            return new ArrayList<PurchaseAttaDTO>(1);
        }

        List<PurchaseAttaDTO> attaDTOs = new ArrayList<PurchaseAttaDTO>();
        for (PurchaseAttaPO attaPO : attaPOs) {
            PurchaseAttaDTO attaDTO = new PurchaseAttaDTO();
            ENTITY2DTO4PurchaseAtta.copy(attaPO, attaDTO, null);
            this.fillCompleteFilePath(attaDTO);
            attaDTOs.add(attaDTO);
        }

        return attaDTOs;
    }

    private void fillCompleteFilePath(PurchaseAttaDTO attaDTO) {
        String filePath = attaDTO.getFilePath();
        attaDTO.setFilePath("http://lloa.com" + UploadBO.getRealPath() + filePath);
    }

    private PageModel convertEntityModel2DTOModel(PageModel queryModel) {
        PageModel result = new PageModel();
        PAGECopier.copy(queryModel, result, null);

        if (queryModel == null || CollectionUtils.isEmpty(queryModel.getRecords())) {
            return result;
        }

        List<PurchaseDTO> resultDTOS = new ArrayList<PurchaseDTO>(queryModel.getRecords().size());
        for (PurchasePO record : (List<PurchasePO>) queryModel.getRecords()) {
            PurchaseDTO dto = new PurchaseDTO();
            ENTITY2DTO4Purchase.copy(record, dto, null);

            fillApplicantName(dto);
            fillBuyTypeName(dto);
            fillMngApproveStatusName(dto);

            resultDTOS.add(dto);
        }

        result.setRecords(resultDTOS);
        return result;
    }

    private void fillBuyTypeName(PurchaseDTO dto) {
        dto.setBuyTypeName(PurchaseBuyTypeEnum.matchType(dto.getBuyType()).getDesc());
    }

    private void fillMngApproveStatusName(PurchaseDTO dto) {
        dto.setMngApproveStatusName(PurchaseAuditStatusEnum.match(dto.getMngApproveStatus()).getDesc());
    }

    private void fillApplicantName(PurchaseDTO dto) {
        EmployeeDTO employeeDTO = employeeService.loadEmployee(dto.getApplicant());
        dto.setApplicantName(employeeDTO.getChineseName());
    }

}
