package io.github.gefangshuai.ext.shiro.bean;

import io.github.gefangshuai.ext.shiro.ShiroExtRealm;
import io.github.gefangshuai.ext.shiro.filter.ShiroExtFormAuthenticationFilter;

import java.util.Map;

/**
 * Created by gefangshuai on 2016/1/5.
 */
public interface ShiroExtConfig {
    ShiroExtRealm getShiroExtRealm();
    ShiroExtFormAuthenticationFilter getShiroServerFormAuthenticationFilter();

    String getLoginUrl();
    String getSuccessUrl();
    String getUnauthorizedUrl();
    Map<String, String> getFilterChainDefinitionMap();

}
