package com.springsecurityjwtdemo.demo.service.impl;

import com.springsecurityjwtdemo.demo.dao.UserMapper;
import com.springsecurityjwtdemo.demo.entity.JwtUser;
import com.springsecurityjwtdemo.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @Descript: 用户详情信息获取
 * @author: xiaosa
 * @create: 2020-05-03 14:43
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.getUserByUserName(userName);
        return new JwtUser(user);
    }
}
