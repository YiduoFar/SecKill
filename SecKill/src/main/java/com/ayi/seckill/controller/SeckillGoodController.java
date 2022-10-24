package com.ayi.seckill.controller;


import com.alibaba.fastjson.JSON;
import com.ayi.seckill.vo.GoodVo;
import com.ayi.seckill.vo.QueryVo;
import com.ayi.seckill.domain.SeckillGood;
import com.ayi.seckill.vo.RespBean;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ayi.seckill.service.SeckillGoodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (SeckillGood)表控制层
 *
 * @author makejava
 * @since 2022-10-14 21:53:14
 */
@RestController
public class SeckillGoodController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SeckillGoodService seckillGoodService;

    /**
     * 分页查询所有数据
     * @param queryVo
     * @return
     */
    @PostMapping("/queryAllSecKillGood")
    public RespBean selectByKeyWord(QueryVo queryVo) {
        System.out.println(queryVo);
        return seckillGoodService.selectByKeyWord(queryVo);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/querySecKillGoodById")
    public RespBean selectById(@RequestParam("id") Long id) {
        return seckillGoodService.selectById(id);
    }


}

