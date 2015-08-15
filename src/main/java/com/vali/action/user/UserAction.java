package com.vali.action.user;

import com.vali.dto.user.EmployeeDTO;
import com.vali.service.user.remote.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

        return new ModelAndView("user/addUserIndex");
    }

    @RequestMapping("/user/addUser")
    public ModelAndView addUser(EmployeeDTO userDTO) {
        int userId = employeeService.addEmployee(userDTO);
        if (userId == 0) {
            //保存失败
            Map model = new HashMap();
            model.put("user", userDTO);
            return new ModelAndView("user/addUserIndex", model);
        }
        //保存成功，直接定位到列表页
        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping("/user/list")
    public String userList(EmployeeDTO userDTO){

        return "user/list";
    }

    @RequestMapping("/user/detail")
    public String userInfoDetail(EmployeeDTO userDTO){
        return "user/detail";
    }

    @RequestMapping("/user/updateUserIndex")
    public String updateUserIndex(EmployeeDTO userDTO){
        return "user/updateUserIndex";
    }

    @RequestMapping("/user/updateUser")
    public String updateUser(EmployeeDTO userDTO){
        return "user/updateUser";
    }







}
