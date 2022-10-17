package com.ayi.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @author HuiBBao
 * @create 2022/10/1 18:40
 */
@Component
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPass2FromPass(String inputPass) {
        return md5(MD5Util.salt.charAt(0) + MD5Util.salt.charAt(2) + inputPass + MD5Util.salt.charAt(5) + MD5Util.salt.charAt(4));
    }

    public static String formPass2DBPass(String formPass, String salt) {
        return md5(salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4));
    }

    public static String inputPass2DBPass(String inputPass, String salt) {
        String formPass = inputPass2FromPass(inputPass);
        System.out.println(formPass);
        String dbPass = formPass2DBPass(formPass, salt);
        System.out.println(dbPass);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPass2DBPass("123456", salt));
    }

}