package com.vali.dto.purchase;

import lombok.Data;

import java.util.Date;

/**
 * 请购申请附件PO
 */

@Data
public class PurchaseAttaDTO {

    private int id;

    //请购申请id
    private int purchaseId;

    //附件名
    private String fileName;

    //附件路径
    private String filePath;

    private Date addTime;

    private Date updateTime;

}
