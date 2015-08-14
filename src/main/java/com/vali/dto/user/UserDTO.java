package com.vali.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * Created by vali on 15-8-14.
 */

@Data
public class UserDTO {

    private int userId;

    private String userName;

    private int departMent;

    private int role;

    private String email;

    private String phone;

    private Date joinDate;

    private UserDTO manger;

}
