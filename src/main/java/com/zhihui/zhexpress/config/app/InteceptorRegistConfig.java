package com.zhihui.zhexpress.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器注册
 *
 * @date 18-8-31 上午11:23
 */
@Configuration
public class InteceptorRegistConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new CusInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
