package com.vali.service.user.remote;

import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.user.EmployeeDTO;

import java.util.List;

/**
 * Created by vali on 15-8-12.
 */
public interface EmployeeService {

    public LoginVerifyDTO verifyLoginUser(String loginName, String password);

    public List<FirstMenuDTO> getEmployeeMenus(String loginName);

    public int addEmployee(EmployeeDTO userDTO);

    public int updateEmployee(EmployeeDTO userDTO);

    /**
     * 根据用户ID查询用户信息，包括他的直接上级
     *
     * @param id
     * @return
     */
    public EmployeeDTO loadEmployee(int id);

    public EmployeeDTO loadEmployee(String email);

    public EmployeeDTO getHr();

    /**
     * 判断是否已经存在相同用户，根据邮箱判断
     *
     * @param userDTO
     * @return
     */
    public boolean hasSameEmployee(EmployeeDTO userDTO);

}
