package com.vali.service.user.remote;

import com.vali.dto.login.LoginVerifyDTO;

/**
 * Created by vali on 15-8-12.
 */
public interface UserService {

    public LoginVerifyDTO verifyLoginUser(String userName, String password);

}
