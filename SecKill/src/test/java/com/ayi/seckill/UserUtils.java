package com.ayi.seckill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ayi.seckill.controller.LoginController;
import com.ayi.seckill.domain.User;
import com.ayi.seckill.mapper.UserMapper;
import com.ayi.seckill.service.LoginService;
import com.ayi.seckill.utils.MD5Util;
import com.ayi.seckill.vo.LoginVo;
import com.ayi.seckill.vo.RegVo;
import com.ayi.seckill.vo.RespBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author HuiBBao
 * @create 2022/10/29 12:27
 */
@SpringBootTest
public class UserUtils {

    @Autowired
    UserMapper userMapper;

    @Autowired
    LoginService loginService;

    public final static int userListSize = 5000;
    public final static Long mobile = 1300000000L;
    public final static String passwordAfterMD5 = MD5Util.myMD5Crypt("123456");
    public final static String password = MD5Util.myMD5Crypt("123456");

    /**
     * 创建模拟数据
     */
    @Test
    public void insertUsers() {
        for (int i = 0; i < userListSize; i++) {
            RegVo regVo = new RegVo();
            regVo.setMobile(mobile + i + "");
            regVo.setPassword(passwordAfterMD5);
            loginService.reg(regVo);
        }
    }

    /**
     * 创建token.txt
     */
    @Test
    public void createTokenTxt() throws Exception {
        // 使用login接口获取token
        List<String> tokenList = new ArrayList<>();
        for (int i = 0; i < userListSize; i++) {
            LoginVo loginVo = new LoginVo();
            loginVo.setMobile(mobile + i + "");
            loginVo.setPassword(passwordAfterMD5);
            RespBean respBean = loginService.login(loginVo);
            String tokenStr = respBean.getObj().toString();
            int length = tokenStr.length();
            String tokenText = tokenStr.substring(0, length - 1).split("=")[1];
            tokenList.add(tokenText);
        }
        File file = new File("D:\\Project\\SecKill\\SecKill\\src\\test\\token.txt");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        // TODO:token写入txt
        for (String x : tokenList) {
            outputStreamWriter.write(x);
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.close();
        fileOutputStream.close();
    }

}
