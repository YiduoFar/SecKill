package com.ayi.seckill.vo;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HuiBBao
 * @create 2022/10/2 9:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @return
     */
    public static RespBean success() {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     * @return
     */
    public static RespBean success(Object o) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), o);
    }

    /**
     * 失败返回结果
     * @param respBeanEnum
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param respBeanEnum
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum, Object o) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), o);
    }


    /**
     * 失败返回结果
     * @param code
     * @param message
     * @return
     */
    public static RespBean error(int code, String message) {
        return new RespBean(code, message, null);
    }

    /**
     * 失败返回结果
     * @param code
     * @param message
     * @return
     */
    public static RespBean error(int code, String message, Object o) {
        return new RespBean(code, message, o);
    }

}
