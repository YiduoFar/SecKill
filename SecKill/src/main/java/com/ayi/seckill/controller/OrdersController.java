package com.ayi.seckill.controller;



import com.ayi.seckill.domain.Orders;
import com.ayi.seckill.vo.RespBean;
import com.ayi.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ayi.seckill.service.OrdersService;
import io.netty.util.internal.ObjectUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2022-10-24 21:16:33
 */
@RestController
public class OrdersController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    @PostMapping("/queryOrderById")
    public RespBean queryOrderById(Long orderId) {
        Orders order = ordersService.getById(orderId);
        if (ObjectUtils.isEmpty(order)) {
            return RespBean.error(RespBeanEnum.NULL_DATA_ERROR);
        }
        return RespBean.success(ordersService.getById(orderId));
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param orders 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Orders> page, Orders orders) {
        return success(this.ordersService.page(page, new QueryWrapper<>(orders)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.ordersService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orders 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Orders orders) {
        return success(this.ordersService.save(orders));
    }

    /**
     * 修改数据
     *
     * @param orders 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Orders orders) {
        return success(this.ordersService.updateById(orders));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.ordersService.removeByIds(idList));
    }
}

