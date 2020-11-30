package com.atheima.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PostMapping("success")
    public String loginSuccess(){
        return "success";
    }

    @GetMapping("/r/r1")
    public String r1(){
        return "访问资源r1 success";
    }

    @GetMapping("/r/r2")
    public String r2(){
        return "访问资源r2 success";
    }
}
