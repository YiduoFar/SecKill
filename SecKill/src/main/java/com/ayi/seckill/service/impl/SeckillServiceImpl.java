package com.ayi.seckill.service.impl;

import java.util.Date;
import java.util.List;

import com.ayi.seckill.domain.*;
import com.ayi.seckill.mapper.GoodMapper;
import com.ayi.seckill.mapper.OrdersMapper;
import com.ayi.seckill.mapper.SeckillGoodMapper;
import com.ayi.seckill.mapper.SeckillOrderMapper;
import com.ayi.seckill.service.GoodService;
import com.ayi.seckill.service.SeckillGoodService;
import com.ayi.seckill.service.SeckillOrderService;
import com.ayi.seckill.service.SeckillService;
import com.ayi.seckill.utils.RedisCache;
import com.ayi.seckill.vo.GoodVo;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author HuiBBao
 * @create 2022/10/24 10:57
 */
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    @Autowired
    SeckillGoodMapper seckillGoodMapper;

    @Autowired
    GoodService goodService;

    @Autowired
    SeckillGoodService seckillGoodService;

    @Autowired
    SeckillOrderService seckillOrderService;

    @Autowired
    RedisCache redisCache;

    //平台事务管理器
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    //事务的定义
    @Autowired
    private TransactionDefinition transactionDefinition;


    public RespBean checkBeforeSecKill(Long goodId, User user) {
        // 判断库存是否为0
        QueryWrapper<Good> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("good_stock").eq("id", goodId);
        int goodStock = goodMapper.selectOne(queryWrapper).getGoodStock();
        if (goodStock == 0) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK_ERROR);
        }
        // 判断秒杀库存是否为0
//        QueryWrapper<SeckillGood> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.select("stock_count").eq("good_id", goodId);
//        int goodStock1 = seckillGoodMapper.selectOne(queryWrapper1).getStockCount();
//        System.out.println("此时剩余：" + goodStock1);
//        if (goodStock1 <= 0) {
//            log.info(RespBeanEnum.EMPTY_STOCK_ERROR + "");
//            return RespBean.error(RespBeanEnum.EMPTY_STOCK_ERROR);
//        }
        // 判断用户是否重复秒杀(缓存中获取)
//        SeckillOrder seckillOrder = (SeckillOrder) redisCache.getCacheObject("order::" + "userId:" + user.getId() + "::goodId:" + goodId);
        QueryWrapper<SeckillOrder> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("good_id", goodId).eq("user_id", user.getId());
        SeckillOrder seckillOrder = seckillOrderMapper.selectOne(queryWrapper2);
        if (!ObjectUtils.isEmpty(seckillOrder)) {
            log.info(RespBeanEnum.REPEAT_KILL_ERROR + "");
            return RespBean.error(RespBeanEnum.REPEAT_KILL_ERROR);
        }
        return null;
    }


    /**
     * 秒杀
     *
     * @param goodId
     * @param user
     * @return
     */
    @Transactional
    @Override
//    public synchronized RespBean doSecKill(Long goodId, User user) {
    public RespBean doSecKill(Long goodId, User user) {

//        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);

        // 秒杀前的判断
        RespBean respBean = checkBeforeSecKill(goodId, user);
        if (!ObjectUtils.isEmpty(respBean)) {
//            platformTransactionManager.rollback(transaction);
            return respBean;
        }
        // 获取秒杀商品信息
        GoodVo goodVo = goodService.findGoodVoByGoodId(goodId);
        /**
         * 秒杀
         */
        // 秒杀商品表减库存
//        SeckillGood seckillGood = seckillGoodService.getOne(new QueryWrapper<SeckillGood>().eq("good_id", goodId));
//        seckillGood.setStockCount(seckillGood.getStockCount() - 1);
//        seckillGoodService.updateById(seckillGood);
        UpdateWrapper<SeckillGood> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("stock_count = stock_count - 1").eq("good_id", goodId).gt("stock_count", 0);
        boolean updateResult = seckillGoodService.update(updateWrapper);
        if (!updateResult) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK_ERROR);
        }
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
        seckillOrderService.save(seckillOrder);
        // 订单信息存redis
        redisCache.setCacheObject("order::" + "userId:" + user.getId() + "::goodId:" + goodId, seckillOrder);

//        platformTransactionManager.commit(transaction);
        return RespBean.success(order);
    }
}
