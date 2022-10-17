package com.ayi.seckill.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据验证工具类
 *
 * @author HuiBBao
 * @create 2022/10/2 12:29
 */
@Component
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }

}
