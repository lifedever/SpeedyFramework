package io.github.gefangshuai.demo.config;

import io.github.gefangshuai.ext.shiro.ShiroExtConfiguration;
import io.github.gefangshuai.ext.shiro.ShiroExtRealm;
import io.github.gefangshuai.ext.shiro.bean.ExtShiroConfig;
import io.github.gefangshuai.ext.shiro.bean.UserModel;
import io.github.gefangshuai.ext.shiro.filter.ShiroExtFormAuthenticationFilter;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gefangshuai on 2016/1/5.
 */
@Configuration
public class ShiroConfiguration extends ShiroExtConfiguration {

    @Override
    protected ExtShiroConfig extShiroConfig() {
        return new ExtShiroConfig() {
            @Override
            public ShiroExtRealm getShiroServerRealm() {
                return new ShiroExtRealm() {
                    @Override
                    protected UserModel getUserByUsername(String username) {
                        UserModel user = new UserModel();
                        user.setUsername("admin");
                        user.setPassword("admin");
                        UserModel.encryptUser(user);
                        return user;
                    }

                    @Override
                    protected Set<String> getRolesByUsername(String username) {
                        return null;
                    }

                    @Override
                    protected Set<String> getPermissionsByUsername(String username) {
                        return null;
                    }
                };
            }

            @Override
            public ShiroExtFormAuthenticationFilter getShiroServerFormAuthenticationFilter() {
                return new ShiroExtFormAuthenticationFilter() {
                    @Override
                    protected void doSomethingOnLoginSuccess(ApplicationContext context, AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
                        System.out.println("login success!");
                    }
                };
            }

            @Override
            public String getLoginUrl() {
                return "/login";
            }

            @Override
            public String getSuccessUrl() {
                return "/";
            }

            @Override
            public String getUnauthorizedUrl() {
                return "/unauthorized";
            }

            @Override
            public Map<String, String> getFilterChainDefinitionMap() {
                Map<String, String> filterMap = new HashMap<>();
                filterMap.put("/js/**", "anon");
                filterMap.put("/css/**", "anon");
                filterMap.put("/imgs/**", "anon");
                filterMap.put("/libs/**", "anon");
                filterMap.put("/**", "authc");
                return filterMap;
            }
        };
    }
}
