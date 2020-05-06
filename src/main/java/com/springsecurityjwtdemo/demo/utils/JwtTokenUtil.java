package com.springsecurityjwtdemo.demo.utils;

import com.springsecurityjwtdemo.demo.consts.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

/**
 * jwt token工具类
 *
 * jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 *
 * @author: xiaosa
 * @create: 2020-05-02 18:32
 */
public class JwtTokenUtil {

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);


    /**
     * 生成 token
     * @return
     */
    public static String createToken(String userName, List<String> roles, boolean rememberMe) {

        long expiration = rememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .claim("role", roles)
                .setIssuer("SnailClimb")
                .setIssuedAt(createdDate)
                .setSubject(userName)
                .setExpiration(expirationDate)
                .compact();

        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
    }


    /**
     * 获取用户名
     */
    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }


    /**
     * 验证 token 是否失效
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }


    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
