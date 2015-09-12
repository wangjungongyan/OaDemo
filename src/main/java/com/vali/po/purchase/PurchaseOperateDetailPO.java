package com.vali.po.purchase;

import lombok.Data;

import java.util.Date;

/**
 * 请购申请审批流水PO
 */

@Data
public class PurchaseOperateDetailPO {

    private int id;

    //请购申请id
    private int purchaseId;

    //操作类型
    private int operateType;

    //描述
    private String description;

    //审批时间
    private Date operateTime;

    //操作人
    private int operateId;

    private Date addTime;

    private Date updateTime;

}
