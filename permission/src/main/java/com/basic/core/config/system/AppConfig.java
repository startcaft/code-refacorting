package com.basic.core.config.system;

import com.basic.core.entity.App;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 当前应用配置
 */
@Configuration
public class AppConfig {

    @Value("${app.id}")
    private Long appId;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Bean
    public App app(){
        {
            App app = new App();
            app.setId(appId);
            return app;
        }
    }
}
