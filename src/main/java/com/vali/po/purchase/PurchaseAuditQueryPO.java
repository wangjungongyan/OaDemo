package com.vali.po.purchase;

import lombok.Data;

import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class PurchaseAuditQueryPO {

    private Date startTime;

    private Date endTime;

    private String applyUserName;

    private int applyUserId;

    private int manager;

}
