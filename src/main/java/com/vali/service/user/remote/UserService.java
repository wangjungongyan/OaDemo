package com.vali.service.user.remote;

import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;

import java.util.List;

/**
 * Created by vali on 15-8-12.
 */
public interface UserService {

    public LoginVerifyDTO verifyLoginUser(String userName, String password);

    public List<FirstMenuDTO> getUserMenus(int userId);

}
