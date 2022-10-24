package com.ayi.seckill.mapper;

import com.ayi.seckill.domain.Good;
import com.ayi.seckill.vo.GoodVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
* @author HuiBBao
* @description 针对表【good】的数据库操作Mapper
* @createDate 2022-10-14 21:49:50
* @Entity com.ayi.seckill.domain.Good
*/
public interface GoodMapper extends BaseMapper<Good> {

    GoodVo selectGoodVoByGoodId(@Param(Constants.WRAPPER) QueryWrapper<Object> queryWrapper);
}




