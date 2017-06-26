package com.basic.core.config.jersey;

import com.basic.core.rest.UserResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * 创建一个类型为 ResourceConfig的 Bean，用于注册所有的断点。
 * 所有注册的断点都需要注解 @Component 和 HRP资源annotation（比如 @Get）
 *
 * 注意：Jersey的servlet会被注册，并默认映射到 /* 这跟默认的mvc冲突了，可以将 @ApplicationPath 添加到 ResourceConfig 来改变映射。
 */
@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(JacksonFeature.class);
        register(UserResource.class);
    }

//    @Bean
//    public ServletRegistrationBean jerseyServlet() {
//        //所有的jersey构建的api都以/rest/*。
//        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
//        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
//        return registration;
//    }

}
