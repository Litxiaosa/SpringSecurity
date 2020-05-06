package com.springsecurityjwtdemo.demo.filter;

import com.springsecurityjwtdemo.demo.consts.SecurityConstants;
import com.springsecurityjwtdemo.demo.entity.JwtUser;
import com.springsecurityjwtdemo.demo.utils.JwtTokenUtil;
import com.springsecurityjwtdemo.demo.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @Description: 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 * @author: xiaosa
 * @create: 2020-05-02 15:52
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = Logger.getLogger(JwtAuthorizationFilter.class.getName());

    private final UserDetailsServiceImpl userDetailsService;
    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1:从header获取token
        String authToken = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (authToken == null || !authToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
        } else {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }



    /**
     * 获取用户认证信息 Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String authorization) {
        log.info("get authentication");
        String token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtil.isTokenExpired(token);
        if (expiration){
            throw new RuntimeException("token 超时了");
        }else {
            try {
                String userName = JwtTokenUtil.getUsernameByToken(token);
                logger.info("userName:" + userName);
                if (StringUtils.isNotBlank(userName)) {
                    // 这里我们是又从数据库拿了一遍, 避免用户的角色信息有变
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());
                    return userDetails.isEnabled() ? usernamePasswordAuthenticationToken : null;
                }
            } catch (SignatureException | ExpiredJwtException | MalformedJwtException | IllegalArgumentException exception) {
                logger.warning("Request to parse JWT with invalid signature . Detail : " + exception.getMessage());
            }
        }
        return null;
    }
}
