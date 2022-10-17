package com.ayi.seckill.utils;

/**
 * @author HuiBBao
 * @create 2022/10/4 15:50
 */

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil
{
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param String 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String str) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}