package com.yhao.fileserver.controller;

import com.yhao.fileserver.entity.User;
import com.yhao.fileserver.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 前往登录
    @GetMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }

    // 注册方法
    @PostMapping("register")
    public String register(User user, Model model){
        try{
            System.out.println(user);
            userService.register(user);
            model.addAttribute("msg","注册成功，请登录");
            return "/user/register";
        }catch (Exception e){
            model.addAttribute("msg","注册失败");
            return "/user/register";
        }

    }

    // 前往登录
    @GetMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    // 登录方法
    @PostMapping("login")
    public String login(User user, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try{
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("user",(User)subject.getPrincipal());
            return "redirect:/file/all";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名或密码错误");
            return "user/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","用户名或密码错误");
            return "user/login";
        }
    }

    //注销方法
    @GetMapping("logout")
    public String logout(){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }
}
