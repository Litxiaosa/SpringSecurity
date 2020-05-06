package com.springsecurityjwtdemo.demo.dao;

import com.springsecurityjwtdemo.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Descript: 员工
 * @author: xiaosa
 * @create: 2020-05-03 14:46
 */
@Mapper
public interface UserMapper {


    /**
     * 根据用户名称查询用户
     * @param userName
     * @return
     */
    User getUserByUserName(@Param("userName") String userName);
}
