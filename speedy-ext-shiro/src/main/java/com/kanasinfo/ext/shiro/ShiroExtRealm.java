package com.kanasinfo.ext.shiro;

import com.kanasinfo.ext.shiro.bean.UserModel;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 自定义Realm
 * Created by gefangshuai on 2015/11/4.
 */
@Component
public abstract class ShiroExtRealm extends AuthorizingRealm {
    /**
     * 获取登录用户信息
     */
    protected abstract UserModel getUserByUsername(String username);

    /**
     * 获取登录用户的角色信息
     */
    protected abstract Set<String> getRolesByUsername(String username);

    /**
     * 获取登录用户的权限信息
     */
    protected abstract Set<String> getPermissionsByUsername(String username);
    /**
     * 授权验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken credentials = (UsernamePasswordToken) token;
        String username = credentials.getUsername();
        if (username == null) {
            throw new UnknownAccountException("username not provided!");
        }
        UserModel user = getUserByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return authenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(getRolesByUsername(username));
        authorizationInfo.setStringPermissions(getPermissionsByUsername(username));
        return authorizationInfo;
    }
}
