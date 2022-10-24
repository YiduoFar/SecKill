package com.ayi.seckill.controller;

import com.ayi.seckill.domain.LoginUser;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.service.SeckillService;
import com.ayi.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SeckillService seckillService;

    @PostMapping("/doSecKill")
    public RespBean doSecKill(Long goodId) {
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        return seckillService.doSecKill(goodId, user);
    }

}
