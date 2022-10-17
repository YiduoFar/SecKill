package com.ayi.seckill.exception.impl;

import com.alibaba.fastjson.JSON;
import com.ayi.seckill.exception.GlobalException;
import com.ayi.seckill.utils.WebUtil;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HuiBBao
 * @create 2022/10/5 14:39
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        RespBean result = RespBean.error(RespBeanEnum.HTTP_STATUS_UNAUTHORIZED);
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}