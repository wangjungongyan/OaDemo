package com.vali.service.user.impl;

import com.vali.bo.LoginBO;
import com.vali.bo.MenuBO;
import com.vali.dao.user.EmployeeDao;
import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.user.RoleEnum;
import com.vali.po.user.EmployeePO;
import com.vali.service.user.remote.EmployeeService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
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

    private BeanCopier ENTITY_2_DTO = BeanCopier.create(EmployeePO.class, EmployeeDTO.class, false);

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
        //TODO add
        return 0;
    }

    @Override
    public int updateEmployee(EmployeeDTO userDTO) {
        //TODO update
        return 0;
    }

    @Override
    public EmployeeDTO loadEmployee(int userId) {
        EmployeePO po = employeeDao.getEmployeeByID(userId);
        EmployeeDTO dto = new EmployeeDTO();
        ENTITY_2_DTO.copy(po, dto, null);
        return dto;
    }

    @Override
    public EmployeeDTO loadEmployee(String email) {

        EmployeePO po = employeeDao.getEmployeeByEmail(email);

        EmployeeDTO dto = new EmployeeDTO();
        ENTITY_2_DTO.copy(po, dto, null);
        dto.setManger(getManger(po.getMangerId()));
        dto.setHr(getHr());

        return dto;
    }

    private EmployeeDTO getManger(int mangerId){

        EmployeePO mangerPO =employeeDao.getEmployeeByID(mangerId);

        EmployeeDTO mangerDTO = new EmployeeDTO();
        ENTITY_2_DTO.copy(mangerPO, mangerDTO, null);

        return mangerDTO;
    }

    private EmployeeDTO getHr(){

        List<EmployeePO> hrs =employeeDao.getEmployeeByRole(RoleEnum.HR.getType());

        EmployeeDTO hrrDTO = new EmployeeDTO();
        ENTITY_2_DTO.copy(hrs.get(0), hrrDTO, null);

        return hrrDTO;
    }

    @Override
    public boolean hasSameEmployee(EmployeeDTO userDTO) {
        if (userDTO.getEmail().equals(loadEmployee(userDTO.getEmail()))) {
            return true;
        }
        return false;
    }

}
