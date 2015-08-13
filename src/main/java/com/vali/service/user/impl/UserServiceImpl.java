package com.vali.service.user.impl;

import com.vali.service.user.dto.LoginVerifyDTO;
import com.vali.service.user.remote.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by vali on 15-8-12.
 */

@Component("userService")
public class UserServiceImpl implements UserService ,InitializingBean{

    @Override public LoginVerifyDTO verifyLoginUser(String userName, String password) {
        return null;
    }

    @Override public void afterPropertiesSet() throws Exception {
        System.out.print("ddd");
    }
}
