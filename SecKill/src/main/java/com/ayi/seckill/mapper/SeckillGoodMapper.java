package com.ayi.seckill.mapper;

import com.ayi.seckill.domain.SeckillGood;
import com.ayi.seckill.vo.GoodVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author HuiBBao
 * @description 针对表【seckill_good】的数据库操作Mapper
 * @createDate 2022-10-14 21:53:00
 * @Entity com.ayi.seckill.domain.SeckillGood
 */
@Mapper
public interface SeckillGoodMapper extends BaseMapper<SeckillGood> {
    // 多表关联sql语句自己写；分页以及条件用mp,返回值是Map

    /**
     * 多表关联SQL语句自主实现，分页以及条件使用MybatisPlus
     * @Param(Constants.WRAPPER) 注解：让Wrapper组合到自定义的SQL语句的${ew.customSqlSegment}中
     * @param page
     * @param queryWrapper
     * @return
     */
    @MapKey("id")
    IPage<Map<String, Object>> selectGoodVoPage(Page page, @Param(Constants.WRAPPER) Wrapper queryWrapper);

    GoodVo selectGoodVoBySecKillGoodId(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}




