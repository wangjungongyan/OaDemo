package com.vali.dto.purchase;

import lombok.Data;

import java.util.Date;

/**
 * 请购报销审批流水DTO
 */

@Data
public class ApplyBuyBaoxiaoDetailDTO {

    private int id;

    //报销id
    private int purchaseReimburseId;

    //操作人
    private int operator;

    //操作类型
    private int operateType;

    //描述
    private String description;

    //审批时间
    private Date operateTime;

    private Date addTime;

    private Date updateTime;

}
