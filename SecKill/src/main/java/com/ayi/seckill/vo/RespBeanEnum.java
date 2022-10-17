package com.ayi.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author HuiBBao
 * @create 2022/10/2 9:49
 */
@ToString
@AllArgsConstructor
@Getter
public enum RespBeanEnum {
    // 通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务器异常"),
    HTTP_STATUS_FORBIDDEN(403001, "权限不足"),
    HTTP_STATUS_UNAUTHORIZED(403002, "认证失败，请重新登录"),
    // 登录模块
    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    TOKEN_ERROR(500213, "token失效，请重新登录"),
    LOGIN_TIMEOUT_ERROR(500214, "用户未登录"),
    // 注册模块
    REG_ERROR(500310, "手机号已被注册"),
    // 搜索模块
    NULL_DATA_ERROR(500400, "无数据"),
    ;
    private final Integer code;
    private final String message;
}
