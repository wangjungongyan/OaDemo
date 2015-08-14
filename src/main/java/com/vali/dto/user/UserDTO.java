package com.vali.dto.user;

import com.vali.enums.user.DepartmentEnum;
import com.vali.enums.user.RoleEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by vali on 15-8-14.
 */

@Data
public class UserDTO {

    private Integer id;

    private String userName;

    private Integer departMent;

    private Integer role;

    private String email;

    private String phone;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;

    private UserDTO manger;

    public static UserDTO getVali(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUserName("王俊");
        userDTO.setEmail("vali@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.MANAGE.getType());
        userDTO.setManger(getKerith());
        return userDTO;
    }
    public static UserDTO getKerith(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setUserName("樊帅");
        userDTO.setEmail("kerith@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.BOSS.getType());
        userDTO.setManger(null);
        return userDTO;
    }

}
