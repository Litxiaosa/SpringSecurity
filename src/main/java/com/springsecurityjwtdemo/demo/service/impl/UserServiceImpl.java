package com.springsecurityjwtdemo.demo.service.impl;

import com.springsecurityjwtdemo.demo.dao.UserMapper;
import com.springsecurityjwtdemo.demo.dto.LoginUser;
import com.springsecurityjwtdemo.demo.entity.User;
import com.springsecurityjwtdemo.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Descript jwt 相关数据库的操作
 * @author: xiaosa
 * @create: 2020-05-03 13:14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 登录并获得 token
     *
     * @Param LoginUser: 登录实体类
     * @return: java.lang.String
     * @author: xiaosa
     * @date: 2020/5/5 14:24
     * @version 1.0
     */
    @Override
    public String login(LoginUser loginUser) {
        //根据用户名获取用户
        User user = userMapper.getUserByUserName(loginUser.getUserName());
        // 账号不存在
        if (null == user) {
            throw new RuntimeException("账号不存在");
        }
        return "登录成功了！";
    }
}
