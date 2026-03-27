package com.efficiency.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component // 声明该类为Spring的一个组件，可以被自动扫描管理
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret; // 从配置文件读取JWT的密钥

    @Value("${jwt.expire-hours}")
    private Integer expireHours; // 从配置文件读取JWT令牌过期时间（单位：小时）

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @param username 用户名
     * @param roleCode 角色代码
     * @return 生成的JWT字符串
     */
    public String generateToken(Long userId, String username, String roleCode) {
        Date now = new Date(); // 当前时间
        Date expireDate = new Date(now.getTime() + expireHours * 3600L * 1000L); // 计算过期时间
        // 构建JWT：设置主体、用户ID、角色、签发时间、过期时间，然后使用HS256和密钥签名
        return Jwts.builder()
                .setSubject(username) // 设置JWT的主题为用户名
                .claim("userId", userId) // 添加自定义字段 userId
                .claim("roleCode", roleCode) // 添加自定义字段 roleCode
                .setIssuedAt(now) // 设置签发时间
                .setExpiration(expireDate) // 设置过期时间
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // 使用密钥和HS256算法签名
                .compact(); // 生成JWT字符串
    }

    /**
     * 解析JWT令牌，获取claims
     * @param token JWT字符串
     * @return JWT的负载部分（Claims）
     */
    public Claims parseToken(String token) {
        // 构建JWT解析器，使用密钥进行解析，得到负载Claims
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey()) // 设置用于校验签名的密钥
                .build()
                .parseClaimsJws(token) // 解析JWT
                .getBody(); // 获取负载部分
    }

    /**
     * 判断JWT是否已过期
     * @param token JWT字符串
     * @return true表示已过期
     */
    public boolean isExpired(String token) {
        // 获取JWT的过期时间，并与当前时间比较
        return parseToken(token).getExpiration().before(new Date());
    }

    /**
     * 获取JWT签名所需的密钥对象
     * @return SecretKey对象
     */
    private SecretKey getSecretKey() {
        // 根据字符串密钥生成HMAC SHA密钥
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
