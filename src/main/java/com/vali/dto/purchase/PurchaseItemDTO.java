package com.vali.dto.purchase;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 请购申请条目DTO
 */

@Data
public class PurchaseItemDTO {

    private int id;

    //请购申请id
    private int purchaseId;

    //物品名称
    private String itemName;

    //数量
    private int quantity;

    //单价
    private BigDecimal unitPrice;

    //币种
    private String currency;

    //总价
    private BigDecimal extendedPrice;

    //预计交货时间
    private Date expDelDate;

    private Date addTime;

    private Date updateTime;

}
