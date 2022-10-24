package com.ayi.seckill.service.impl;
import java.util.Date;

import com.ayi.seckill.domain.*;
import com.ayi.seckill.mapper.GoodMapper;
import com.ayi.seckill.mapper.OrdersMapper;
import com.ayi.seckill.mapper.SeckillOrderMapper;
import com.ayi.seckill.service.GoodService;
import com.ayi.seckill.service.SeckillGoodService;
import com.ayi.seckill.service.SeckillOrderService;
import com.ayi.seckill.service.SeckillService;
import com.ayi.seckill.vo.GoodVo;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author HuiBBao
 * @create 2022/10/24 10:57
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    @Autowired
    GoodService goodService;

    @Autowired
    SeckillGoodService seckillGoodService;

    @Autowired
    SeckillOrderService seckillOrderService;


    public RespBean checkBeforeSecKill(Long goodId, User user) {
        // 判断库存是否为0
        QueryWrapper<Good> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("good_stock").eq("id", goodId);
        int goodStock = goodMapper.selectOne(queryWrapper).getGoodStock();
        System.out.println("goodStock:" + goodStock);
        if (goodStock == 0) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK_ERROR);
        }
        // 判断用户是否重复秒杀
        QueryWrapper<SeckillOrder> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("good_id", goodId).eq("user_id", user.getId());
        SeckillOrder seckillOrder = seckillOrderMapper.selectOne(queryWrapper1);
        if (!ObjectUtils.isEmpty(seckillOrder)) {
            return RespBean.error(RespBeanEnum.REPEAT_KILL_ERROR);
        }
        return null;
    }


    /**
     * 秒杀
     * @param goodId
     * @param user
     * @return
     */
    @Override
    public RespBean doSecKill(Long goodId, User user) {
        // 秒杀前的判断
        RespBean respBean = checkBeforeSecKill(goodId, user);
        if (!ObjectUtils.isEmpty(respBean)) {
            return respBean;
        }
        // 获取秒杀商品信息
        GoodVo goodVo = goodService.findGoodVoByGoodId(goodId);
        /**
         * 秒杀
         */
        // 秒杀商品表减库存
        SeckillGood seckillGood = seckillGoodService.getOne(new QueryWrapper<SeckillGood>().eq("good_id", goodId));
        seckillGood.setStockCount(seckillGood.getStockCount() - 1);
        seckillGoodService.updateById(seckillGood);
        // 生成订单
        Orders order = new Orders();
        order.setUserId(user.getId());
        order.setGoodId(goodVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodName(goodVo.getGoodName());
        order.setGoodCount(1);
        order.setGoodPrice(goodVo.getSeckillPrice());
        order.setOrderChannel(1);
        order.setOrderStatus(0);
        order.setCreateDate(new Date());
        ordersMapper.insert(order);
        // 生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodId(goodVo.getId());
//        seckillOrderService.save(seckillOrder);
        seckillOrderMapper.insert(seckillOrder);
        return RespBean.success(order);
    }
}
