package com.ayi.seckill.vo;

import com.ayi.seckill.domain.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HuiBBao
 * @create 2022/10/14 21:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodVo extends Good {

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

}
