package com.kanasinfo.ext.shiro.bean;

/**
 * 封装登录用户Shiro信息
 * Created by gefangshuai on 2015/11/5.
 */
public class ShiroUser {
    public static final String SHIRO_USER_SESSION_KEY = "shiro_ext_user_session_key";

    private Object user;
    private String principal;
    private String[] roles;
    private String[] permissions;

    public ShiroUser(String principal) {
        this.principal = principal;
    }

    public ShiroUser(boolean isLogin, String principal, Object user) {
        this.principal = principal;
        this.user = user;
    }


    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
