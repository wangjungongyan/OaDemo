package com.vali.service.purchase.impl;

import com.leya.idal.model.PageModel;
import com.vali.dao.purchase.PurchaseApplyDao;
import com.vali.dao.purchase.PurchaseReimburseDao;
import com.vali.dao.purchase.PurchaseReimburseOperateDao;
import com.vali.dto.purchase.ApplyBuyBaoxiaoDetailDTO;
import com.vali.dto.purchase.PurchaseReimburseDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.DefaultApproveStatusEnum;
import com.vali.po.purchase.ApplyBuyBaoxiaoDetailPO;
import com.vali.po.purchase.PurchaseReimbursePO;
import com.vali.service.purchase.PurchaseReimburseService;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    PurchaseReimburseOperateDao purchaseReimburseOperateDao;
    @Override
    public void savePurchaseReimburseApply(PurchaseReimburseDTO purchaseReimburseDTO) {
        PurchaseReimbursePO purchaseReimburse = new PurchaseReimbursePO();
        BeanUtils.copyProperties(purchaseReimburseDTO,purchaseReimburse);
        purchaseReimburseDao.savePurchaseReimburse(purchaseReimburse);
        StringBuffer desc = new StringBuffer();
        String chinaeseName=employeeService.loadEmployee(purchaseReimburse.getApplicant()).getChineseName();
        desc.append(chinaeseName).append("提交申请");
        savePurchaseReimburseApproveDetail(purchaseReimburse.getId(), purchaseReimburseDTO.getApplicant(), desc.toString());
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

    @Override
    public PageModel queryPreApprove(EmployeeDTO loginUser, Integer pageNo, int limit) {
        PageModel pageModel = purchaseReimburseDao.pagePurchaseReimbursePreApproves(loginUser.getId(), pageNo, limit);
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

    @Override
    public PageModel queryMyApprove(EmployeeDTO loginUser, Integer pageNo, int limit) {
        //读流水表
        PageModel pageModel = purchaseReimburseDao.pagePurchaseReimbursePreApproves(loginUser.getId(), pageNo, limit);
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

    @Override
    public PurchaseReimburseDTO queryPurchaseReimburse(Integer applyId) {
        List<PurchaseReimbursePO> pos = purchaseReimburseDao.getPurchaseReimburse(applyId);
        if(CollectionUtils.isEmpty(pos)){
            return null;
        }
        PurchaseReimburseDTO dto = new PurchaseReimburseDTO();
        BeanUtils.copyProperties(pos.get(0),dto);
        dto.setApplicantName(employeeService.loadEmployee(dto.getApplicant()).getChineseName());
        return dto;
    }

    @Override
    public void doPurchaseReimburseApprove(EmployeeDTO loginUser, Integer applyId, Integer status, String mark, String approveType) {
        if(approveType.equals("mng")){
            purchaseReimburseDao.updateMngApproveStatus(applyId,status);
            if(status==DefaultApproveStatusEnum.PASSED.getStatusCode()){
                purchaseReimburseDao.updatePurchaseReimburseStatus(applyId,DefaultApproveStatusEnum.DOING.getStatusCode());
            }
            if(status==DefaultApproveStatusEnum.REJECT.getStatusCode()){
                purchaseReimburseDao.updatePurchaseReimburseStatus(applyId,DefaultApproveStatusEnum.REJECT.getStatusCode());
            }
            StringBuffer desc = new StringBuffer();
            desc.append(loginUser.getChineseName()).append("审批").append(DefaultApproveStatusEnum.getApproveStatusEnum(status).getStatusName());
            savePurchaseReimburseApproveDetail(applyId, loginUser.getId(), desc.toString());
        }
        if(approveType.equals("finnace")){
            purchaseReimburseDao.updateFinnaceApproveStatus(applyId,status);
            if(status==DefaultApproveStatusEnum.PASSED.getStatusCode()){
                purchaseReimburseDao.updatePurchaseReimburseStatus(applyId,DefaultApproveStatusEnum.PASSED.getStatusCode());
            }
            if(status==DefaultApproveStatusEnum.REJECT.getStatusCode()){
                purchaseReimburseDao.updatePurchaseReimburseStatus(applyId,DefaultApproveStatusEnum.REJECT.getStatusCode());
            }
            StringBuffer desc = new StringBuffer();
            desc.append(loginUser.getChineseName()).append("审批").append(DefaultApproveStatusEnum.getApproveStatusEnum(status).getStatusName());
            savePurchaseReimburseApproveDetail(applyId, loginUser.getId(), desc.toString());
        }
    }

    public void savePurchaseReimburseApproveDetail(Integer purchaseReimburseId,Integer operatorid,String description){
        ApplyBuyBaoxiaoDetailPO po = new ApplyBuyBaoxiaoDetailPO();
        po.setPurchaseReimburseId(purchaseReimburseId);
        po.setOperator(operatorid);
        po.setDescription(description);
        po.setAddTime(new Date());
        purchaseReimburseOperateDao.savePurchaseReimburseOperate(po);
    }

    public List<ApplyBuyBaoxiaoDetailDTO> getPurchaseReimburseOperatRecords(int purchaseReimburseId){
        List<ApplyBuyBaoxiaoDetailPO> pos = purchaseReimburseOperateDao.getPurchaseReimburseOperatRecords(purchaseReimburseId);
        if(CollectionUtils.isEmpty(pos)){
            return null;
        }
        List<ApplyBuyBaoxiaoDetailDTO> dtos = new ArrayList<ApplyBuyBaoxiaoDetailDTO>();
        for (ApplyBuyBaoxiaoDetailPO po : pos){
            ApplyBuyBaoxiaoDetailDTO dto = new ApplyBuyBaoxiaoDetailDTO();
            BeanUtils.copyProperties(po,dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
