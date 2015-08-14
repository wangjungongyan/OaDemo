package com.vali.service.user.impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.vali.dto.login.LoginVerifyDTO;
import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.menu.SecondMenuDTO;
import com.vali.dto.user.UserDTO;
import com.vali.service.user.remote.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vali on 15-8-12.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public LoginVerifyDTO verifyLoginUser(String email, String password) {
        LoginVerifyDTO dto = new LoginVerifyDTO();
        UserDTO u=loadUser(email);
        if(u==null){
            dto.setVerify(false);
            return dto;
        }
        if(u.getPassword().equals(password)){
            dto.setVerify(true);
            return dto;
        }
        dto.setVerify(false);
        return dto;
    }

    @Override
    public List<FirstMenuDTO> getUserMenus(int userId) {

        List<FirstMenuDTO> menus = new ArrayList<FirstMenuDTO>(5);

        FirstMenuDTO firstMenuDTO1 = new FirstMenuDTO();
        firstMenuDTO1.setHref("oneMenuId_001");
        firstMenuDTO1.setName("请假");

        SecondMenuDTO secondMenuDTO1 = new SecondMenuDTO();
        secondMenuDTO1.setName("假期申请");
        secondMenuDTO1.setHref("/apply");
        secondMenuDTO1.setIndex(1005);

        SecondMenuDTO secondMenuDTO2 = new SecondMenuDTO();
        secondMenuDTO2.setName("假期申请历史");
        secondMenuDTO2.setHref("/applyList");
        secondMenuDTO2.setIndex(1006);

        SecondMenuDTO secondMenuDTO3 = new SecondMenuDTO();
        secondMenuDTO3.setName("假期审核");
        secondMenuDTO3.setHref("/audit");
        secondMenuDTO3.setIndex(1007);

        List<SecondMenuDTO> secondMenus1 = new ArrayList<SecondMenuDTO>(3);
        secondMenus1.add(secondMenuDTO1);
        secondMenus1.add(secondMenuDTO2);
        secondMenus1.add(secondMenuDTO3);

        firstMenuDTO1.setSecondMenus(secondMenus1);

        FirstMenuDTO firstMenuDTO2 = new FirstMenuDTO();
        firstMenuDTO2.setHref("oneMenuId_002");
        firstMenuDTO2.setName("用户管理");

        SecondMenuDTO secondMenuDTO11 = new SecondMenuDTO();
        secondMenuDTO11.setName("添加新员工");
        secondMenuDTO11.setHref("/user/addUserIndex");
        secondMenuDTO11.setIndex(1008);

        SecondMenuDTO secondMenuDTO22 = new SecondMenuDTO();
        secondMenuDTO22.setName("员工查询");
        secondMenuDTO22.setHref("/user/list");
        secondMenuDTO22.setIndex(1009);



        List<SecondMenuDTO> secondMenus2 = new ArrayList<SecondMenuDTO>(3);
        secondMenus2.add(secondMenuDTO11);
        secondMenus2.add(secondMenuDTO22);

        firstMenuDTO2.setSecondMenus(secondMenus2);

        menus.add(firstMenuDTO1);
        menus.add(firstMenuDTO2);

        return menus;
    }

    @Override
    public int addUser(UserDTO userDTO) {
        return 0;
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        return 0;
    }

    @Override
    public UserDTO loadUser(int userId) {
        if(userId==(UserDTO.getVali().getId())){
            return UserDTO.getVali();
        }
        if(userId==(UserDTO.getKerith().getId())){
            return UserDTO.getKerith();
        }
        return null;
    }

    @Override
    public UserDTO loadUser(String email) {
        if(email.equals(UserDTO.getVali().getEmail())){
            return UserDTO.getVali();
        }
        if(email.equals(UserDTO.getKerith().getEmail())){
            return UserDTO.getKerith();
        }
        return null;
    }

    @Override
    public boolean hasSameUser(UserDTO userDTO) {
        if(userDTO.getEmail().equals(loadUser(userDTO.getEmail()))) {
            return true;
        }
        return false;
    }

}
