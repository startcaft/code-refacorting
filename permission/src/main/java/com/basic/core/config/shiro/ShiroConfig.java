package com.basic.core.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import javax.servlet.DispatcherType;
import java.util.LinkedHashMap;

/**
 * Created by startcaft on 2017/7/7.
 * Shiro集中配置类
 */
@Configuration
public class ShiroConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    /*配置LifecycleBeanPostProcessor声明周期管理器，它可以自动地来调用配置在IOC容器中的 shiro bean 的生命周期方法，这个bean不能少*/
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /*启用 IOC 容器中使用 shiro 的注解，但是必须在配置了  lifecycleBeanPostProcessor 才可以使用*/
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired org.apache.shiro.mgt.SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }


    /*配置EhCacheManager*/
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    /*配置密码比对器，配置为加密方式为MD5，还可以设置循环加密的次数*/
    @Bean
    public HashedCredentialsMatcher hashMatcher(){
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("MD5");
        return hashMatcher;
    }

    /*配置自定义Realm，也就是安全数据的来源，MD5加密*/
    @Bean
    public UserDataRealm realm(@Autowired HashedCredentialsMatcher hashMatcher,
                               @Autowired EhCacheManager cacheManager) {
        UserDataRealm realm = new UserDataRealm();
        realm.setCredentialsMatcher(hashMatcher);
        realm.setCacheManager(cacheManager);
        //开启认证信息的缓存，并指定 cache 的名称(ehcache.xml中配置的cache名)
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("shiroCache");
        //开启授权信息的缓存，并指定 cache 的名称(ehcache.xml中配置的cache名)
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("shiroCache");
        return realm;
    }

    /*配置核心安全管理器SecurityManager，依赖于Realm或者认证器(多Realm认证)，必须从中读取安全数据*/
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(@Autowired EhCacheManager cacheManager,
                                                                @Autowired UserDataRealm realm) {
        LOGGER.info("初始化Shiro核心安全管理器配置核心安全管理器SecurityManager");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(cacheManager);
        return manager;
    }

    /*该bean的名称必须为shiroFilter*/
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录需要跳转的url和登录成功后跳转的url以及认证失败后跳转的url
        bean.setLoginUrl("/admin/index");
        bean.setUnauthorizedUrl("/admin/unauth");

        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/main", "anon");
        filterChainDefinitionMap.put("/admin/login","anon");
        filterChainDefinitionMap.put("/statics/**","anon");/*mvc配置的资源文件的映射路径*/
        //登出
        filterChainDefinitionMap.put("/admin/logout","logout");
    //标识需要认证才能访问
        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /*最后配置一个shiroFilter 拦截器，拦截所有的url请求，该filter-name的配置必须跟Spring容器中配置的bean的id一致*/
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }
}
