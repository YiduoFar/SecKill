package com.ayi.seckill.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName seckill_good
 */
@TableName(value ="seckill_good")
@Data
public class SeckillGood implements Serializable {
    /**
     * 秒杀商品id
     */
    @TableId
    private Long id;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}