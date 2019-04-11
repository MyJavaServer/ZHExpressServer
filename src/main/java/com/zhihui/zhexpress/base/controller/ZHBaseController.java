package com.zhihui.zhexpress.base.controller;

import com.zhihui.zhexpress.base.exception.ZHErrorParamsException;
import com.zhihui.zhexpress.config.ZHConfig;
import com.zhihui.zhexpress.manager.UserTokenManager;
import com.zhihui.zhexpress.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author cc
 * @date 18-7-12 下午3:39
 * 一般controller里面，都会有 增删查改四中最基本的
 * 这里没有写删除。不常用
 * 添加参数null验证。。自动抛异常。并 返回参数错误
 */
@RestController
public abstract class ZHBaseController{


    @Autowired
    protected HttpServletRequest request;

    /**
     * 判断参数是否为null
     * 若为null则抛出自定义 ASException,并返回 参数错误，请核对
     *
     * @param object
     * @param <T>
     * @return
     */
    static <T> T notNull(Object object) {
        return (T) Optional.ofNullable(object)
                .orElseThrow(ZHErrorParamsException::new);
   }

    /**
     * 判断参数是否为null或者“” 主要对字符串
     * 若为null或“” 则抛出自定义 ASException,并返回参数错误，请核对
     *
     * @param str 字符串
     * @return 原字符串
     */
    static String notNullEmpty(String str) {
        return Optional.ofNullable(str)
                .filter(s -> !s.isEmpty())
                .orElseThrow(ZHErrorParamsException::new);
    }


    /**
     * 获取用户的token
     *
     * @return userToken对象
     */
    UserToken getToken() {
        String str = request.getHeader(ZHConfig.KEY_AUTHORIZATION);
        UserToken userToken = (UserTokenManager.getInstance().parseTokenStr(str));
        return userToken;
    }


//    /**
//     * 获取ip
//     *
//     * @return ip地址
//     */
//    String getIp() {
//        return NetworkIpUtil.getClientIpAddr(request);
//    }

}
