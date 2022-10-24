package com.ayi.seckill.service;

import com.ayi.seckill.domain.SeckillGood;
import com.ayi.seckill.vo.QueryVo;
import com.ayi.seckill.vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HuiBBao
* @description 针对表【seckill_good】的数据库操作Service
* @createDate 2022-10-14 21:53:00
*/
public interface SeckillGoodService extends IService<SeckillGood> {

    RespBean selectByKeyWord(QueryVo queryVo);

    RespBean selectById(Long id);

    RespBean selectGoodVoBySecKillGoodId(Long id);
}
