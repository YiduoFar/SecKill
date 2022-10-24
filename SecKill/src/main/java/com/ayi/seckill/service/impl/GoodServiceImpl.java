package com.ayi.seckill.service.impl;

import com.ayi.seckill.vo.GoodVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ayi.seckill.domain.Good;
import com.ayi.seckill.service.GoodService;
import com.ayi.seckill.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author HuiBBao
* @description 针对表【good】的数据库操作Service实现
* @createDate 2022-10-14 21:49:50
*/
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good>
    implements GoodService{

    @Autowired
    GoodMapper goodMapper;

    @Override
    public GoodVo findGoodVoByGoodId(Long goodId) {
        return goodMapper.selectGoodVoByGoodId(new QueryWrapper<>().eq("g.id", goodId));
    }
}




