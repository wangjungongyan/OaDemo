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
public class EmployeeDTO {

    private Integer id;

    /**
     * 姓名
     */
    private String chineseName;

    /**
     * 英文名
     */
    private String firstName;

    /**
     * 英文名
     */
    private String middleName;

    /**
     * 英文名
     */
    private String lastName;

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
     * 员工状态
     */
    private int status;

    /**
     * 离职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    /**
     * 直接上级
     */
    private EmployeeDTO manger;

    public static EmployeeDTO getVali() {
        EmployeeDTO userDTO = new EmployeeDTO();
        userDTO.setId(1);
        userDTO.setChineseName("王俊");
        userDTO.setEmail("vali@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.MANAGE.getType());
        userDTO.setManger(getKerith());
        return userDTO;
    }

    public static EmployeeDTO getKerith() {
        EmployeeDTO userDTO = new EmployeeDTO();
        userDTO.setId(2);
        userDTO.setChineseName("樊帅");
        userDTO.setEmail("kerith@ll.com");
        userDTO.setPassword("123456");
        userDTO.setPhone("18521352927");
        userDTO.setDepartMent(DepartmentEnum.DEV.getType());
        userDTO.setRole(RoleEnum.BOSS.getType());
        userDTO.setManger(null);
        return userDTO;
    }

}
