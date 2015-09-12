package com.vali.dto.purchase;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 请购报销DTO
 */

@Data
public class PurchaseReimburseDTO {

    private int id;

    //请购申请id
    private int purchaseId;

    //部门
    private String dept;

    //报销时间
    private Date applyTime;

    //收款人姓名
    private String bankAccountName;

    //银行名
    private String bankName;

    //银行账号
    private String bankAccountNO;

    //付款金额
    private BigDecimal payAccount;

    //币种
    private String currency;

    //要求付款时间
    private Date payDate;

    //具体付款内容文件地址
    private String descriptionFile;

    //具体付款内容描述
    private String description;

    //审批经理
    private int manager;

    //经理审批状态
    private int mngApproveStatus;

    //报销审核状态
    private int status;

    //审批财务
    private int finnaceUserId;

    //财务审批状态
    private int finnaceApproveStatus;

    private Date addTime;

    private Date updateTime;

}
