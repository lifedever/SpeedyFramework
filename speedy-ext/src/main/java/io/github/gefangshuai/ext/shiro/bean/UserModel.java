package io.github.gefangshuai.ext.shiro.bean;

import io.github.gefangshuai.ext.persistence.CoreModel;
import io.github.gefangshuai.ext.utils.HashUtils;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * 用于Shiro登录校验的用户封装，自定义User Model可以继承此类
 */
@MappedSuperclass
public class UserModel extends CoreModel{

    @NotNull
    private String username;        // 登录名
    @NotNull
    private String password;        // 密码
    private String salt;            // 密码盐值

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 加密用户，赋予用户salt值及md5 password
     * @param user
     */
    public static void encryptUser(UserModel user){
        String salt = HashUtils.generateSalt();
        user.setSalt(salt);
        String password = HashUtils.toMd5(user.getPassword(), salt);
        user.setPassword(password);
    }
}
