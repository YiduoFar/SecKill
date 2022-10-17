package com.ayi.seckill;

import com.ayi.seckill.controller.SeckillGoodController;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.mapper.UserMapper;
import com.ayi.seckill.vo.QueryVo;
import com.ayi.seckill.vo.RespBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
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




}
