package com.zhihui.zhexpress.manager;

import com.alibaba.fastjson.JSONObject;
import com.zhihui.zhexpress.config.ZHConfig;
import com.zhihui.zhexpress.model.UserToken;
import com.zhihui.zhexpress.utils.JwtUtil;

import java.util.concurrent.TimeUnit;

public class UserTokenManager {

    public static UserTokenManager mInstance;
    private RedisManager mRedisManager = RedisManager.getInstance();


    public static UserTokenManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserTokenManager();
        }
        return mInstance;
    }

    /**
     * 有效时长自定义
     *
     * @param cacheType
     * @param cacheKey
     * @param token
     * @param time
     * @param timeUnit
     * @return
     */
    public String createToken(int cacheType, String cacheKey, UserToken token, long time, TimeUnit timeUnit) {
        //使用uuid作为源token
        String tokenstr = JwtUtil.createJWT(cacheType + "-" + cacheKey, token, ZHConfig.SECRET_KEY);
        if (timeUnit == null) {
            timeUnit = TimeUnit.HOURS;
        }
        mRedisManager.set(cacheType, cacheKey, tokenstr, time, timeUnit);
        return tokenstr;
    }

    /**
     * 解析tokenStr -> UserToken 对象
     *
     * @param authentication
     * @return
     */
    public UserToken parseTokenStr(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        try {
            return getParserToken(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取已存在的解密过后的UserToekn
     *
     * @param cacheType
     * @param cacheKey
     * @return
     */
    public UserToken getToken(int cacheType, String cacheKey) {
        String tokenStr = mRedisManager.get(cacheType, cacheKey).toString();
        try {
            return getParserToken(tokenStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取已存在的加密过后的用户token
     *
     * @param cacheType
     * @param cacheKey
     * @return
     */
    public String getTokenStr(int cacheType, String cacheKey) {
        return String.valueOf(mRedisManager.get(cacheType, cacheKey));
    }

    /**
     * 检查缓存是否有当前的token
     * 通过value判断
     *
     * @param cacheType
     * @param tokenStr
     * @return
     */
    public boolean hasToken(int cacheType, String tokenStr) {
        if (tokenStr == null) {
            return false;
        }
        int id;
        try {
            id = getParserToken(tokenStr).getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String token = String.valueOf(mRedisManager.get(cacheType, String.valueOf(id)));
        if (token == null || !token.equals(tokenStr)) {
            return false;
        } else {
            //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
            long time = 2;  //普通客户端，2小时
            if (cacheType == RedisManager.KEY_TYPE_USER_TOKEN_MOBILE_APP) {
                time = 7 * 24; //app端，一周的时间
            }
            mRedisManager.set(cacheType, String.valueOf(id), token, time, TimeUnit.HOURS);
            return true;
        }

    }

    /**
     * 判断当前缓存是否有token
     * 通过key判断
     *
     * @param cacheType
     * @param userId
     * @return
     */
    public boolean hasToken(int cacheType, int userId) {
        return mRedisManager.hasKey(cacheType, String.valueOf(userId));
    }

    public boolean removeToken(int cacheType, int userId) {
        if (hasToken(cacheType, userId)) {
            mRedisManager.delete(cacheType, String.valueOf(userId));
            return true;
        }
        return false;
    }

    public boolean removeToken(int cacheType, String tokenStr) {
        try {
            if (hasToken(cacheType, tokenStr)) {
                mRedisManager.delete(cacheType, String.valueOf(getParserToken(tokenStr).getUserId()));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 获取解密后的token对象
     *
     * @param tokenStr
     * @return
     * @throws Exception
     */
    public UserToken getParserToken(String tokenStr) throws Exception {
        String token = JwtUtil.parseJWT(tokenStr, ZHConfig.SECRET_KEY);
        if (token != null) {
            return JSONObject.parseObject(token, UserToken.class);
        }
        return null;
    }
}
