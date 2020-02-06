package com.hezhe.security.distributed.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 贺哲
 * @2020-02-06 21:21
 */
@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('p:test1')")
    public String test1() {
        return "获取资源";
    }
}
