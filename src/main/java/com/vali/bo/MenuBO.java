package com.vali.bo;

import com.vali.dto.menu.FirstMenuDTO;
import com.vali.dto.menu.SecondMenuDTO;
import com.vali.enums.user.RoleEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vali on 15-8-15.
 */
public class MenuBO {

    private static Map<Integer, List<FirstMenuDTO>> roleMenu = new HashMap<Integer, List<FirstMenuDTO>>(3);

    public static List<FirstMenuDTO> getMenuByRole(int role) {
        initMenu4Roles();
        return roleMenu.get(role);
    }

    private static void initMenu4Roles() {
        if (roleMenu.size() == 0){
            synchronized(MenuBO.class){

                if (roleMenu.size() > 0){
                    return;
                }

                initMenu4Normal();
                initMenu4Manage();
                initMenu4Hr();
            }
        }

    }

    private static void initMenu4Normal() {

        FirstMenuDTO firstMenuDTO1 = new FirstMenuDTO();
        firstMenuDTO1.setHref("oneMenuId_001");
        firstMenuDTO1.setName("我的请假");

        SecondMenuDTO secondMenuDTO1 = new SecondMenuDTO();
        secondMenuDTO1.setName("我要申请");
        secondMenuDTO1.setHref("/leave");
        secondMenuDTO1.setIndex(1005);

        SecondMenuDTO secondMenuDTO2 = new SecondMenuDTO();
        secondMenuDTO2.setName("我的申请");
        secondMenuDTO2.setHref("/leave/myLeaveApply");
        secondMenuDTO2.setIndex(1006);

        SecondMenuDTO secondMenuDTO3 = new SecondMenuDTO();
        secondMenuDTO3.setName("我要审核");
        secondMenuDTO3.setHref("/leave/audit");
        secondMenuDTO3.setIndex(1007);

        SecondMenuDTO secondMenuDTO4 = new SecondMenuDTO();
        secondMenuDTO4.setName("我的审核");
        secondMenuDTO4.setHref("/leave/myAudit");
        secondMenuDTO4.setIndex(1008);

        List<SecondMenuDTO> secondMenus1 = new ArrayList<SecondMenuDTO>(3);
        secondMenus1.add(secondMenuDTO1);
        secondMenus1.add(secondMenuDTO2);
        secondMenus1.add(secondMenuDTO3);
        secondMenus1.add(secondMenuDTO4);

        firstMenuDTO1.setSecondMenus(secondMenus1);

        FirstMenuDTO firstMenuDTO22 = new FirstMenuDTO();
        firstMenuDTO22.setHref("oneMenuId_002");
        firstMenuDTO22.setName("用户管理");

        SecondMenuDTO secondMenuDTO11 = new SecondMenuDTO();
        secondMenuDTO11.setName("添加新员工");
        secondMenuDTO11.setHref("/user/addUserIndex");
        secondMenuDTO11.setIndex(1008);

        SecondMenuDTO secondMenuDTO22 = new SecondMenuDTO();
        secondMenuDTO22.setName("员工查询");
        secondMenuDTO22.setHref("/user/list");
        secondMenuDTO22.setIndex(1009);

        List<SecondMenuDTO> secondMenus22 = new ArrayList<SecondMenuDTO>(3);
        secondMenus22.add(secondMenuDTO11);
        secondMenus22.add(secondMenuDTO22);

        firstMenuDTO22.setSecondMenus(secondMenus22);

        List<FirstMenuDTO> menus = new ArrayList<FirstMenuDTO>(5);
        menus.add(firstMenuDTO1);
        menus.add(firstMenuDTO22);

        roleMenu.put(RoleEnum.NOMALR.getType(), menus);
    }

    private static void initMenu4Manage() {
        initMenu4Normal();
    }

    private static void initMenu4Hr() {
        initMenu4Normal();
    }

}
