package com.ayi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.service.UserService;
import com.ayi.seckill.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author HuiBBao
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-10-02 10:33:55
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




