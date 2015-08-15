package com.vali.service.user.impl;

import com.vali.bo.LoginBO;
import com.vali.bo.MenuBO;
import com.vali.dao.user.EmployeeDao;
import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.po.user.EmployeePO;
import com.vali.service.user.remote.EmployeeService;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by vali on 15-8-12.
 */

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Setter
    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    @Override
    public LoginVerifyDTO verifyLoginUser(String loginName, String password) {

        LoginVerifyDTO loginVerifyDTO = new LoginVerifyDTO();

        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            loginVerifyDTO.setVerify(false);
            loginVerifyDTO.setMsg("用户名或密码为空");
            return loginVerifyDTO;
        }

        EmployeePO employeePO = employeeDao.getEmployeeByEmail(loginName);

        if (employeePO == null) {
            loginVerifyDTO.setVerify(false);
            loginVerifyDTO.setMsg("用户不存在");
            return loginVerifyDTO;
        }

        String encryptPassword = LoginBO.encryptPassword(password);
        boolean isLegal = encryptPassword.equals(employeePO.getPassword());

        if (isLegal) {
            loginVerifyDTO.setVerify(true);
            return loginVerifyDTO;
        }

        loginVerifyDTO.setVerify(false);
        loginVerifyDTO.setMsg("密码不合法");
        return loginVerifyDTO;
    }

    @Override
    public List<FirstMenuDTO> getEmployeeMenus(String loginName) {
        EmployeePO employeePO = employeeDao.getEmployeeByEmail(loginName);
        return MenuBO.getMenuByRole(employeePO.getRole());
    }

    @Override
    public int addEmployee(EmployeeDTO userDTO) {
        return 0;
    }

    @Override
    public int updateEmployee(EmployeeDTO userDTO) {
        return 0;
    }

    @Override
    public EmployeeDTO loadEmployee(int userId) {
        if (userId == (EmployeeDTO.getVali().getId())) {
            return EmployeeDTO.getVali();
        }
        if (userId == (EmployeeDTO.getKerith().getId())) {
            return EmployeeDTO.getKerith();
        }
        return null;
    }

    @Override
    public EmployeeDTO loadEmployee(String email) {
        if (email.equals(EmployeeDTO.getVali().getEmail())) {
            return EmployeeDTO.getVali();
        }
        if (email.equals(EmployeeDTO.getKerith().getEmail())) {
            return EmployeeDTO.getKerith();
        }
        return null;
    }

    @Override
    public boolean hasSameEmployee(EmployeeDTO userDTO) {
        if (userDTO.getEmail().equals(loadEmployee(userDTO.getEmail()))) {
            return true;
        }
        return false;
    }

}