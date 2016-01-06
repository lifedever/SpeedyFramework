package io.github.gefangshuai.demo.model;

import io.github.gefangshuai.ext.shiro.bean.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gefangshuai on 2016/1/6.
 */
@Entity
@Table(name = "user")
public class User extends UserModel{
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
