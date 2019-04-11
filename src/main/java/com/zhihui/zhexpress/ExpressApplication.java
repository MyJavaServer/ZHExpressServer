package com.zhihui.zhexpress;

import com.zhihui.zhexpress.base.ZHLog;
import com.zhihui.zhexpress.config.app.AppGlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ExpressApplication {

    @Autowired
    private AppGlobalConfig appGlobalConfig;
    private static AppGlobalConfig mAppGlobalConfig;

    @PostConstruct()
    public void init(){
        mAppGlobalConfig = appGlobalConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExpressApplication.class, args);
        System.out.println("=====Global Application Mode: " + mAppGlobalConfig.getAppMode());
    }


    /**
     * get global application mode
     *
     * @return LIVE----线上模式
     * DEV ----开发模式
     * TEST----测试模式
     */
    public static String getGlobalAppMode() {
        String mode = mAppGlobalConfig.getAppMode();
        ZHLog.info( " =====getGlobalAppMode: mContext=" + mode);
        return mode;
    }
}
