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

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门代码
     */
    private Integer departMent;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 入职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;

    /**
     * 直接上级
     */
    private UserDTO manger;

    /**
     * 员工状态
     */
    private int status;

    /**
     * 离职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureTime;

    public static UserDTO getVali() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("王俊");
        userDTO.setEmail("vali@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.MANAGE.getType());
        userDTO.setManger(getKerith());
        return userDTO;
    }

    public static UserDTO getKerith() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setName("樊帅");
        userDTO.setEmail("kerith@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.BOSS.getType());
        userDTO.setManger(null);
        return userDTO;
    }

}
