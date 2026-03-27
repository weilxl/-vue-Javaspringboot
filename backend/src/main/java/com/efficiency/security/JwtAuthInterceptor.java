package com.efficiency.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            writeUnauthorized(response, "未登录，请先登录");
            return false;
        }
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            Claims claims = jwtTokenUtil.parseToken(token);
            if (jwtTokenUtil.isExpired(token)) {
                writeUnauthorized(response, "登录已过期，请重新登录");
                return false;
            }
            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();
            String roleCode = claims.get("roleCode", String.class);
            //有了 UserContext：
            //后端不再相信前端传来的 userId，而是只相信 Token 里解析出来的 ID。因为 Token 是加密的，
            // 用户改不了。我们把这个“绝对可信”的 ID 存在 UserContext 里，后续业务只用这个 ID
            UserContext.set(new LoginUser(userId, username, roleCode));
            return true;
        } catch (Exception e) {
            writeUnauthorized(response, "Token无效，请重新登录");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws Exception {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"message\":\"" + message + "\",\"data\":null}");
    }
}
