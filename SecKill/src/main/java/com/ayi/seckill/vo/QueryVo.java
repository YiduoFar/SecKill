package com.ayi.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HuiBBao
 * @create 2022/10/17 0:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryVo {
    /**
     * 当前页
     */
    int current;
    /**
     * 一页多少条数据
     */
    int size;
    /**
     * 关键字
     */
    String word;
}
