package com.zhihui.zhexpress.config.app;

import com.alibaba.fastjson.JSONObject;
import com.zhihui.zhexpress.base.ZHLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jerryyin
 * @version 1.0
 * @name AppRequestAspect
 * @description 自定义切面， 用于捕捉前端用户请求参数及显示日志
 * @date 2019/5/7 14:10
 **/
@Aspect
@Component
public class AppRequestAspect {

    /**
     * 定义一个切入点，面向全部的controller层
     */
    @Pointcut("execution(* com.zhihui.zhexpress.controller.*.*(..))")
    public void allRequests() {
    }

    /**
     * 请求之前，捕捉
     *
     * @param point
     */
    @Before("allRequests()")
    public void beforeRequest(JoinPoint point) {
        String url = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        ZHLog.info2("-->>请求：" + url);
        try {
//            if (fileuri.contains("FileUpload")) {
//                DSLog.info2("-->>参数" + "文件file类型");
//            } else {
            ZHLog.info2("-->>参数：" + JSONObject.toJSONString(point.getArgs()));
//            }
        } catch (Exception i) {
            ZHLog.info(i.getMessage());
        }
    }

    @AfterThrowing(value = "allRequests()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) {
        String url = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        ZHLog.info2("-->>请求：" + url);
        ZHLog.info2("-->>处理异常 ：" + e.getMessage());
    }


}
