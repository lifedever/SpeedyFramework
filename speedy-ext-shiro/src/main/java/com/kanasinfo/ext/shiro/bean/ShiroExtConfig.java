package com.kanasinfo.ext.shiro.bean;

import com.kanasinfo.ext.shiro.filter.ShiroExtFormAuthenticationFilter;
import com.kanasinfo.ext.shiro.ShiroExtRealm;

import java.util.Map;

/**
 * Created by gefangshuai on 2016/1/5.
 */
public interface ShiroExtConfig {

    ShiroExtRealm getShiroExtRealm();
    ShiroExtFormAuthenticationFilter getShiroServerFormAuthenticationFilter();

    /**
     * 定义登陆url
     */
    String getLoginUrl();

    /**
     * 定义登陆成功跳转的Url
     */
    String getSuccessUrl();

    /**
     * 定义未授权跳转的Url
     */
    String getUnauthorizedUrl();

    /**
     * 定义权限过滤链
     */
    Map<String, String> getFilterChainDefinitionMap();

}
