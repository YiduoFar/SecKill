package com.ayi.seckill.service.impl;

import com.ayi.seckill.domain.LoginUser;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.exception.GlobalException;
import com.ayi.seckill.mapper.UserMapper;
import com.ayi.seckill.service.LoginService;
import com.ayi.seckill.utils.JwtUtil;
import com.ayi.seckill.utils.MD5Util;
import com.ayi.seckill.utils.RedisCache;
import com.ayi.seckill.utils.SaltUtil;
import com.ayi.seckill.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author HuiBBao
 * @create 2022/10/2 12:42
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    @Override
    public RespBean login(LoginVo loginVo) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getMobile(), loginVo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        // 使用userid(mobile)生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String mobile = loginUser.getUser().getId().toString();
        String token = "Bearer " + JwtUtil.createJWT(mobile);

        // authenticate存入redis
        redisCache.setCacheObject("login:" + mobile, loginUser);

        // 把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);

        return RespBean.success(map);
    }

    /**
     * 注册
     *
     * @param regVo
     * @return
     */
    @Override
    public RespBean reg(RegVo regVo) {
        String mobile = regVo.getMobile();
        String password = regVo.getPassword();
        // 根据手机号获取用户
        User user = userMapper.selectById(mobile);
        // 用户已存在
        if (user != null) {
            throw new GlobalException(RespBeanEnum.REG_ERROR);
        }
        // 属性装配
        user = new User();
        user.setId(Long.valueOf(mobile));
        user.setNickname("user_" + mobile);
        // BCrypt加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        if (userMapper.insert(user) != 1) {
            throw new GlobalException(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }

    /**
     * 注销
     * @return
     */
    @Override
    public RespBean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long mobile = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + mobile);
        return RespBean.success();
    }

    /**
     * 检查登录
     * @return
//     */
//    @Override
//    public RespBean checkLogin() {
//
//    }

}
