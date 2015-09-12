package com.vali.po.purchase;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 请购申请PO
 */

@Data
public class PurchasePO {

    private int id;

    //申请人
    private int applicant;

    //申请人部门
    private String dept;

    //申请时间
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

    private Date addTime;

    private Date updateTime;

}
