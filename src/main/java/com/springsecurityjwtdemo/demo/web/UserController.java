package com.springsecurityjwtdemo.demo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @describe:
 * @author: xiaosa
 * @date：2020-05-06 17:31
 * @version：1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/list")
    public String list(){
        //比如：查询用户列表
        return "用户列表";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update")
    public void update(){
        System.out.println("修改用户");
    }
}
