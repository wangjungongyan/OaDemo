package com.vali.service.purchase.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.purchase.PurchaseApplyDao;
import com.vali.dao.purchase.PurchaseReimburseDao;
import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.po.purchase.PurchaseReimbursePO;
import com.vali.service.purchase.PurchaseReimburseService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 15/9/16.
 */
@Service("purchaseReimburseService")
public class PurchaseReimburseServiceImpl implements PurchaseReimburseService{
    @Resource(name = "purchaseReimburseDao")
    PurchaseReimburseDao purchaseReimburseDao;
    @Resource(name = "employeeService")
    EmployeeService employeeService;
    @Override
    public void savePurchaseReimburseApply(PurchaseReimburseDTO purchaseReimburseDTO) {
        PurchaseReimbursePO purchaseReimburse = new PurchaseReimbursePO();
        BeanUtils.copyProperties(purchaseReimburseDTO,purchaseReimburse);
        purchaseReimburseDao.savePurchaseReimburse(purchaseReimburse);
    }

    @Override
    public PageModel queryMyPurchaseReimburse(EmployeeDTO loginUser, int pageNo, int limit) {
        PageModel pageModel = purchaseReimburseDao.pagePurchaseReimburses(loginUser.getId(), pageNo, limit);
        List<PurchaseReimbursePO> purchaseReimbursePOs =(List<PurchaseReimbursePO>) pageModel.getRecords();
        if(CollectionUtils.isEmpty(purchaseReimbursePOs)){
            return null;
        }
        List<PurchaseReimburseDTO> dtoList = new ArrayList<PurchaseReimburseDTO>();
        for (PurchaseReimbursePO po:purchaseReimbursePOs){
            PurchaseReimburseDTO dto = new PurchaseReimburseDTO();
            BeanUtils.copyProperties(po,dto);
            dto.setApplicantName(employeeService.loadEmployee(dto.getApplicant()).getChineseName());
            dtoList.add(dto);
        }
        pageModel.setRecords(dtoList);
        return pageModel;
    }
}
