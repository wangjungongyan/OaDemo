package com.vali.dto.purchase;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 请购申请DTO
 */

@Data
public class PurchaseDTO {

    private int id;

    //申请人
    private int applicant;

    //申请人部门
    private String dept;

    //申请时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyTime;

    //请购类型
    private int buyType;

    //最终购买人
    private int buyUser;

    //审批上级
    private int manager;

    //申请总价
    private BigDecimal extendedPrice;

    //上级审批状态
    private int mngApproveStatus;

    //请购申请条目
    private List<PurchaseItemDTO> purchaseItemDTOs;

    //请购申请附件
    private List<PurchaseAttaDTO> purchaseAttaDTOs;

    private Date addTime;

    private Date updateTime;

}
