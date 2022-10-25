package com.ayi.seckill.filter;

import com.ayi.seckill.domain.LoginUser;
import com.ayi.seckill.exception.GlobalException;
import com.ayi.seckill.utils.JwtUtil;
import com.ayi.seckill.utils.RedisCache;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

/**
 * @author HuiBBao
 * @create 2022/10/5 13:28
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("authorization");
        log.info("请求中携带的token:" + token);
        if (!StringUtils.hasText(token)) {
            // 放行
            log.info("放行了不携带token的请求...");
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String mobile;
        try {
            Claims claims = JwtUtil.parseJWT(token.split(" ")[1]);
            mobile = claims.getSubject();
        } catch (Exception e) {
            log.info("token解析出错");
            throw new RuntimeException("token非法");
        }
        // 从redis中获取用户信息
        String redisKey = "login:" + mobile;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            log.info("用户登录超时");
            throw new RuntimeException("登录超时");
        }
        // 存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
        log.info("放行了..来自" + loginUser.getUsername() + "携带token的请求");
    }
}