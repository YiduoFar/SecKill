package com.ayi.seckill.controller;

import com.ayi.seckill.domain.LoginUser;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.vo.RespBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuiBBao
 * @create 2022/10/23 20:01
 */
@RestController
public class SeckillController {

    @PostMapping("/doSecKill")
    public RespBean doSecKill(Integer goodId) {
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("goodid"+goodId);
        System.out.println(principal.getUser().toString());
        return null;
    }

}
