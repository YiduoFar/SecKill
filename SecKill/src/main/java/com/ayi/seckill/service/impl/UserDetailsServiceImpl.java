package com.ayi.seckill.service.impl;

import com.ayi.seckill.domain.LoginUser;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.exception.GlobalException;
import com.ayi.seckill.mapper.UserMapper;
import com.ayi.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author HuiBBao
 * @create 2022/10/4 17:02
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, mobile);
        User user = userMapper.selectOne(wrapper);
        // 如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中

        // 封装成UserDetails对象返回
        return new LoginUser(user);
    }
}