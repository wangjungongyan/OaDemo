package com.vali.po.purchase;

import lombok.Data;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class PurchaseApplyQueryPO {

    private Date startTime;

    private Date endTime;

    private int auditStatus;

    private int applicantID;

    private int buyType;

}
