package com.yhao.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 20:37 2020/4/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        if(!StringUtils.isEmpty(username) && password.equals("123456")){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或密码错误!");
            return "index";
        }
    }
}
