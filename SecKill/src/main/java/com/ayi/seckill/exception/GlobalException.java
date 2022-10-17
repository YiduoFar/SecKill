package com.ayi.seckill.exception;

import com.ayi.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HuiBBao
 * @create 2022/10/4 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{

    private RespBeanEnum respBeanEnum;

}
