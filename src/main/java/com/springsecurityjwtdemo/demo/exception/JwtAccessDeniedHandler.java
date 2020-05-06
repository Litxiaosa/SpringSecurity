package com.springsecurityjwtdemo.demo.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 * @author: xiaosa
 * @create: 2020-05-02 16:19
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {


    /**
     * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException{
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");

        httpServletResponse.getWriter().println("{\"code\":403,\"message\":\"你没有权限访问呀！\",\"data\":\"\"}");
        httpServletResponse.getWriter().flush();

    }
}
