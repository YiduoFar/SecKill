package com.ayi.seckill.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName seckill_order
 */
@TableName(value ="seckill_order")
@Data
public class SeckillOrder implements Serializable {
    /**
     * 秒杀订单id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}