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
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 收货地址id
     */
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private Integer goodCount;

    /**
     * 商品单价
     */
    private BigDecimal goodPrice;

    /**
     * 1pc,2android,3ios
     */
    private Integer orderChannel;

    /**
     * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    private Integer status;

    /**
     * 订单创建时间
     */
    private Date createDate;

    /**
     * 订单支付时间
     */
    private Date payDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}