package com.kanasinfo.ext.shiro;

import com.kanasinfo.ext.shiro.bean.ShiroExtConfig;
import com.kanasinfo.ext.shiro.filter.ShiroExtFormAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gefangshuai on 2015/11/4.
 */
@Configuration
public abstract class ShiroExtConfiguration {

    protected abstract ShiroExtConfig shiroExtConfig();

    @Bean(name = "ShiroRealmImpl")
    public ShiroExtRealm getShiroRealm() {
        ShiroExtRealm serverRealm = shiroExtConfig().getShiroExtRealm();

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        serverRealm.setCredentialsMatcher(credentialsMatcher);
        return serverRealm;
    }


    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getShiroRealm());
        securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    @Bean
    public CustomAuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        CustomAuthorizationAttributeSourceAdvisor sourceAdvisor = new CustomAuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(getDefaultWebSecurityManager());
        return new CustomAuthorizationAttributeSourceAdvisor();
    }

    /**
     * 设置自定义的formFilter
     *
     * @return
     */
    public ShiroExtFormAuthenticationFilter getCustomFormAuthenticationFilter() {
        ShiroExtFormAuthenticationFilter authenticationFilter = shiroExtConfig().getShiroServerFormAuthenticationFilter();
        return authenticationFilter;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("authc", getCustomFormAuthenticationFilter());

        filterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        filterFactoryBean.setLoginUrl(shiroExtConfig().getLoginUrl());
        filterFactoryBean.setSuccessUrl(shiroExtConfig().getSuccessUrl());
        filterFactoryBean.setUnauthorizedUrl(shiroExtConfig().getUnauthorizedUrl());
        filterFactoryBean.setFilters(filterMap);
        filterFactoryBean.setFilterChainDefinitionMap(shiroExtConfig().getFilterChainDefinitionMap());

        return filterFactoryBean;
    }
}
