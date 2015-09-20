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
        if (roleMenu.size() == 0) {
            synchronized (MenuBO.class) {

                if (roleMenu.size() > 0) {
                    return;
                }

                initMenu4Normal();
                initMenu4Manage();
                initMenu4Hr();
            }
        }

    }
    private static FirstMenuDTO preparePurchaseManage(){
        FirstMenuDTO firstMenuDTO1 = new FirstMenuDTO();
        firstMenuDTO1.setHref("oneMenuId_004");
        firstMenuDTO1.setName("请购管理");

        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("我要请购申请","/purchase/applyIndex",4001));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("我的请购申请","/purchase/myApply",4002));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("我要请购审批","/purchase/myAudits",4003));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("我的请购审批","/purchase/myHisAudits",4004));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("请购报销申请","/purchase/reimburseApplyIndex",4005));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("我的请购报销","/purchase/myReimburseApply",4006));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("请购报销审核","/purchase/reimburseApproveIndex",4007));
        firstMenuDTO1.addSecondMenuDTO(getSecondMenuDTO("请购报销查询","/purchase/myReimburseApprove",4008));
        return firstMenuDTO1;
    }
    //请假管理
    private static FirstMenuDTO prepareLeaveManage() {

        FirstMenuDTO firstMenuDTO1 = new FirstMenuDTO();
        firstMenuDTO1.setHref("oneMenuId_001");
        firstMenuDTO1.setName("请假管理");

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
        secondMenuDTO3.setHref("/leave/wait2Audit");
        secondMenuDTO3.setIndex(1007);

        SecondMenuDTO secondMenuDTO4 = new SecondMenuDTO();
        secondMenuDTO4.setName("我的审核");
        secondMenuDTO4.setHref("/leave/myAudits");
        secondMenuDTO4.setIndex(1008);

        SecondMenuDTO secondMenuDTO5 = new SecondMenuDTO();
        secondMenuDTO5.setName("我的假期");
        secondMenuDTO5.setHref("/leave/myHolidays");
        secondMenuDTO5.setIndex(1009);

        List<SecondMenuDTO> secondMenus1 = new ArrayList<SecondMenuDTO>(3);
        secondMenus1.add(secondMenuDTO1);
        secondMenus1.add(secondMenuDTO2);
        secondMenus1.add(secondMenuDTO3);
        secondMenus1.add(secondMenuDTO4);
        secondMenus1.add(secondMenuDTO5);

        firstMenuDTO1.setSecondMenus(secondMenus1);

        return firstMenuDTO1;
    }

    //用户管理
    private static FirstMenuDTO prepareUserManage() {

        FirstMenuDTO firstMenuDTO22 = new FirstMenuDTO();
        firstMenuDTO22.setHref("oneMenuId_002");
        firstMenuDTO22.setName("用户管理");

        SecondMenuDTO secondMenuDTO11 = new SecondMenuDTO();
        secondMenuDTO11.setName("添加新员工");
        secondMenuDTO11.setHref("/user/addUserIndex");
        secondMenuDTO11.setIndex(2001);

        SecondMenuDTO secondMenuDTO22 = new SecondMenuDTO();
        secondMenuDTO22.setName("员工查询");
        secondMenuDTO22.setHref("/user/list");
        secondMenuDTO22.setIndex(2002);

        List<SecondMenuDTO> secondMenus22 = new ArrayList<SecondMenuDTO>(3);
        secondMenus22.add(secondMenuDTO11);
        secondMenus22.add(secondMenuDTO22);

        firstMenuDTO22.setSecondMenus(secondMenus22);

        return firstMenuDTO22;
    }

    //系统管理
    private static FirstMenuDTO prepareSyetemManage() {

        FirstMenuDTO firstMenuDTO22 = new FirstMenuDTO();
        firstMenuDTO22.setHref("sysManage_03");
        firstMenuDTO22.setName("系统管理");

        SecondMenuDTO secondMenuDTO22 = new SecondMenuDTO();
        secondMenuDTO22.setName("法定假日");
        secondMenuDTO22.setHref("/sys/holidaySettingIndex");
        secondMenuDTO22.setIndex(3001);


        firstMenuDTO22.addSecondMenuDTO(secondMenuDTO22);
        firstMenuDTO22.addSecondMenuDTO(getSecondMenuDTO("初始化下年年假","/sys/initYearHoliday",3002));
        return firstMenuDTO22;
    }

    private static SecondMenuDTO getSecondMenuDTO(String title,String url,int id){
        SecondMenuDTO secondMenuDTO23 = new SecondMenuDTO();
        secondMenuDTO23.setName(title);
        secondMenuDTO23.setHref(url);
        secondMenuDTO23.setIndex(id);
        return secondMenuDTO23;
    }
    private static void initMenu4Normal() {

        List<FirstMenuDTO> menus = new ArrayList<FirstMenuDTO>(3);
        menus.add(prepareLeaveManage());
        menus.add(preparePurchaseManage());
        roleMenu.put(RoleEnum.NOMALR.getType(), menus);
    }

    private static void initMenu4Manage() {

        List<FirstMenuDTO> menus = new ArrayList<FirstMenuDTO>(3);
        menus.add(prepareLeaveManage());
        menus.add(preparePurchaseManage());
        roleMenu.put(RoleEnum.MANAGE.getType(), menus);
    }

    private static void initMenu4Hr() {

        List<FirstMenuDTO> menus = new ArrayList<FirstMenuDTO>(3);

        FirstMenuDTO leaveManage = prepareLeaveManage();
        List<SecondMenuDTO> leaveSecondMenus = leaveManage.getSecondMenus();

        SecondMenuDTO secondMenuDTO5 = new SecondMenuDTO();
        secondMenuDTO5.setName("我要销假");
        secondMenuDTO5.setHref("/leave/revoke");

        leaveSecondMenus.add(secondMenuDTO5);

        menus.add(leaveManage);
        menus.add(prepareUserManage());
        menus.add(preparePurchaseManage());
        menus.add(prepareSyetemManage());
        roleMenu.put(RoleEnum.HR.getType(), menus);
    }

}
