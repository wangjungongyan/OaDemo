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
import com.vali.util.BeanUtilsA;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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
        EmployeePO po = new EmployeePO();
        BeanUtils.copyProperties(userDTO, po);
        po.setPassword(LoginBO.encryptPassword(po.getPassword()));
        po.setMangerId(userDTO.getManger().getId());
        return employeeDao.saveEmployee(po);
    }

    @Override
    public int updateEmployee(EmployeeDTO userDTO) {
        String password = userDTO.getPassword();
        EmployeePO po = employeeDao.getEmployeeByID(userDTO.getId());
        BeanUtilsA.copyPropertiesWithSourcePropertiesNullNotCopy(userDTO, po);
        po.setMangerId(userDTO.getManger().getId());
        if(password!=null){
            po.setPassword(LoginBO.encryptPassword(password));
        }
        return employeeDao.updateEmployee(po);
    }

    @Override
    public EmployeeDTO loadEmployee(int userId) {
        EmployeePO po = employeeDao.getEmployeeByID(userId);
        if(po==null){
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(po,dto);
        //setManager
        EmployeePO managerPo = employeeDao.getEmployeeByID(po.getMangerId());
        if(managerPo!=null) {
            EmployeeDTO managerDto = new EmployeeDTO();
            BeanUtils.copyProperties(managerPo, managerDto);
            dto.setManger(managerDto);
        }
        return dto;
    }

    @Override
    public EmployeeDTO loadEmployee(String email) {

        EmployeePO po = employeeDao.getEmployeeByEmail(email);
        if(po==null){
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(po, dto);
        //setManager
        EmployeePO managerPo = employeeDao.getEmployeeByID(po.getMangerId());
        if(managerPo!=null){
            EmployeeDTO managerDto = new EmployeeDTO();
            BeanUtils.copyProperties(managerPo,managerDto);
            dto.setManger(managerDto);
        }
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
