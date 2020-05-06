package com.springsecurityjwtdemo.demo.consts;


/**
 * 常量
 * @author: xiaosa
 * @create: 2020-05-02 17:58
 */
public class SecurityConstants {

    /**
     * 登录的地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /**
     * JWT token defaults
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";


    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
   // public static final long EXPIRATION = 60 * 60L;
    public static final long EXPIRATION = 60L;

    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
   // public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    public static final long EXPIRATION_REMEMBER = 5 * 60L ;


    private SecurityConstants() {
    }

}
