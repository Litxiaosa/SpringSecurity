package com.springsecurityjwtdemo.demo.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: AuthenticationEntryPoint 用来解决匿名用户访问需要权限才能访问的资源时的异常
 * @author: xiaosa
 * @create: 2020-05-02 16:28
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException accessDeniedException) throws IOException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");

        httpServletResponse.getWriter().println("{\"code\":401,\"message\":\"你没有携带 token 或者 token 无效！\",\"data\":\"\"}");
        httpServletResponse.getWriter().flush();
    }
}
