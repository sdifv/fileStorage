package com.yhao.fileserver.controller;

import com.sun.deploy.security.WSeedGenerator;
import com.yhao.fileserver.entity.User;
import com.yhao.fileserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录方法
    @PostMapping("login")
    public String login(User user, HttpSession session){
        System.out.println(user);
        User userDB = userService.login(user);
        if(userDB != null){
            session.setAttribute("user",userDB);
            System.out.println(userDB);
            return "redirect:/file/all";
        }else{
            return "redirect:/index";
        }
    }
}
