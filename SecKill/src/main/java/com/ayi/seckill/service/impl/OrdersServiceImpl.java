package com.ayi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ayi.seckill.domain.Orders;
import com.ayi.seckill.service.OrdersService;
import com.ayi.seckill.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author HuiBBao
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2022-10-24 21:16:13
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




