package com.zhihui.zhexpress.interfaces;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 用于设置接口权限
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Permission {

    boolean needToken() default true;   //是否需要登录（token） 默认-需要

    /**
     * 权限控制
     * 加上@Authority(role = {"0","1"}) 表示可以支持  type= 0或1 的用户访问该接口  ::: -1表示 不需要权限
     * 加上@Authority(derole ={"1","2"}) 表示不让  1 ，2 的角色访问，其他未申明的可以访问
     */
//    String[] role() default {"1"};  //可访问
//    String[] derole() default {};   //不可访问

}
