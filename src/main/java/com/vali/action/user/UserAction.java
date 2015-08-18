package com.vali.action.user;

import com.leya.idal.model.PageModel;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.user.DepartmentEnum;
import com.vali.enums.user.EmployeeStatusEnum;
import com.vali.enums.user.RoleEnum;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 15/8/14.
 */
@Controller
public class UserAction {

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @RequestMapping("/user/addUserIndex")
    public ModelAndView addUserIndex(EmployeeDTO userDTO) {
        Map model = new HashMap();
        model.put("departments", DepartmentEnum.values());
        model.put("roles", RoleEnum.values());
        model.put("manageList",employeeService.queryAllManager());
        return new ModelAndView("user/addUserIndex",model);
    }

    @RequestMapping("/user/addUser")
    public ModelAndView addUser(EmployeeDTO userDTO) {
        if(employeeService.hasSameEmployee(userDTO)){
            Map model = new HashMap();
            model.put("user", userDTO);
            model.put("errorMsg","此用户邮箱已经存在");
            return new ModelAndView("user/addUserIndex", model);
        }
        userDTO.setStatus(EmployeeStatusEnum.ON.getStatus());
        int userId = employeeService.addEmployee(userDTO);
        if (userId == 0) {
            //保存失败
            Map model = new HashMap();
            model.put("user", userDTO);
            model.put("errorMsg","添加失败");
            return new ModelAndView("user/addUserIndex", model);
        }
        //保存成功，直接定位到列表页
        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping("/user/list")
    public ModelAndView userList(EmployeeDTO userDTO,@DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,@DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime,Integer pageNum){
        if(pageNum==null){
            pageNum=1;
        }
        if(pageNum<1){
            pageNum=1;
        }
        PageModel pageModel = employeeService.queryEmployee(userDTO,startTime,endTime,pageNum,20);
        //employeeService.
        Map model = new HashMap();
        model.put("pageModel",pageModel);
        model.put("userDTO",userDTO);
        model.put("startTime",startTime);
        model.put("endTime",endTime);
        return new ModelAndView("user/list",model);
    }

    @RequestMapping("/user/detail")
    public String userInfoDetail(EmployeeDTO userDTO){
        return "user/detail";
    }

    @RequestMapping("/user/updateUserIndex")
    public ModelAndView updateUserIndex(EmployeeDTO userDTO){
        EmployeeDTO employeeDTO = employeeService.loadEmployee(userDTO.getId());
        Map model = new HashMap();
        model.put("user",employeeDTO);
        model.put("departments", DepartmentEnum.values());
        model.put("roles", RoleEnum.values());
        model.put("manageList",employeeService.queryAllManager());
        return new ModelAndView( "user/updateUserIndex",model);
    }

    @RequestMapping("/user/update")
    public String updateUser(EmployeeDTO userDTO){
        employeeService.updateEmployee(userDTO);
        return "redirect:/user/list";
    }
}
