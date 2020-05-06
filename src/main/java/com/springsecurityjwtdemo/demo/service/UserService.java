package com.springsecurityjwtdemo.demo.service;

import com.springsecurityjwtdemo.demo.dto.LoginUser;
import com.springsecurityjwtdemo.demo.entity.JwtUser;

import java.io.IOException;

/**
 * @Descript jwt 相关数据库的操作
 * @author: xiaosa
 * @create: 2020-05-03 12:36
 */
public interface UserService {

    /**
     * 登录并获得 token
     *
     * @return: java.lang.String
     * @author: xiaosa
     * @date: 2020/5/5 14:24
     * @version 1.0
     */
    String login(LoginUser loginUser);
}
