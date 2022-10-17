package com.ayi.seckill.service;

import com.ayi.seckill.vo.LoginVo;
import com.ayi.seckill.vo.RegVo;
import com.ayi.seckill.vo.RespBean;

/**
 * @author HuiBBao
 * @create 2022/10/2 12:42
 */
public interface LoginService {

    RespBean login(LoginVo loginVo);

    RespBean reg(RegVo regVo);

    RespBean logout();

}
