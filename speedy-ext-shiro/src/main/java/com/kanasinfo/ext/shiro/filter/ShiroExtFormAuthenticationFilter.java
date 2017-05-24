package com.kanasinfo.ext.shiro.filter;

import com.kanasinfo.ext.shiro.bean.ShiroUser;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
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

    protected abstract void doSomethingOnLoginSuccess(ApplicationContext context, AuthenticationToken token, ShiroUser shiroUser, Subject subject, ServletRequest request, ServletResponse response);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((request.getServletContext()));
        Session session = subject.getSession();
        ShiroUser shiroUser = new ShiroUser((String) subject.getPrincipal());
        doSomethingOnLoginSuccess(context, token, shiroUser, subject, request, response);
        session.setAttribute(ShiroUser.SHIRO_USER_SESSION_KEY, shiroUser);
        return super.onLoginSuccess(token, subject, request, response);
    }
}