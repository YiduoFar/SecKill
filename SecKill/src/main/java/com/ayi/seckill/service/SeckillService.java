package com.ayi.seckill.service;

import com.ayi.seckill.domain.User;
import com.ayi.seckill.vo.RespBean;

/**
 * @author HuiBBao
 * @create 2022/10/24 10:57
 */
public interface SeckillService {
    RespBean doSecKill(Long goodId, User user);
}
