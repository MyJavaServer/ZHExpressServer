package com.zhihui.zhexpress.config.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Personal Configure Class
 */
@Component
public class AppGlobalConfig {

    @Value("${spring.profiles.active}")
    private String appMode;

    public String getAppMode()
    {
        return appMode;
    }


}
