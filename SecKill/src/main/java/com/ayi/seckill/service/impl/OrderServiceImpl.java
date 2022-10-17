package com.ayi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ayi.seckill.domain.Order;
import com.ayi.seckill.service.OrderService;
import com.ayi.seckill.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author HuiBBao
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-10-14 21:51:59
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




