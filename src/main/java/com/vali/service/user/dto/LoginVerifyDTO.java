package com.vali.service.user.dto;

import lombok.Data;

/**
 * Created by vali on 15-8-12.
 */

@Data
public class LoginVerifyDTO {

    /**
     * 验证结果
     */
    private boolean verify;

    /**
     * 验证不通过信息
     */
    private String msg;

}
