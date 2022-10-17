package com.ayi.seckill.service.impl;

import com.ayi.seckill.domain.Good;
import com.ayi.seckill.mapper.GoodMapper;
import com.ayi.seckill.vo.GoodVo;
import com.ayi.seckill.vo.QueryVo;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ayi.seckill.domain.SeckillGood;
import com.ayi.seckill.service.SeckillGoodService;
import com.ayi.seckill.mapper.SeckillGoodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author HuiBBao
 * @description 针对表【seckill_good】的数据库操作Service实现
 * @createDate 2022-10-14 21:53:00
 */
@Service
@Slf4j
public class SeckillGoodServiceImpl extends ServiceImpl<SeckillGoodMapper, SeckillGood>
        implements SeckillGoodService {

    @Autowired
    SeckillGoodMapper seckillGoodMapper;


    @Override
    public RespBean selectByKeyWord(QueryVo queryVo) {

        log.info(queryVo.toString());

        // 封装分页对象
        Page<GoodVo> page = new Page<>(queryVo.getCurrent(), queryVo.getSize());

        // 模糊查询good_name字段
        seckillGoodMapper.selectGoodVoPage(page, new QueryWrapper<>().like("g.good_name", queryVo.getWord()));

        List<GoodVo> records = page.getRecords();

        if (CollectionUtils.isEmpty(records)) {
            return RespBean.error(RespBeanEnum.NULL_DATA_ERROR);
        }

        return RespBean.success(records);
    }
}




