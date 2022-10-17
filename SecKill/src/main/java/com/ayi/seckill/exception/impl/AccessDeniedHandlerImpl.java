package com.ayi.seckill.exception.impl;

import com.alibaba.fastjson.JSON;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.ayi.seckill.utils.WebUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HuiBBao
 * @create 2022/10/5 14:33
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        RespBean result = RespBean.error(RespBeanEnum.HTTP_STATUS_FORBIDDEN);
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}