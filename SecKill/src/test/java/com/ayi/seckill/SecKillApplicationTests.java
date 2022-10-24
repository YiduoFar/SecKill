package com.ayi.seckill;
import java.math.BigDecimal;
import java.util.Date;

import com.ayi.seckill.controller.SeckillGoodController;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.mapper.OrdersMapper;
import com.ayi.seckill.mapper.UserMapper;
import com.ayi.seckill.service.impl.SeckillServiceImpl;
import com.ayi.seckill.vo.QueryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@SpringBootTest
class SecKillApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SeckillGoodController seckillGoodController;

    @Test
    public void TestUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode = passwordEncoder.encode("d3b1294a61a07da9b49b6e22b2cbd7f9");
//        System.out.println(encode);
        System.out.println(passwordEncoder.matches("d3b1294a61a07da9b49b6e22b2cbd7f9", "$2a$10$MnAmRa7bH34yonCybzoBbupibfoKO3dqWRyYNGP6v6w3YrXGqGJPu"));

    }

    @Test
    public void testSelectPage() {
        Object obj = seckillGoodController.selectByKeyWord(new QueryVo(1, 2, "")).getObj();
        System.out.println(obj);
    }

    @Autowired
    SeckillServiceImpl seckillServiceImpl;

    @Autowired
    OrdersMapper orderMapper;

    @Test
    public void testSecKill() {
        User user = new User();
        user.setId(2L);
        seckillServiceImpl.doSecKill(1L, user);

//        Order order = new Order();
//        order.setUserId(0L);
//        order.setGoodId(0L);
//        order.setDeliveryAddrId(0L);
//        order.setGoodName("");
//        order.setGoodCount(0);
//        order.setGoodPrice(new BigDecimal("0"));
//        order.setOrderChannel(0);
//        order.setStatus(0);
//        order.setCreateDate(new Date());
//        order.setPayDate(new Date());
//        int insert = orderMapper.insert(order);
//        assert insert == 1;
    }




}
