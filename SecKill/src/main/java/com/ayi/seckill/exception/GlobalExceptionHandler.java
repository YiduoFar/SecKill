package com.ayi.seckill.exception;

import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理类
 *
 * @author HuiBBao
 * @create 2022/10/4 14:31
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e) {
        if (e instanceof GlobalException || e instanceof AccessDeniedException) {
            log.info("进来处理类GlobalException了。。");
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        } else if (e instanceof InternalAuthenticationServiceException) {
            InternalAuthenticationServiceException ex = (InternalAuthenticationServiceException) e;
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        } else if (e instanceof BadCredentialsException) {
//            用户名或密码不正确
            BadCredentialsException ex = (BadCredentialsException) e;
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        e.printStackTrace();
        return RespBean.error(RespBeanEnum.ERROR);
    }


}
