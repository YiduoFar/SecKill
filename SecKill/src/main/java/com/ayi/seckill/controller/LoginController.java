package com.ayi.seckill.controller;

import com.ayi.seckill.service.LoginService;
import com.ayi.seckill.vo.LoginVo;
import com.ayi.seckill.vo.RegVo;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author HuiBBao
 * @create 2022/10/2 11:39
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/hello")
    public RespBean hello() {
        return RespBean.success("Hello!!!");
    }

    @GetMapping("/userLogout")
    public RespBean logo() {
        return loginService.logout();
    }

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    @Validated
    public RespBean login(@Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        return loginService.login(loginVo);
    }


    /**
     * 注册
     * @param regVo
     * @return
     */
    @PostMapping("/reg")
    public RespBean reg(@Valid RegVo regVo) {
        log.info(regVo.toString());
        return loginService.reg(regVo);
    }

}
