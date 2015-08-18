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
     * 离职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    /**
     * 员工状态
     */
    private Integer status;


    private Date addTime;

    private Date updateTime;

    /**
     * 直接上级
     */
    private EmployeeDTO manager;

    /**
     * HR
     */
    private EmployeeDTO hr;



}
