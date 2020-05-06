package com.springsecurityjwtdemo.demo.dto;

import lombok.Data;

/**
 * @describe: 登录 DTO
 * @author: xiaosa
 * @date：2020-05-05 19:31
 * @version：1.0
 */
@Data
public class LoginUser {

    private String userName;

    private String password;

    private Boolean rememberMe;
}
