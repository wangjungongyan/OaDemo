package com.vali.bo;

import com.vali.dto.user.EmployeeDTO;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Created by vali on 15-8-15.
 */
public class LoginBO {

    private static ThreadLocal<EmployeeDTO> users = new ThreadLocal<EmployeeDTO>();

    public static void setLoginUser(EmployeeDTO user) {
        users.set(user);
    }

    public static EmployeeDTO getLoginUser() {
        return users.get();
    }

    public static void removeLoginUser() {
        users.remove();
    }

    public static String encryptPassword(String originalPassword) {

        if (originalPassword == null) {
            return null;
        }

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return new BASE64Encoder().encode(md5.digest(originalPassword.getBytes("utf-8")));
        } catch (Exception e) {
            throw new IllegalArgumentException("对密码加密失败");
        }

    }

    public static void main(String []aa){
        String s ="123456";
        System.out.print(encryptPassword(s));
    }

}
