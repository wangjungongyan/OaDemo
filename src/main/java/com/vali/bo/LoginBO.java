package com.vali.bo;

import com.vali.dto.user.UserDTO;

/**
 * Created by vali on 15-8-15.
 */
public class LoginBO {

    private static ThreadLocal<UserDTO> users = new ThreadLocal<UserDTO>();

    public static void setLoginUser(UserDTO user) {
        users.set(user);
    }

    public static UserDTO getLoginUser() {
        return users.get();
    }

    public static void removeLoginUser() {
        users.remove();
    }

}
