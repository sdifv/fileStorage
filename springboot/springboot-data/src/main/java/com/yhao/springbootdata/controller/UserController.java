package com.yhao.springbootdata.controller;

import com.yhao.springbootdata.mapper.UserMapper;
import com.yhao.springbootdata.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for(User user:userList){
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("addUser")
    public String addUser(){
        userMapper.addUser(new User(1007,"lx","12345"));
        return "addUser OK!";
    }

    @GetMapping("deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(1007);
        return "deleteUser OK!";
    }

    @GetMapping("updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(1007,"lx","1234567"));
        return "updateUser OK!";
    }
}
