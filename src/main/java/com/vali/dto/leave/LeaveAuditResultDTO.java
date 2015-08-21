package com.vali.dto.leave;

import lombok.Data;

/**
 * Created by vali on 15-8-21.
 */

@Data
public class LeaveAuditResultDTO {

    private boolean pass;

    private String msg;

    public LeaveAuditResultDTO(){

    }

    public LeaveAuditResultDTO(boolean pass, String msg) {
        this.pass = pass;
        this.msg = msg;
    }
}
