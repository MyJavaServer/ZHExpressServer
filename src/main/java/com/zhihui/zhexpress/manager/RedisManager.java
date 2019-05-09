package com.zhihui.zhexpress.manager;
import com.zhihui.zhexpress.base.ZHLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {


    //缓存数据的主键类型 (避免种类过多重复key)
    // 1. 用户类， 主键为 type-userId
    public static final int KEY_TYPE_USER_TOKEN_ID = 0x01;            //用户id           (用于pc网页存储用户登录生成的token) 2小时有效期
    public static final int KEY_TYPE_USER_TOKEN_ID_RSA = 0x02;        //用户id + 密钥rsa  (用于登录校验的短时缓存)
    public static final int KEY_TYPE_USER_TOKEN_MOBILE_WXH5 = 0x03;   //用户 微信H5 登录  (用于存储用户登录生成的token)
    public static final int KEY_TYPE_USER_TOKEN_MOBILE_APP = 0x05;    //移动端app登录     (用于存移动端app生成的token)       7天有效期

    // 2. 手机类，  type-phone
    public static final int KEY_TYPE_PHONE = 0x10;         //用户电话号吗      (短信验证码)

    // 3.其他   type-barId
    public static final int KEY_TYPE_OTHER = 0x30;        //其他缓存




    public static RedisManager mInstance;

    //线程共享变量
//    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    //lua 脚本删除redislock 更快，不会有get出来，对比，再删除的长时间差
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }


    @Qualifier("stringRedisTemplate")
    @Autowired
    public RedisTemplate<String, String> redisTemplate;
    private static RedisTemplate<String, String> mRedisTemplate;
    private static ValueOperations<String, String> mOperations;

    @PostConstruct
    public void init() {
        mRedisTemplate = redisTemplate;
        if (mOperations == null) {
            mOperations = mRedisTemplate.opsForValue();
        }
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return mRedisTemplate;
    }

    public RedisManager setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        return this;
    }

    public static RedisManager getInstance() {
        if (mInstance == null) {
            mInstance = new RedisManager();
        }
        return mInstance;
    }

    /**
     * 添加缓存数据
     *
     * @param key      数据主键
     * @param keyType  主键类型     数据种类看上方
     * @param value    数据内容     目前设定成只支持string， 详情请查看 redis数据类型 和 里氏替换原则的危机
     * @param time     有效时长
     * @param timeUnit 时间单位   eg： TimeUnit.HOURS
     */
    public void set(int keyType, String key, String value, long time, TimeUnit timeUnit) {
        mOperations.set(keyType + "-" + key, value, time, timeUnit);


    }

    public String get(int keyType, String key) {
        return mOperations.get(keyType + "-" + key);
    }

    public void delete(int keyType, String key) {
        mRedisTemplate.delete(keyType + "-" + key);
    }

    public boolean hasKey(int keyType, String key) {
        return mRedisTemplate.hasKey(keyType + "-" + key);
    }

    /**
     * 设置有序list，用于队列
     * @param key 类型
     * @param value 值
     */
    public void setList(String key,String value){
        mRedisTemplate.opsForList().leftPush(key,value);
    }

}
