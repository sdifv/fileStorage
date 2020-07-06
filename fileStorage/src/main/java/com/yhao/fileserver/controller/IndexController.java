package com.yhao.fileserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/","index"})
    public String toLogin(){
        return "user/login";
    }
}
