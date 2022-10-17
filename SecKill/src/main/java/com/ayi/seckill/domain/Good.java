package com.ayi.seckill.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName good
 */
@TableName(value ="good")
@Data
public class Good implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 商品标题
     */
    private String goodTitle;

    /**
     * 商品图片
     */
    private String goodImg;

    /**
     * 详情
     */
    private String goodDetail;

    /**
     * 价格
     */
    private BigDecimal goodPrice;

    /**
     * 库存，-1表示无限制
     */
    private Integer goodStock;

    /**
     * 付款人数
     */
    private Integer goodPayNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}