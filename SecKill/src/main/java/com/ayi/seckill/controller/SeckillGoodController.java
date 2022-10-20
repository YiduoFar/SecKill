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
    public RespBean selectById(@RequestParam("id") Integer id) {
        return seckillGoodService.selectById(id);
    }
    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param seckillGood 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SeckillGood> page, SeckillGood seckillGood) {
        return success(this.seckillGoodService.page(page, new QueryWrapper<>(seckillGood)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.seckillGoodService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param seckillGood 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SeckillGood seckillGood) {
        return success(this.seckillGoodService.save(seckillGood));
    }

    /**
     * 修改数据
     *
     * @param seckillGood 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SeckillGood seckillGood) {
        return success(this.seckillGoodService.updateById(seckillGood));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.seckillGoodService.removeByIds(idList));
    }
}

