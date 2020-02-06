package com.hezhe.security.distributed.uaa.controller;

import com.hezhe.security.distributed.uaa.mapper.UserMapper;
import com.hezhe.security.distributed.uaa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 贺哲
 * @2020-02-06 16:45
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        List<User> user = userMapper.getAllPagerUser();
        return user;
    }
}
