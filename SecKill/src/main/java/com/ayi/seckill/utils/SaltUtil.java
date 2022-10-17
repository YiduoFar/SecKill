package com.ayi.seckill.utils;

import java.util.Random;

/**
 * @author HuiBBao
 * @create 2022/10/2 18:39
 */
public class SaltUtil {
    /**
     * 生成salt的静态方法
     *
     * @return
     */
    public static String getSalt() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

}
