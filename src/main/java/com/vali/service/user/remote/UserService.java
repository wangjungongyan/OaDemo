package com.vali.service.user.remote;

import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.user.UserDTO;

import java.util.List;

/**
 * Created by vali on 15-8-12.
 */
public interface UserService {

    public LoginVerifyDTO verifyLoginUser(String email, String password);

    public List<FirstMenuDTO> getUserMenus(int userId);

    public int addUser(UserDTO userDTO);


    public int updateUser(UserDTO userDTO);

    /**
     * 根据用户ID查询用户信息，包括他的直接上级
     * @param userId
     * @return
     */
    public UserDTO loadUser(int userId);

    public UserDTO loadUser(String email);

    /**
     * 判断是否已经存在相同用户，根据邮箱判断
     * @param userDTO
     * @return
     */
    public boolean hasSameUser(UserDTO userDTO);

}
