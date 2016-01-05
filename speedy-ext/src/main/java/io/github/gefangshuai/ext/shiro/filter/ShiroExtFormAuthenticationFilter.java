package io.github.gefangshuai.ext.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * shiro登录权限验证过滤器
 */
public abstract class ShiroExtFormAuthenticationFilter extends FormAuthenticationFilter {

    protected abstract void doSomethingOnLoginSuccess(ApplicationContext context, AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((request.getServletContext()));
        doSomethingOnLoginSuccess(context, token, subject, request, response);

        return super.onLoginSuccess(token, subject, request, response);
    }
}