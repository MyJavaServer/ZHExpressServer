package com.zhihui.zhexpress.config.app;


import com.zhihui.zhexpress.base.ZHLog;
import com.zhihui.zhexpress.base.exception.ZHUserInfoException;
import com.zhihui.zhexpress.config.ZHConfig;
import com.zhihui.zhexpress.interfaces.Permission;
import com.zhihui.zhexpress.manager.RedisManager;
import com.zhihui.zhexpress.manager.UserTokenManager;
import com.zhihui.zhexpress.model.User;
import com.zhihui.zhexpress.model.UserToken;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @date 18-3-22 上午10:40
 * <p>
 * 添加接口单位时间请求次数限制
 */

@Component
public class CusInterceptor extends HandlerInterceptorAdapter {

    private UserTokenManager userTokenManager = UserTokenManager.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        boolean result = false;
        //对于注释在类上的 需要先获取类名，然后再通过反射获取注释内容
        ZHLog.info(request.getMethod() + ":" + request.getRequestURI());

        //统一接口权限判断
        Permission permission = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
        if (permission != null  && !permission.needToken()){
            //不需要权限，直接通过
            return true;
        }

        String tokenStr = request.getHeader(ZHConfig.KEY_AUTHORIZATION);
        if (tokenStr == null || tokenStr.isEmpty()) {
//        Optional.ofNullable(tokenStr).orElseThrow(() -> new ZHUserInfoException("您还未登陆，无法请求！"));
            throw new ZHUserInfoException("您还未登陆，无法请求！");
        }

        boolean hasToken = userTokenManager.hasToken(RedisManager.KEY_TYPE_USER_TOKEN_ID, tokenStr);
        if (!hasToken) {
            throw new ZHUserInfoException("登录过期，请重新登录！");
        }else {
            result = true;
        }
//        UserToken userToken = userTokenManager.getParserToken(tokenStr);
        return result;
    }

//    private boolean checkCount(Authority author, Integer userId) {
//        //针对同一用户，接口请求次数限制
//        if (author.count() != -1 && author.second() != -1) {
//            Integer count = Optional.ofNullable(DSRedisManager.getInstance().get(DSRedisManager.KEY_TYPE_ACCESS_COUNT, userId + author.methoName())).map(Integer::parseInt).orElse(0);
//            if (count < author.count()) {
//                DSRedisManager.getInstance().set(DSRedisManager.KEY_TYPE_ACCESS_COUNT, userId + author.methoName(), String.valueOf(count + 1), author.second(), TimeUnit.SECONDS);
//                return true;
//            } else {
//                throw new DSOperationFailedException("请求过于频繁，请稍后重试!");
//            }
//        }
//        return true;
//    }
}