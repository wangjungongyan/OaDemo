package com.vali.action.user;

import com.vali.dto.user.UserDTO;
import com.vali.service.user.remote.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 15/8/14.
 */
@Controller
public class UserAction {
    @Resource(name = "userService")
    private UserService userService;
    @RequestMapping("/user/addUserIndex")
    public ModelAndView addUserIndex(UserDTO userDTO){

        return new ModelAndView("user/addUserIndex");
    }

    @RequestMapping("/user/addUser")
    public ModelAndView addUser(UserDTO userDTO){
        int userId = userService.addUser(userDTO);
        if(userId==0){
            //保存失败
            return new ModelAndView("user/addUserIndex");
        }
        //保存成功，直接定位到列表页
        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping("/user/list")
    public String userList(UserDTO userDTO){

        return "user/list";
    }

    @RequestMapping("/user/detail")
    public String userInfoDetail(UserDTO userDTO){
        return "user/detail";
    }

    @RequestMapping("/user/updateUserIndex")
    public String updateUserIndex(UserDTO userDTO){
        return "user/updateUserIndex";
    }

    @RequestMapping("/user/updateUser")
    public String updateUser(UserDTO userDTO){
        return "user/updateUser";
    }







}
